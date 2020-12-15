package com.mycompany.sudoku;

import java.io.Serializable;
import java.util.Observable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.platform.commons.util.ToStringBuilder;

public class SudokuField extends Observable implements Serializable, Cloneable {

    private int value;

    public SudokuField() {
        value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        //if (this.value != 0) {
        
        //}
        value = newValue;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("SudokuField", value)
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

        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder()
                .append(value, rhs.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

    @Override
    protected SudokuField clone() {
        SudokuField fieldClone = new SudokuField();
        fieldClone.setFieldValue(value);
        return fieldClone;
    }

}
