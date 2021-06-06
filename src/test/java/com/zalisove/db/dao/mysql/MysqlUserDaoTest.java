package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.AnswerDao;
import com.zalisove.db.dao.dao_interfases.UserDao;
import com.zalisove.db.entity.Answer;
import com.zalisove.db.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class MysqlUserDaoTest {

    private static final String SQL_FIND_USER_BY_ID = "SELECT id,email,password,name,surname,roles_id, is_blocked from user where id = ?";
    private static final String SQL_FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT id,email,password,name,surname,roles_id, is_blocked from user where email = ? and password = ?";
    private static final String SQL_CREATE_USER = "insert into user values (default, ?,?,?,?,?,?);";
    private static final String SQL_COUNT_RECORDS_USER = "select count(*) from user";
    private static final String SQL_GET_USERS_QUANTITY_FROM = "SELECT id,email,password,name,surname,roles_id, is_blocked from user limit ? offset ?";


    UserDao userDao = new MysqlUserDao();


    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    @BeforeAll
    public static void beforeClass(){
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
    }

    @BeforeEach
    void setUp() {
        reset(connection);
        reset(preparedStatement);
        reset(resultSet);
    }
    @Test
    void create() throws SQLException {
        when(connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);

        User actual = getUserModel();

        userDao.create(connection,actual);

        User expected = getUserModel();

        assertEquals(expected,actual);
    }

    @Test
    void read() throws SQLException {
        when(connection.prepareStatement(SQL_FIND_USER_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.USER__EMAIL)).thenReturn("email@gmail.com");
        when(resultSet.getString(Fields.USER__PASSWORD)).thenReturn("12345678");
        when(resultSet.getString(Fields.USER__NAME)).thenReturn("name");
        when(resultSet.getString(Fields.USER__SURNAME)).thenReturn("surname");
        when(resultSet.getInt(Fields.USER__ROLES_ID)).thenReturn(1);
        when(resultSet.getBoolean(Fields.USER__IS_BLOCKED)).thenReturn(false);

        User actual = userDao.read(connection,1L).get();

        User expected = getUserModel();

        assertEquals(expected,actual);
    }

    @Test
    void searchUserByEmailAndPassword() throws SQLException {

        when(connection.prepareStatement(SQL_FIND_USER_BY_EMAIL_AND_PASSWORD)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.USER__EMAIL)).thenReturn("email@gmail.com");
        when(resultSet.getString(Fields.USER__PASSWORD)).thenReturn("12345678");
        when(resultSet.getString(Fields.USER__NAME)).thenReturn("name");
        when(resultSet.getString(Fields.USER__SURNAME)).thenReturn("surname");
        when(resultSet.getInt(Fields.USER__ROLES_ID)).thenReturn(1);
        when(resultSet.getBoolean(Fields.USER__IS_BLOCKED)).thenReturn(false);

        User actual = userDao.searchUserByEmailAndPassword(connection,"email@gmail.com","12345678").get();

        User expected = getUserModel();

        assertEquals(expected,actual);
    }

    @Test
    void getCountRecords() throws SQLException {
        when(connection.prepareStatement(SQL_COUNT_RECORDS_USER)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);
        Long actual = userDao.getCountRecords(connection);
        assertEquals(1L,actual);
    }

    @Test
    void getUsers() throws SQLException {
        when(connection.prepareStatement(SQL_GET_USERS_QUANTITY_FROM)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.USER__EMAIL)).thenReturn("email@gmail.com");
        when(resultSet.getString(Fields.USER__PASSWORD)).thenReturn("12345678");
        when(resultSet.getString(Fields.USER__NAME)).thenReturn("name");
        when(resultSet.getString(Fields.USER__SURNAME)).thenReturn("surname");
        when(resultSet.getInt(Fields.USER__ROLES_ID)).thenReturn(1);
        when(resultSet.getBoolean(Fields.USER__IS_BLOCKED)).thenReturn(false);

        List<User> actual = userDao.getUsers(connection,1L,1L);

        List<User> expected = new ArrayList<>();
        expected.add(getUserModel());

        assertEquals(expected,actual);
    }

    private User getUserModel() {
        User user = new User();
        user.setPassword("12345678");
        user.setName("name");
        user.setBlocked(false);
        user.setSurname("surname");
        user.setId(1L);
        user.setRoleId(1);
        user.setEmail("email@gmail.com");
        return user;
    }

}