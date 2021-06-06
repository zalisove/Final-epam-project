package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.SubjectDao;
import com.zalisove.db.entity.Question;
import com.zalisove.db.entity.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class MysqlSubjectDaoTest {

    private static final String SQL_CREATE_SUBJECT= "insert into subject values (default, ?)";
    private static final String SQL_FIND_SUBJECT_BY_ID = "SELECT id,name from subject where id = ?";
    private static final String SQL_GET_All_SUBJECT = "SELECT id,name from subject";

    SubjectDao subjectDao = new MysqlSubjectDao();

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
        when(connection.prepareStatement(SQL_CREATE_SUBJECT, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);


        Subject actual = getSubjectModel();

        subjectDao.create(connection,actual);

        Subject expected = getSubjectModel();

        assertEquals(expected,actual);
    }

    @Test
    void read() throws SQLException {

        when(connection.prepareStatement(SQL_FIND_SUBJECT_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.SUBJECT__NAME)).thenReturn("name");


        Subject actual = subjectDao.read(connection,1L).get();

        Subject expected = getSubjectModel();

        assertEquals(expected,actual);
    }

    @Test
    void getAllSubject() throws SQLException {

        when(connection.prepareStatement(SQL_GET_All_SUBJECT)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.SUBJECT__NAME)).thenReturn("name");


        List<Subject> actual = subjectDao.getAllSubject(connection);

        List<Subject> expected = new ArrayList<>();
        expected.add(getSubjectModel());

        assertEquals(expected,actual);
    }

    private Subject getSubjectModel() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("name");
        return subject;
    }


}