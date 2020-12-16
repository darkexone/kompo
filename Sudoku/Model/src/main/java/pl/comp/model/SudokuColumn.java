package pl.comp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.platform.commons.util.ToStringBuilder;

public class SudokuColumn implements Cloneable {

    private SudokuField[] column = new SudokuField[9];

    public SudokuColumn(SudokuField[] field) {

        for (int i = 0; i < 9; i++) {
            column[i] = new SudokuField();
        }

        for (int i = 0; i < 9; i++) {
            column[i].setFieldValue(field[i].getFieldValue());
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
                sum += column[i].getFieldValue();
                temp[column[i].getFieldValue()]++;
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
                .append("column", column)
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

        SudokuColumn rhs = (SudokuColumn) obj;
        return new EqualsBuilder()
                .append(column, rhs.column)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(column)
                .toHashCode();
    }

    @Override
    protected SudokuColumn clone() {
        try {
            return (SudokuColumn) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(this.getClass().getName()
                    + " nie implementuje Cloneable...");
            return null;
        }
    }

}
