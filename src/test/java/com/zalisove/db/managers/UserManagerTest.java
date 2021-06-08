package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.UserDao;
import com.zalisove.db.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testDao.TestDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserManagerTest {

    static UserManager userManager;
    static TestDAOFactory daoFactory;
    static UserDao userDao;

    @BeforeAll
    static void beforeAll() {
        DAOFactory.setDaoFactoryFCN(TestDAOFactory.class.getName());
        daoFactory = (TestDAOFactory) DAOFactory.getInstance();
        userManager = UserManager.getInstance();
        userDao = mock(UserDao.class);

        daoFactory.setConnection(mock(Connection.class));
    }

    @BeforeEach
    void setUp() {
        reset(userDao);
    }

    @Test
    void create() throws ManagerException, SQLException {
        daoFactory.setTestUserDao(userDao);
        userManager.create(getUserModel(1));
        verify(userDao, times(1)).create(any(), any());
    }

    @Test
    void read() throws ManagerException, SQLException {
        Optional<User> testData = Optional.of(getUserModel(1));
        when(userDao.read(any(Connection.class), any(long.class))).thenReturn(testData);
        daoFactory.setTestUserDao(userDao);
        userManager.read(1L);
        verify(userDao, times(1)).read(any(Connection.class), any(long.class));
    }

    @Test
    void update() throws ManagerException, SQLException {
        daoFactory.setTestUserDao(userDao);
        userManager.update(getUserModel(1));
        verify(userDao, times(1)).update(any(), any());
    }

    @Test
    void delete() throws ManagerException, SQLException {
        daoFactory.setTestUserDao(userDao);
        userManager.delete(1L);
        verify(userDao, times(1)).delete(any(), any());
    }

    @Test
    void searchUserByEmailAndPassword() throws SQLException, ManagerException {
        Optional<User> testData = Optional.of(getUserModel(1));
        when(userDao.searchUserByEmailAndPassword(any(), any(),any())).thenReturn(testData);
        daoFactory.setTestUserDao(userDao);
        userManager.searchUserByEmailAndPassword("","");
        verify(userDao, times(1)).searchUserByEmailAndPassword(any(), any(),any());
    }

    @Test
    void getCountRecords() throws SQLException, ManagerException {
        when(userDao.getCountRecords(any())).thenReturn(2L);
        daoFactory.setTestUserDao(userDao);
        userManager.getCountRecords();
        verify(userDao, times(1)).getCountRecords(any());
    }

    @Test
    void getUsers() throws SQLException, ManagerException {
        List<User> testData = new ArrayList<>();
        testData.add(getUserModel(1));
        testData.add(getUserModel(2));
        testData.add(getUserModel(3));
        when(userDao.getUsers(any(), any(),any())).thenReturn(testData);
        daoFactory.setTestUserDao(userDao);
        userManager.getUsers(1L,1L);
        verify(userDao, times(1)).getUsers(any(), any(),any());
    }


    private User getUserModel(long id) {
        User user = new User();
        user.setPassword("12345678");
        user.setName("name");
        user.setBlocked(false);
        user.setSurname("surname");
        user.setId(id);
        user.setRoleId(1);
        user.setEmail("email@gmail.com");
        return user;
    }
}