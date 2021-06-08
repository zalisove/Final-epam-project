package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.SubjectDao;
import com.zalisove.db.dao.dao_interfases.UserTestDao;
import com.zalisove.db.entity.UserTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testDao.TestDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserTestManagerTest {


    static UserTestManager userTestManager;
    static TestDAOFactory daoFactory;
    static UserTestDao userTestDao;

    @BeforeAll
    static void beforeAll() {
        DAOFactory.setDaoFactoryFCN(TestDAOFactory.class.getName());
        daoFactory = (TestDAOFactory) DAOFactory.getInstance();
        userTestManager = UserTestManager.getInstance();
        userTestDao = mock(UserTestDao.class);

        daoFactory.setConnection(mock(Connection.class));
    }

    @BeforeEach
    void setUp() {
        reset(userTestDao);
    }
    @Test
    void create() throws ManagerException, SQLException {
        daoFactory.setTestUserTestDao(userTestDao);
        userTestManager.create(getUserTestModel(1));
        verify(userTestDao, times(1)).create(any(), any());
    }

    @Test
    void update() throws ManagerException, SQLException {
        daoFactory.setTestUserTestDao(userTestDao);
        userTestManager.update(getUserTestModel(1));
        verify(userTestDao, times(1)).update(any(), any());
    }

    @Test
    void read() throws ManagerException, SQLException {

        Optional<UserTest> testData = Optional.of(getUserTestModel(1));
        when(userTestDao.read(any(Connection.class), any(long.class),any(long.class))).thenReturn(testData);
        daoFactory.setTestUserTestDao(userTestDao);
        userTestManager.read(1,1L);
        verify(userTestDao, times(1)).read(any(Connection.class), any(long.class),any(long.class));
    }

    @Test
    void createOrUpdateCreateTest() throws SQLException, ManagerException {
        daoFactory.setTestUserTestDao(userTestDao);
        userTestManager.createOrUpdate(getUserTestModel(1));
        verify(userTestDao, times(1)).create(any(), any());
    }
    @Test
    void createOrUpdateUpdateTest() throws SQLException, ManagerException {
        daoFactory.setTestUserTestDao(userTestDao);
        doThrow(SQLException.class).when(userTestDao).create(any(),any());
        userTestManager.createOrUpdate(getUserTestModel(1));
        verify(userTestDao, times(1)).create(any(), any());
    }




    @Test
    void createThrowsException() throws  SQLException {
        daoFactory.setTestUserTestDao(userTestDao);
        doThrow(SQLException.class).when(userTestDao).create(any(),any());
        assertThrows(ManagerException.class,() ->userTestManager.create(getUserTestModel(1)));
    }

    @Test
    void updateThrowsException() throws SQLException {
        daoFactory.setTestUserTestDao(userTestDao);
        doThrow(SQLException.class).when(userTestDao).update(any(),any());
        assertThrows(ManagerException.class,() ->userTestManager.update(getUserTestModel(1)));
    }

    @Test
    void readThrowsException() throws  SQLException {
        when(userTestDao.read(any(Connection.class), any(long.class),any(long.class))).thenThrow(SQLException.class);
        daoFactory.setTestUserTestDao(userTestDao);
        assertThrows(ManagerException.class,() ->userTestManager.read(1,1));

    }

    @Test
    void createOrUpdateThrowsExceptionTest() throws SQLException {
        doThrow(SQLException.class).when(userTestDao).update(any(),any());
        doThrow(SQLException.class).when(userTestDao).create(any(),any());
        daoFactory.setTestUserTestDao(userTestDao);
        assertThrows(ManagerException.class,() ->userTestManager.createOrUpdate(getUserTestModel(1)));

    }


    private UserTest getUserTestModel(long id) {
        UserTest userTest = new UserTest();
        userTest.setTestId(id);
        userTest.setMark(100);
        userTest.setUserId(1);
        return userTest;
    }
}