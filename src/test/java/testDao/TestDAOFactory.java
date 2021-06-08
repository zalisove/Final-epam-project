package testDao;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDAOFactory extends DAOFactory {

    private Connection connection;
    private UserDao testUserDao;
    private AnswerDao testAnswerDao;
    private TestDao testTestDao;
    private QuestionDao testQuestionDao;
    private SubjectDao testSubjectDao;
    private UserTestDao testUserTestDao;

    @Override
    public synchronized Connection getConnection(){
        return connection;
    }

    @Override
    public UserDao getUserDao() {
        return testUserDao;
    }

    @Override
    public TestDao getTestDao() {
        return testTestDao;
    }

    @Override
    public AnswerDao getAnswerDao() {
        return testAnswerDao;
    }

    @Override
    public QuestionDao getQuestionDao() {
        return testQuestionDao;
    }

    @Override
    public SubjectDao getSubjectDao() {
        return testSubjectDao;
    }

    @Override
    public UserTestDao getUserTestDao() {
        return testUserTestDao;
    }

    public void setTestUserDao(UserDao testUserDao) {
        this.testUserDao = testUserDao;
    }

    public void setTestAnswerDao(AnswerDao testAnswerDao) {
        this.testAnswerDao = testAnswerDao;
    }

    public void setTestTestDao(TestDao testTestDao) {
        this.testTestDao = testTestDao;
    }

    public void setTestQuestionDao(QuestionDao testQuestionDao) {
        this.testQuestionDao = testQuestionDao;
    }

    public void setTestSubjectDao(SubjectDao testSubjectDao) {
        this.testSubjectDao = testSubjectDao;
    }

    public void setTestUserTestDao(UserTestDao testUserTestDao) {
        this.testUserTestDao = testUserTestDao;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
