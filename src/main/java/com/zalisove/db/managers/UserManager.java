package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * Class that implements complex queries to User
 * @author O.S.Kyrychenko
 */
public class UserManager implements CRUD<User,Long> {


    private static final Logger LOG = LogManager.getLogger(UserManager.class);


    private static UserManager instance;


    private UserManager() { }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    /**
     * Create User.
     *
     * @param entity user to create.
     *
     * @throws ManagerException when throws SQLException
     */
    @Override
    public void create(User entity) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getInstance().getConnection();
            DAOFactory.getInstance().getUserDao().create(con, entity);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no create User", throwables);
        }
    }

    /**
     * Read User.
     *
     * @param primaryKey user identifier.
     *
     * @return <code>Optional<User><code/> user
     * @throws ManagerException when throws SQLException
     */
    @Override
    public Optional<User> read(Long primaryKey) throws ManagerException {
        Optional<User> entity;
        Connection con = null;
        try {
            con = DAOFactory.getInstance().getConnection();
            entity = DAOFactory.getInstance().getUserDao().read(con, primaryKey);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no read User", throwables);
        }
        return entity;
    }

    /**
     * Update User.
     *
     * @param entity user to update.
     *
     * @throws ManagerException when throws SQLException
     */
    @Override
    public void update(User entity) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getInstance().getConnection();
            DAOFactory.getInstance().getUserDao().update(con, entity);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no update User", throwables);
        }
    }

    /**
     * Delete User.
     *
     * @param primaryKey user identifier.
     *
     * @throws ManagerException when throws SQLException
     */
    @Override
    public void delete(Long primaryKey) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getInstance().getConnection();
            DAOFactory.getInstance().getUserDao().delete(con, primaryKey);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no update User", throwables);
        }
    }

    /**
     * Search User by email and password.
     *
     * @param  email user email.
     * @param  password user password.
     * @return <code>Optional<User><code/> user
     * @throws ManagerException when throws SQLException
     */
    public Optional<User> searchUserByEmailAndPassword(String email, String password) throws ManagerException {
        Optional<User> entity;
        Connection con = null;
        try {
            con = DAOFactory.getInstance().getConnection();
            entity = DAOFactory.getInstance().getUserDao().searchUserByEmailAndPassword(con, email,password);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no search User", throwables);
        }
        return entity;
    }

    /**
     * Get count of user
     *
     * @return <code>Long</code> count of user.
     *
     * @throws ManagerException when throws SQLException
     */
    public long getCountRecords() throws ManagerException{
        Connection con = null;
        long countRecords;
        try {
            con = DAOFactory.getInstance().getConnection();
            countRecords = DAOFactory.getInstance().getUserDao().getCountRecords(con);
            DAOFactory.commitAndClose(con);
            return countRecords;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get count records", throwables);
        }

    }

    /**
     * Get a certain number of users from a position in the table
     * @param quantity number user
     * @param from position in table
     *
     * @return <code>List<User></code> users.
     * @throws ManagerException when throws SQLException
     */
    public List<User> getUsers(Long quantity, Long from) throws ManagerException {
        List<User> users;
        Connection con = null;
        try {
            con = DAOFactory.getInstance().getConnection();
            users = DAOFactory.getInstance().getUserDao().getUsers(con, quantity, from);
            DAOFactory.commitAndClose(con);
            return users;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get user quantity from", throwables);
        }
    }
}