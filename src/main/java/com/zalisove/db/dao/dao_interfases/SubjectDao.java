package com.zalisove.db.dao.dao_interfases;

import com.zalisove.db.entity.Subject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;

/**
 * Defines a general contract for means of access to an object Subject
 * @author O.S.Kyrychenko
 */
public interface SubjectDao extends Dao<Subject, Long> {

    /**
     * Get all subject
     *
     * @param con connection on which the operation will be performed
     *
     * @return <code>List<Subject></code> subjects.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Subject> getAllSubject(Connection con) throws SQLException;
}
