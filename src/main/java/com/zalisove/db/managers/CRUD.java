package com.zalisove.db.managers;

import java.util.Optional;

/**
 * Defines a general contract for CRUD
 * @author O.S.Kyrychenko
 */
public interface CRUD <T,PK> {
    void create(T entity) throws ManagerException;
    Optional<T> read(PK primaryKey) throws ManagerException;
    void update(T entity) throws ManagerException;
    void delete(PK primaryKey) throws ManagerException;
}
