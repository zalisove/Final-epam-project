package com.zalisove.db.managers;

import com.zalisove.db.bean.QuestionBean;
import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.QuestionDao;
import com.zalisove.db.entity.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testDao.TestDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class QuestionManagerTest {
    static QuestionManager questionManager;
    static TestDAOFactory daoFactory;
    @BeforeAll
    static void beforeAll() {
        DAOFactory.setDaoFactoryFCN(TestDAOFactory.class.getName());
        daoFactory = (TestDAOFactory)DAOFactory.getInstance();
        questionManager = QuestionManager.getInstance();
    }

    @Test
    void updateQuestionCreateTest() throws ManagerException, SQLException {
        Connection connection = mock(Connection.class);

        List<QuestionBean> expected = new ArrayList<>();
        expected.add(getQuestionBeanModel(1));
        expected.add(getQuestionBeanModel(2));
        expected.add(getQuestionBeanModel(3));

        List<Question> oldTestData = new ArrayList<>();

        QuestionDao questionDao = mock(QuestionDao.class);

        when(questionDao.findQuestionByTestId(any(),any())).thenReturn(oldTestData);

        daoFactory.setTestQuestionDao(questionDao);

        questionManager.questionBeansUpdate(connection,expected,1);
        verify(questionDao,times(3)).create(any(),any());
    }

    @Test
    void updateQuestionThrowsExceptionTest() throws SQLException {
        Connection connection = mock(Connection.class);

        List<QuestionBean> expected = new ArrayList<>();
        expected.add(getQuestionBeanModel(1));
        expected.add(getQuestionBeanModel(2));
        expected.add(getQuestionBeanModel(3));

        QuestionDao questionDao = mock(QuestionDao.class);

        doThrow(SQLException.class).when(questionDao).findQuestionByTestId(any(),any());

        daoFactory.setTestQuestionDao(questionDao);

        assertThrows(ManagerException.class,()->questionManager.questionBeansUpdate(connection,expected,1));
    }
    @Test
    void updateQuestionUpdateTest() throws ManagerException, SQLException {
        Connection connection = mock(Connection.class);

        List<QuestionBean> expected = new ArrayList<>();
        expected.add(getQuestionBeanModel(1));
        expected.add(getQuestionBeanModel(2));
        expected.add(getQuestionBeanModel(3));

        List<Question> oldTestData = new ArrayList<>();
        oldTestData.add(getQuestionModel(2));
        oldTestData.add(getQuestionModel(3));

        QuestionDao questionDao = mock(QuestionDao.class);

        when(questionDao.findQuestionByTestId(any(),any())).thenReturn(oldTestData);

        daoFactory.setTestQuestionDao(questionDao);

        questionManager.questionBeansUpdate(connection,expected,1);
        verify(questionDao,times(2)).update(any(),any());
    }

    @Test
    void updateQuestionDeleteTest() throws ManagerException, SQLException {
        Connection connection = mock(Connection.class);

        List<QuestionBean> expected = new ArrayList<>();

        List<Question> oldTestData = new ArrayList<>();
        oldTestData.add(getQuestionModel(1));
        oldTestData.add(getQuestionModel(2));
        oldTestData.add(getQuestionModel(3));

        QuestionDao questionDao = mock(QuestionDao.class);

        when(questionDao.findQuestionByTestId(any(),any())).thenReturn(oldTestData);

        daoFactory.setTestQuestionDao(questionDao);

        questionManager.questionBeansUpdate(connection,expected,1);
        verify(questionDao,times(3)).delete(any(),any());
    }


    private QuestionBean getQuestionBeanModel(long id) {

        QuestionBean questionBean = new QuestionBean();

        Question question = new Question();
        question.setId(id);
        question.setQuestionTypeId(1);
        question.setName("name");
        question.setTestId(1L);

        questionBean.setQuestion(question);
        questionBean.setAnswers(new ArrayList<>());
        return questionBean;
    }
    private Question getQuestionModel(long id) {

        Question question = new Question();
        question.setId(id);
        question.setQuestionTypeId(1);
        question.setName("name");
        question.setTestId(1L);
        return question;
    }
}