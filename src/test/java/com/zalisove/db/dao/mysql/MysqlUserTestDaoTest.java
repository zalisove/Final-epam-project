package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.UserDao;
import com.zalisove.db.dao.dao_interfases.UserTestDao;
import com.zalisove.db.entity.User;
import com.zalisove.db.entity.UserTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MysqlUserTestDaoTest {

    private static final String SQL_CREATE_USER_TEST = "insert into user_has_test values (?, ?, ?);";
    private static final String SQL_FIND_USER_TEST_BY_IDS = "SELECT user_id, test_id, mark  from user_has_test where user_id  = ? and test_id = ?";
    private static final String SQL_FIND_USER_TEST_BY_USER_ID = "SELECT user_id, test_id, mark  from user_has_test where user_id  = ?";



    UserTestDao userTestDao = new MysqlUserTestDao();


    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    @BeforeAll
    public static void beforeClass(){
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
    }
    @Test
    void create() throws SQLException {
        when(connection.prepareStatement(SQL_CREATE_USER_TEST)).thenReturn(preparedStatement);

        UserTest actual = getUserTestModel();

        userTestDao.create(connection,actual);

        UserTest expected = getUserTestModel();

        assertEquals(expected,actual);
    }

    @Test
    void read() throws SQLException {
        when(connection.prepareStatement(SQL_FIND_USER_TEST_BY_IDS)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.USER_TEST__USER_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.USER_TEST__TEST_ID)).thenReturn(1L);
        when(resultSet.getDouble(Fields.USER_TEST__MARK)).thenReturn(100D);

        UserTest actual = userTestDao.read(connection,1L,1L).get();
        UserTest expected = getUserTestModel();

        assertEquals(expected,actual);
    }

    @Test
    void getAllTestByUser() throws SQLException {
        when(connection.prepareStatement(SQL_FIND_USER_TEST_BY_USER_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.USER_TEST__USER_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.USER_TEST__TEST_ID)).thenReturn(1L);
        when(resultSet.getDouble(Fields.USER_TEST__MARK)).thenReturn(100D);

        List<UserTest> actual = userTestDao.getAllTestByUser(connection,1L);
        List<UserTest> expected = new ArrayList<>();
        expected.add(getUserTestModel());

        assertEquals(expected,actual);
    }

    private UserTest getUserTestModel() {
        UserTest userTest = new UserTest();
        userTest.setTestId(1);
        userTest.setMark(100);
        userTest.setUserId(1);
        return userTest;
    }
}