package org.example.Dao;

import org.example.Dao.Exceptions.DaoException;

import java.util.List;

public interface DaoBase<K, T> {
    List<T> findAll() throws DaoException;
    T findById(K id) throws DaoException;
    void update(K id, T entity) throws DaoException;
    void delete(K id) throws DaoException;
    void insert(T entity) throws DaoException;
}
