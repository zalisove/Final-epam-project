package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.entity.UserTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


/**
 * Class that implements complex queries to UserTest
 * @author O.S.Kyrychenko
 */
public class UserTestManager {

    private static UserTestManager instance;
    private static final Logger LOG = LogManager.getLogger(UserTestManager.class);
    private UserTestManager() { }

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
            con = DAOFactory.getInstance().getConnection();
            DAOFactory.getInstance().getUserTestDao().create(con,userTest);
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
            con = DAOFactory.getInstance().getConnection();
            DAOFactory.getInstance().getUserTestDao().update(con,userTest);
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
            con = DAOFactory.getInstance().getConnection();
            userTest = DAOFactory.getInstance().getUserTestDao().read(con,userId,testId).orElseThrow(()-> new ManagerException("No read user_test"));
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
            con = DAOFactory.getInstance().getConnection();
            try {
                DAOFactory.getInstance().getUserTestDao().create(con,userTest);
            }catch (SQLException e){
                LOG.info("no create trying update");
                DAOFactory.getInstance().getUserTestDao().update(con,userTest);
            }
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error("no create or update user_test",throwables);
            throw new ManagerException("no create or update user_test",throwables);
        }
    }


    public List<UserTest> getAllUserTestByDateFromTo(Date from, Date to)throws ManagerException{
        Connection con = null;
        List<UserTest> userTest;
        try {
            con = DAOFactory.getInstance().getConnection();
            userTest = DAOFactory.getInstance().getUserTestDao().getAllUserTestByDateFromTo(con,from,to);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error("no read user_test by date",throwables);
            throw new ManagerException("no read user_test by date",throwables);
        }
        return userTest;
    }

}
