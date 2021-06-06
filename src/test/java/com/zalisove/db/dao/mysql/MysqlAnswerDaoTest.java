package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.AnswerDao;
import com.zalisove.db.entity.Answer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MysqlAnswerDaoTest {

    private static final String SQL_CREATE_ANSWER = "insert into answer values (default, ?,?,?)";
    private static final String SQL_FIND_ANSWER_BY_ID = "SELECT id,name,true_answer,question_id from answer where id = ?";
    private static final String SQL_FIND_ANSWER_BY_QUESTION__ID = "SELECT id,name,true_answer,question_id from answer where question_id = ?";
    private static final String SQL_FIND_RIGHT_ANSWER_BY_QUESTION__ID = "SELECT id,name,true_answer,question_id from answer where question_id = ? and true_answer = true";


    AnswerDao answerDao = new MysqlAnswerDao();


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
    void readTest() throws SQLException {

        when(connection.prepareStatement(SQL_FIND_ANSWER_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.ANSWER__NAME)).thenReturn("name");
        when(resultSet.getBoolean(Fields.ANSWER_TRUE)).thenReturn(true);
        when(resultSet.getInt(Fields.ANSWER__QUESTION_ID)).thenReturn(1);


        Answer actual = answerDao.read(connection,1L).get();

        Answer expected = getAnswerModel();

        assertEquals(expected,actual);
    }

    @Test
    void createTest() throws SQLException {

        when(connection.prepareStatement(SQL_CREATE_ANSWER, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);

        Answer actual = getAnswerModel();

        answerDao.create(connection,actual);

        Answer expected = getAnswerModel();

        assertEquals(expected,actual);
    }

    @Test
    void findAnswerByQuestionIdTest() throws SQLException {

        when(connection.prepareStatement(SQL_FIND_ANSWER_BY_QUESTION__ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.ANSWER__NAME)).thenReturn("name");
        when(resultSet.getBoolean(Fields.ANSWER_TRUE)).thenReturn(true);
        when(resultSet.getInt(Fields.ANSWER__QUESTION_ID)).thenReturn(1);

        List<Answer> actual = answerDao.findAnswerByQuestionId(connection,1L);

        List<Answer> expected = new ArrayList<>();
        expected.add(getAnswerModel());

        assertEquals(expected,actual);
    }
    @Test
    void findRightAnswerByQuestionIdTest() throws SQLException {

        when(connection.prepareStatement(SQL_FIND_RIGHT_ANSWER_BY_QUESTION__ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.ANSWER__NAME)).thenReturn("name");
        when(resultSet.getBoolean(Fields.ANSWER_TRUE)).thenReturn(true);
        when(resultSet.getInt(Fields.ANSWER__QUESTION_ID)).thenReturn(1);

        List<Answer> actual = answerDao.findRightAnswerByQuestionId(connection,1L);

        List<Answer> expected = new ArrayList<>();
        expected.add(getAnswerModel());

        assertEquals(expected,actual);
    }

    private Answer getAnswerModel() {
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setName("name");
        answer.setTrueAnswer(true);
        answer.setQuestionId(1);
        return answer;
    }
}