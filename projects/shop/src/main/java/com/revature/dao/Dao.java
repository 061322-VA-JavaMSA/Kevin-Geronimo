package com.revature.dao;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {
    public T get(int id);
    Collection<T> getAll();
    public T save(T t);
    boolean update(T t);
    boolean delete(int id);
}
