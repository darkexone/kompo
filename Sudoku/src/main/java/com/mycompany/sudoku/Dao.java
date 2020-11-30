package com.mycompany.sudoku;

public interface Dao<T> {

    public T read() throws Throwable;

    public void write(T obj) throws Throwable;

}
