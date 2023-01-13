package com.smoothstack.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    void insert(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(int id) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}

