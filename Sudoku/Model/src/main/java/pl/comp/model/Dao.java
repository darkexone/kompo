package pl.comp.model;

import pl.comp.model.exceptions.DaoFileException;

public interface Dao<T> {

    public T read() throws DaoFileException;

    public void write(T obj) throws DaoFileException;

}
