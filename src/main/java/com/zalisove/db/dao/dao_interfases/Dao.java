package com.zalisove.db.dao.dao_interfases;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Optional;

/**
 * Defines a general contract for CRUD
 * @author O.S.Kyrychenko
 */
public interface Dao <T,PK extends Serializable>{

    /**
     * Create entity.
     *
     * @param con connection on which the operation will be performed
     * @param entity entity to create.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    void create(Connection con, T entity) throws SQLException;

    /**
     * Returns a entity with the given identifier.
     *
     * @param primaryKey entity identifier.
     * @param con connection on which the operation will be performed
     * @return Optional<entity>.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    Optional<T> read(Connection con, PK primaryKey) throws SQLException;

    /**
     * Update entity.
     *
     * @param entity entity to update.
     * @param con connection on which the operation will be performed
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    void update(Connection con,T entity) throws SQLException;

    /**
     * Delete entity with the given identifier.
     *
     * @param primaryKey entity identifier.
     * @param con connection on which the operation will be performed
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    void delete(Connection con,PK primaryKey) throws SQLException;
}
