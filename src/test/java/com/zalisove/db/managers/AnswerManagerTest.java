package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.AnswerDao;
import com.zalisove.db.entity.Answer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testDao.TestDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AnswerManagerTest {

    static AnswerManager answerManager;
    static TestDAOFactory daoFactory;
    @BeforeAll
    static void beforeAll() {
        DAOFactory.setDaoFactoryFCN(TestDAOFactory.class.getName());
        daoFactory = (TestDAOFactory)DAOFactory.getInstance();
        answerManager = AnswerManager.getInstance();
    }

    @Test
    void createAnswersTest() throws ManagerException, SQLException {
        Connection connection = mock(Connection.class);
        List<Answer> expected = new ArrayList<>();

        AnswerDao answerDao = mock(AnswerDao.class);

        daoFactory.setTestAnswerDao(answerDao);

        expected.add(getAnswerModel(1));
        expected.add(getAnswerModel(2));
        answerManager.createAnswers(connection,expected);

        verify(answerDao,times(2)).create(any(),any());
    }
    @Test
    void createAnswersThrowsExceptionTest() throws SQLException {
        Connection connection = mock(Connection.class);

        List<Answer> expected = new ArrayList<>();
        expected.add(getAnswerModel(1));
        expected.add(getAnswerModel(2));

        AnswerDao answerDao = mock(AnswerDao.class);
        doThrow(SQLException.class).when(answerDao).create(any(),any());
        daoFactory.setTestAnswerDao(answerDao);

        assertThrows(ManagerException.class,()->answerManager.createAnswers(connection,expected));

    }

    @Test
    void updateAnswersCreateTest() throws ManagerException, SQLException {
        Connection connection = mock(Connection.class);

        List<Answer> expected = new ArrayList<>();
        expected.add(getAnswerModel(1));
        expected.add(getAnswerModel(2));
        expected.add(getAnswerModel(5));

        List<Answer> oldTestData = new ArrayList<>();

        AnswerDao answerDao = mock(AnswerDao.class);

        when(answerDao.findAnswerByQuestionId(any(),any())).thenReturn(oldTestData);

        daoFactory.setTestAnswerDao(answerDao);
        answerManager.updateAnswers(connection,expected);

        verify(answerDao,times(3)).create(any(),any());
    }

    @Test
    void updateAnswersIsEmptyDataTest() throws ManagerException, SQLException {
        Connection connection = mock(Connection.class);

        List<Answer> expected = new ArrayList<>();

        List<Answer> oldTestData = new ArrayList<>();

        AnswerDao answerDao = mock(AnswerDao.class);

        when(answerDao.findAnswerByQuestionId(any(),any())).thenReturn(oldTestData);

        daoFactory.setTestAnswerDao(answerDao);
        answerManager.updateAnswers(connection,expected);

        verify(answerDao,times(0)).create(any(),any());
        verify(answerDao,times(0)).update(any(),any());
        verify(answerDao,times(0)).delete(any(),any());
    }

    @Test
    void updateAnswersThrowsExceptionTest() throws SQLException{
        Connection connection = mock(Connection.class);

        List<Answer> expected = new ArrayList<>();
        expected.add(getAnswerModel(1));
        AnswerDao answerDao = mock(AnswerDao.class);

        when(answerDao.findAnswerByQuestionId(any(),any())).thenThrow(SQLException.class);

        daoFactory.setTestAnswerDao(answerDao);

        assertThrows(ManagerException.class, () ->  answerManager.updateAnswers(connection,expected) );

    }

    @Test
    void updateAnswersDeleteTest() throws ManagerException, SQLException {
        Connection connection = mock(Connection.class);

        List<Answer> expected = new ArrayList<>();

        expected.add(getAnswerModel(5));

        List<Answer> oldTestData = new ArrayList<>();
        oldTestData.add(getAnswerModel(1));
        oldTestData.add(getAnswerModel(2));

        AnswerDao answerDao = mock(AnswerDao.class);

        when(answerDao.findAnswerByQuestionId(any(),any())).thenReturn(oldTestData);

        daoFactory.setTestAnswerDao(answerDao);
        answerManager.updateAnswers(connection,expected);

        verify(answerDao,times(2)).delete(any(),any());
    }

    @Test
    void updateAnswersUpdateTest() throws ManagerException, SQLException {
        Connection connection = mock(Connection.class);


        List<Answer> expected = new ArrayList<>();
        expected.add(getAnswerModel(1));
        expected.add(getAnswerModel(2));

        List<Answer> oldTestData = new ArrayList<>();
        oldTestData.add(getAnswerModel(1));
        oldTestData.add(getAnswerModel(2));

        AnswerDao answerDao = mock(AnswerDao.class);

        when(answerDao.findAnswerByQuestionId(any(),any())).thenReturn(oldTestData);

        daoFactory.setTestAnswerDao(answerDao);
        answerManager.updateAnswers(connection,expected);

        verify(answerDao,times(2)).update(any(),any());
    }

    private Answer getAnswerModel(long id) {
        Answer answer = new Answer();
        answer.setId(id);
        answer.setName("name");
        answer.setTrueAnswer(true);
        answer.setQuestionId(1);
        return answer;
    }
}