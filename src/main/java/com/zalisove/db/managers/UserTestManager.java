package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.UserTestDao;
import com.zalisove.db.entity.UserTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Class that implements complex queries to UserTest
 * @author O.S.Kyrychenko
 */
public class UserTestManager {

    private static UserTestManager instance;
    private UserTestDao userTestDao;
    private static final Logger LOG = LogManager.getLogger(UserTestManager.class);
    private UserTestManager() {
        userTestDao = DAOFactory.getInstance().getUserTestDao();
    }

    public static UserTestManager getInstance() {
        if (instance == null){
            instance = new UserTestManager();
        }
        return instance;
    }


    /**
     * Create UserTest.
     *
     * @param userTest userTest to create.
     *
     * @throws ManagerException when throws SQLException
     */
    public void create(UserTest userTest)throws ManagerException{
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            userTestDao.create(con,userTest);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error("no create user_test",throwables);
            throw new ManagerException("no create user_test",throwables);
        }
    }

    /**
     * Update UserTest.
     *
     * @param userTest userTest to update.
     *
     * @throws ManagerException when throws SQLException
     */
    public void update(UserTest userTest)throws ManagerException{
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            userTestDao.update(con,userTest);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error("no update user_test",throwables);
            throw new ManagerException("no update user_test",throwables);
        }
    }

    /**
     * Read UserTest.
     *
     * @param userId user identifier.
     * @param testId test identifier.
     *
     * @return <code>UserTest<code/> user
     * @throws ManagerException when throws SQLException
     */
    public UserTest read(long userId, long testId)throws ManagerException{
        Connection con = null;
        UserTest userTest;
        try {
            con = DAOFactory.getConnection();
            userTest = userTestDao.read(con,userId,testId).orElseThrow(()-> new ManagerException("No read user_test"));
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error("no read user_test",throwables);
            throw new ManagerException("no read user_test",throwables);
        }
        return userTest;
    }

    /**
     * Create userTest or Update user test
     *
     * @param userTest userTest.
     *
     * @throws ManagerException when throws SQLException
     */
    public void createOrUpdate(UserTest userTest) throws ManagerException{
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            try {
                userTestDao.create(con,userTest);
            }catch (SQLException e){
                LOG.info("no create trying update");
                userTestDao.update(con,userTest);
            }
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error("no create or update user_test",throwables);
            throw new ManagerException("no create or update user_test",throwables);
        }
    }


}
