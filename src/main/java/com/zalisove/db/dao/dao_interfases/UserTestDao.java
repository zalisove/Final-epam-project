package com.zalisove.db.dao.dao_interfases;

import com.zalisove.db.entity.UserTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;
import java.util.Optional;

/**
 * Defines a general contract for means of access to an object UserTest
 * @author O.S.Kyrychenko
 */
public interface UserTestDao{

    /**
     * Create userTest.
     *
     * @param con connection on which the operation will be performed
     * @param userTest userTest to create.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    void create (Connection con, UserTest userTest) throws SQLException;


    /**
     * Update userTest.
     *
     * @param con connection on which the operation will be performed
     * @param userTest userTest to update.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    void update (Connection con, UserTest userTest) throws SQLException;

    /**
     * Returns a userTest with the given identifiers.
     *
     * @param userId user identifier.
     * @param testId test identifier.
     * @param con connection on which the operation will be performed
     * @return <code>Optional<UserTest></code>.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    Optional<UserTest> read (Connection con, long userId, long testId) throws SQLException;


    /**
     * Get all userTest by user identifier
     *
     * @param id user identifier.
     * @param con connection on which the operation will be performed
     * @return <code>List<UserTest></code> userTests.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<UserTest> getAllTestByUser(Connection con, long id) throws SQLException;
}
