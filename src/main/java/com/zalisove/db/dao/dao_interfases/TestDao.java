package com.zalisove.db.dao.dao_interfases;

import com.zalisove.db.entity.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;

/**
 * Defines a general contract for means of access to an object Test
 * @author O.S.Kyrychenko
 */

public interface TestDao extends Dao<Test,Long>{

    /**
     * Get all test
     *
     * @param con connection on which the operation will be performed
     *
     * @return <code>List<Answer></code> tests.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Test> getAllTest(Connection con) throws SQLException;

    /**
     * Get a certain number of tests from a position in the table
     *
     * @param con connection on which the operation will be performed
     * @param quantity number tests
     * @param from position in table
     *
     * @return <code>List<Test></code> tests.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Test> getAllTest(Connection con, Long quantity, Long from) throws SQLException;

    /**
     * Get a certain number of tests from a position in the table that relate to a specific subject
     *
     * @param con connection on which the operation will be performed
     * @param quantity number tests
     * @param from position in table
     * @param subjectId identifier subject
     *
     * @return <code>List<Test></code> tests.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Test> getAllTest(Connection con, long subjectId ,Long quantity, Long from) throws SQLException;

    /**
     * Get a certain number of tests from a position in the table that sorted by param orderBy
     *
     * @param con connection on which the operation will be performed
     * @param quantity number tests
     * @param from position in table
     * @param orderBy sort param
     * @param revers true - revers order false - straight order
     *
     * @return <code>List<Test></code> tests.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Test> getAllTest(Connection con, String orderBy, Long quantity, Long from,boolean revers) throws SQLException;


    /**
     * Get a certain number of tests from a position in the table that relate to a specific subject sorted by param orderBy
     *
     * @param con connection on which the operation will be performed
     * @param quantity number tests
     * @param from position in table
     * @param orderBy sort param
     * @param revers true - revers order false - straight order
     * @param subjectId identifier subject
     *
     * @return <code>List<Test></code> tests.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Test> getAllTest(Connection con, String orderBy,long subjectId , Long quantity, Long from,boolean revers) throws SQLException;


    /**
     * Get count of test
     *
     * @param con connection on which the operation will be performed
     *
     * @return <code>Long</code> count of test.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    Long countRecords(Connection con) throws SQLException;

    /**
     * Get count of test that relate to a specific subject
     *
     * @param con connection on which the operation will be performed
     * @param subjectId identifier subject
     *
     * @return <code>Long</code> count of test.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    Long countRecords(Connection con, long subjectId) throws SQLException;
}
