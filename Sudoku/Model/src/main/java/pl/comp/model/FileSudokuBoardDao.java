package pl.comp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.platform.commons.util.ToStringBuilder;
import pl.comp.model.exceptions.DaoFileException;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private static Logger logger = (Logger)
            LogManager.getLogger(FileSudokuBoardDao.class.getName());

    ResourceBundle resourceBundle = ResourceBundle.getBundle("pl.comp.model.bundles.bundle");

    private String filename;
    private String absolutePath;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
        this.absolutePath = System.getProperty("user.dir") + File.separator + this.filename;
        // .. + "Serializable" + File.separator + this.filename;
    }

    @Override
    public SudokuBoard read() throws DaoFileException {
        SudokuBoard sudokuBoardInstance = null;

        try (FileInputStream fileIn = new FileInputStream(absolutePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            sudokuBoardInstance = (SudokuBoard) in.readObject();
            logger.debug("read file: " + absolutePath);
        } catch (FileNotFoundException e) {
            throw new DaoFileException(resourceBundle
                    .getObject("FileNotFoundException").toString(), e);
        } catch (IOException e) {
            throw new DaoFileException(resourceBundle
                    .getObject("IOException").toString(), e);
        } catch (ClassNotFoundException e) {
            throw new DaoFileException(resourceBundle
                    .getObject("ClassNotFoundException").toString(), e);
        }

        return sudokuBoardInstance;
    }

    @Override
    public void write(SudokuBoard sudokuBoardInstance) throws DaoFileException {

        try (FileOutputStream fileOut = new FileOutputStream(absolutePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(sudokuBoardInstance);
            logger.debug("write file: " + absolutePath);
        } catch (FileNotFoundException e) {
            throw new DaoFileException(resourceBundle
                    .getObject("FileNotFoundException").toString(), e);
        } catch (IOException e) {
            throw new DaoFileException(resourceBundle
                    .getObject("IOException").toString(), e);
        }
    }

    @Override
    public void finalize() throws Exception {
        new FileInputStream(absolutePath).close();
        this.close();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("filename ", filename)
                .append("asbolutePath ", absolutePath)
                .toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        FileSudokuBoardDao rhs = (FileSudokuBoardDao) obj;
        return new EqualsBuilder()
                .append(filename, rhs.filename)
                .append(absolutePath, rhs.absolutePath)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(filename)
                .append(absolutePath)
                .toHashCode();
    }

    @Override
    public void close() {
        //zasoby sa zamykane w instrukcjach try-with-resources
    }
}
