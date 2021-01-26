package pl.comp.model;

import pl.comp.model.exceptions.DaoException;

public interface Dao<T> {

    public T read() throws DaoException;

    public void write(T obj) throws DaoException;

}
