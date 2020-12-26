package pl.comp.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.platform.commons.util.ToStringBuilder;

public class SudokuBox implements Cloneable {

    private final SudokuField[] box = new SudokuField[9];

    public SudokuBox(SudokuField[] field) {

        for (int i = 0; i < 9; i++) {
            box[i] = new SudokuField();
            box[i].setFieldValue(field[i].getFieldValue());
            }
        }

    public boolean verify() {

        //3x3
        int sum = 0;

        int [] temp = new int[10];

        for (int i : temp) {
            temp[i] = 0;
        }
        
        for (int i = 0; i < 9; i++) {
                sum += box[i].getFieldValue();
                temp[box[i].getFieldValue()]++;
            }

        for (int i = 1; i < 10; i++) {
         if (temp[i] > 1) {
           return false;
         }
        }



       
        
        //if (sum != 45) {
        //    System.out.print(sum);
        //   return false;
        //}

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("SudokuBox", box)
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

        SudokuBox rhs = (SudokuBox) obj;
        return new EqualsBuilder()
                .append(box, rhs.box)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(box)
                .toHashCode();
    }

    @Override
    protected SudokuBox clone() throws CloneNotSupportedException {
        return (SudokuBox) super.clone();

    }

    //TODO czy tak moze byc?
    public List<SudokuField> getList() {
        return Collections.unmodifiableList(Arrays.asList(box.clone()));
    }
}