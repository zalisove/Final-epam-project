package com.zalisove.db.dao.dao_interfases;

import com.zalisove.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines a general contract for means of access to an object User
 * @author O.S.Kyrychenko
 */
public interface UserDao extends Dao<User,Long>{

    /**
     * Returns a user with the given email and password.
     *
     * @param email User email.
     * @param password User password
     * @param con connection on which the operation will be performed
     * @return <code>Optional<User></code> user.
     */
    Optional<User> searchUserByEmailAndPassword(Connection con, String email, String password) throws SQLException;

    /**
     * Get count of users
     *
     * @param con connection on which the operation will be performed
     * @return count of users
     */
    long getCountRecords(Connection con) throws SQLException;

    /**
     * Get a certain number of users from a position in the table
     *
     * @param con connection on which the operation will be performed
     * @return a <code>List<User></code> object that contains the users
     */
    List<User> getUsers(Connection con, Long quantity, Long from) throws SQLException;
}
