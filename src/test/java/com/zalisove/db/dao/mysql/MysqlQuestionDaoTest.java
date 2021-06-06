package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.QuestionDao;
import com.zalisove.db.entity.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.reset;

class MysqlQuestionDaoTest {
    private static final String SQL_CREATE_QUESTION = "insert into question values (default, ?,?,?)";
    private static final String SQL_FIND_QUESTION_BY_ID = "SELECT id,name,test_id,question_type_id from question where id = ?";
    private static final String SQL_FIND_QUESTIONS_BY_TEST_ID = "SELECT id,name,test_id,question_type_id from question where test_id = ?";

    QuestionDao questionDao = new MysqlQuestionDao();

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
    void createTest() throws SQLException {

        when(connection.prepareStatement(SQL_CREATE_QUESTION, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);


        Question actual = getQuestionModel();

        questionDao.create(connection,actual);

        Question expected = getQuestionModel();

        assertEquals(expected,actual);
    }



    @Test
    void readTest() throws SQLException {

        when(connection.prepareStatement(SQL_FIND_QUESTION_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.QUESTION__NAME)).thenReturn("name");
        when(resultSet.getLong(Fields.QUESTION__TEST__ID)).thenReturn(1L);
        when(resultSet.getInt(Fields.QUESTION__TYPE__ID)).thenReturn(1);

        Question actual = questionDao.read(connection, 1L).get();

        Question expected = getQuestionModel();

        assertEquals(expected,actual);
    }

    @Test
    void findQuestionByTestIdTest() throws SQLException {

        when(connection.prepareStatement(SQL_FIND_QUESTIONS_BY_TEST_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.QUESTION__NAME)).thenReturn("name");
        when(resultSet.getLong(Fields.QUESTION__TEST__ID)).thenReturn(1L);
        when(resultSet.getInt(Fields.QUESTION__TYPE__ID)).thenReturn(1);

        List<Question> actual = questionDao.findQuestionByTestId(connection, 1L);

        List<Question> expected = new ArrayList<>();
        expected.add(getQuestionModel());

        assertEquals(expected,actual);
    }


    private Question getQuestionModel() {
        Question question = new Question();

        question.setId(1L);
        question.setQuestionTypeId(1);
        question.setName("name");
        question.setTestId(1L);
        return question;
    }
}