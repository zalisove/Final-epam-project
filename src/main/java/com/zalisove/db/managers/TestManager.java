package com.zalisove.db.managers;

import com.zalisove.db.bean.QuestionBean;
import com.zalisove.db.bean.SimpleQuestionAnswerBean;
import com.zalisove.db.bean.SimpleTestBean;
import com.zalisove.db.bean.TestBean;
import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.*;
import com.zalisove.db.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that implements complex queries to Test
 * @author O.S.Kyrychenko
 */
public class TestManager implements CRUD<Test, Long> {

    private static final Logger LOG = LogManager.getLogger(TestManager.class);

    private static TestManager instance;
    private TestDao testDao;

    private TestManager() {
        testDao = DAOFactory.getInstance().getTestDao();
    }

    public static TestManager getInstance() {
        if (instance == null) {
            instance = new TestManager();
        }
        return instance;
    }

    /**
     * Create Test.
     *
     * @param entity test to create.
     *
     * @throws ManagerException when throws SQLException
     */
    @Override
    public void create(Test entity) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            testDao.create(con, entity);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no create Test", throwables);
        }
    }

    /**
     * Read Test.
     *
     * @param primaryKey test identifier.
     *
     * @return <code>Optional<Test><code/> test
     * @throws ManagerException when throws SQLException
     */
    @Override
    public Optional<Test> read(Long primaryKey) throws ManagerException {
        Optional<Test> entity;
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            entity = testDao.read(con, primaryKey);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no read Test", throwables);
        }
        return entity;
    }

    /**
     * Update Test.
     *
     * @param entity test to update.
     *
     * @throws ManagerException when throws SQLException
     */
    @Override
    public void update(Test entity) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            testDao.update(con, entity);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no update Test", throwables);
        }
    }

    /**
     * Delete Test.
     *
     * @param primaryKey test identifier.
     *
     * @throws ManagerException when throws SQLException
     */
    @Override
    public void delete(Long primaryKey) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            testDao.delete(con, primaryKey);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no update Test", throwables);
        }
    }

    /**
     * Get all test.
     *
     * @throws ManagerException when throws SQLException
     */
    public List<Test> getAllTest() throws ManagerException {
        List<Test> tests;
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            tests = testDao.getAllTest(con);
            DAOFactory.commitAndClose(con);
            return tests;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get tests", throwables);
        }
    }

    /**
     * Get a certain number of tests from a position in the table
     * @param quantity number tests
     * @param from position in table
     *
     * @return <code>List<Test></code> tests.
     * @throws ManagerException when throws SQLException
     */
    public List<Test> getAllTest(Long quantity, Long from) throws ManagerException {
        List<Test> tests;
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            tests = testDao.getAllTest(con, quantity, from);
            DAOFactory.commitAndClose(con);
            return tests;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get tests quantity from", throwables);
        }
    }

    /**
     * Get a certain number of tests from a position in the table that relate to a specific subject
     *
     * @param quantity number tests
     * @param from position in table
     * @param subjectId identifier subject
     *
     * @return <code>List<Test></code> tests.
     *
     * @throws ManagerException when throws SQLException
     */
    public List<Test> getAllTest(Long subjectId, Long quantity, Long from) throws ManagerException {
        List<Test> tests;
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            tests = testDao.getAllTest(con, subjectId, quantity, from);
            DAOFactory.commitAndClose(con);
            return tests;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get tests subjectId quantity from", throwables);
        }
    }

    /**
     * Get a certain number of tests from a position in the table that sorted by param orderBy
     *
     * @param quantity number tests
     * @param from position in table
     * @param orderBy sort param
     * @param revers true - revers order false - straight order
     *
     * @return <code>List<Test></code> tests.
     *
     * @throws ManagerException when throws SQLException
     */
    public List<Test> getAllTest(String orderBy, Long quantity, Long from, boolean revers) throws ManagerException {
        List<Test> tests;
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            tests = testDao.getAllTest(con, orderBy, quantity, from, revers);
            DAOFactory.commitAndClose(con);
            return tests;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get tests orderBy quantity from", throwables);
        }
    }

    /**
     * Get a certain number of tests from a position in the table that relate to a specific subject sorted by param orderBy
     *
     * @param quantity number tests
     * @param from position in table
     * @param orderBy sort param
     * @param revers true - revers order false - straight order
     * @param subjectId identifier subject
     *
     * @return <code>List<Test></code> tests.
     *
     * @throws ManagerException when throws SQLException
     */
    public List<Test> getAllTest(String orderBy, long subjectId, Long quantity, Long from, boolean revers) throws ManagerException {
        List<Test> tests;
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            tests = testDao.getAllTest(con, orderBy, subjectId, quantity, from, revers);
            DAOFactory.commitAndClose(con);
            return tests;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get tests orderBy subjectId quantity from", throwables);
        }
    }

    /**
     * Get count of test
     *
     *
     * @return <code>Long</code> count of test.
     *
     * @throws ManagerException when throws SQLException
     */
    public Long countRecords() throws ManagerException {
        Connection con = null;
        Long countRecords;
        try {
            con = DAOFactory.getConnection();
            countRecords = testDao.countRecords(con);
            DAOFactory.commitAndClose(con);
            return countRecords;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get count records", throwables);
        }
    }

    /**
     * Get count of test that relate to a specific subject
     *
     * @param subjectId identifier subject
     *
     * @return <code>Long</code> count of test.
     *
     * @throws ManagerException when throws SQLException
     */
    public Long countRecords(long subjectId) throws ManagerException {
        Connection con = null;
        Long countRecords;
        try {
            con = DAOFactory.getConnection();
            countRecords = testDao.countRecords(con, subjectId);
            DAOFactory.commitAndClose(con);
            return countRecords;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("no get count records by subject id", throwables);
        }
    }


    /**
     * Get test that contains right id question and id right answer
     *
     * @param testId identifier test
     *
     * @return <code>SimpleTestBean</code> right simple test.
     *
     * @throws ManagerException when throws SQLException
     */
    public SimpleTestBean getRightTest(long testId) throws ManagerException {
        SimpleTestBean simpleTestBean = new SimpleTestBean();
        AnswerDao answerDao = DAOFactory.getInstance().getAnswerDao();
        QuestionDao questionDao = DAOFactory.getInstance().getQuestionDao();
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            List<Question> questions = questionDao.findQuestionByTestId(con, testId);
            List<SimpleQuestionAnswerBean> questionAnswerBeans = new ArrayList<>();
            for (Question question : questions) {
                SimpleQuestionAnswerBean questionAnswerBean = new SimpleQuestionAnswerBean();
                questionAnswerBean.setQuestionId(question.getId());
                List<Answer> answers = answerDao.findRightAnswerByQuestionId(con, question.getId());
                List<Long> answersId = answers.stream()
                        .map(Entity::getId)
                        .collect(Collectors.toList());
                questionAnswerBean.setAnswerId(answersId);
                questionAnswerBeans.add(questionAnswerBean);
            }
            simpleTestBean.setQuestionsAnswerID(questionAnswerBeans);
            DAOFactory.commitAndClose(con);
            return simpleTestBean;
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("No get testBeans", throwables);
        }
    }

    /**
     * Get test that contains question test that contains questions that contain answers
     *
     * @param id identifier test
     *
     * @return <code>TestBean</code> test that contains questions that contain answers.
     *
     * @throws ManagerException when throws SQLException
     */
    public TestBean getTestBean(Long id) throws ManagerException {
        AnswerDao answerDao = DAOFactory.getInstance().getAnswerDao();
        QuestionDao questionDao = DAOFactory.getInstance().getQuestionDao();

        TestBean testBean = new TestBean();
        Connection con = null;
        try {
            con = DAOFactory.getConnection();

            Test test = read(id).orElseThrow(() -> new ManagerException("no get Test"));
            testBean.setTest(test);

            List<Question> questions = questionDao.findQuestionByTestId(con, id);
            List<QuestionBean> questionBeans = new ArrayList<>();

            for (Question question : questions) {
                QuestionBean questionBean = new QuestionBean();
                questionBean.setQuestion(question);
                List<Answer> answers = answerDao.findAnswerByQuestionId(con, question.getId());
                questionBean.setAnswers(answers);
                questionBeans.add(questionBean);
            }
            testBean.setQuestionBeans(questionBeans);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            LOG.error(throwables.getMessage());
            throw new ManagerException("No get testBeans", throwables);
        }
        return testBean;
    }


    /**
     * Full create test, question, answer
     *
     * @param testBean test that contains questions that contain answers
     *
     * @throws ManagerException when throws SQLException
     */
    public void createTestBean(TestBean testBean) throws ManagerException {
        TestDao testDao = DAOFactory.getInstance().getTestDao();
        QuestionDao questionDao = DAOFactory.getInstance().getQuestionDao();
        AnswerDao answerDao = DAOFactory.getInstance().getAnswerDao();
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            Test test = testBean.getTest();
            testDao.create(con, test);
            LOG.debug("i'm create test");
            for (QuestionBean questionBean : testBean.getQuestionBeans()) {
                Question question = questionBean.getQuestion();
                question.setTestId(test.getId());
                questionDao.create(con, question);
                LOG.debug("i'm create question");
                for (Answer answer : questionBean.getAnswers()) {
                    answer.setQuestionId(question.getId());
                    answerDao.create(con, answer);
                    LOG.debug("i'm create answer");
                }
            }
            DAOFactory.commitAndClose(con);
        } catch (SQLException e) {
            DAOFactory.rollbackAndClose(con);
            LOG.error("error by crate test bean", e);
            throw new ManagerException("error by crate test bean", e);
        }

    }

    /**
     * Full update create test, question, answer
     *
     * @param newTestBean test that contains questions that contain answers
     *
     * @throws ManagerException when throws SQLException
     */
    public void updateTestBean(TestBean newTestBean) throws ManagerException {
        TestDao testDao = DAOFactory.getInstance().getTestDao();
        QuestionManager questionManager = QuestionManager.getInstance();
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            testDao.update(con,newTestBean.getTest());
            questionManager.questionBeansUpdate(con,newTestBean.getQuestionBeans(),newTestBean.getTest().getId());
            DAOFactory.commitAndClose(con);
        } catch (SQLException e) {
            LOG.error("no update TestBean");
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("No update testBean",e);
        }
    }

    /**
     * Get user test and evaluation of it
     *
     * @param id user identifier
     *
     * @return <code>Map<Test,Double><code/> test and mark for it
     * @throws ManagerException when throws SQLException
     */
    public Map<Test,Double> getTestsAndMarkByUserId(long id)throws ManagerException {
        TestDao testDao = DAOFactory.getInstance().getTestDao();
        UserTestDao userTestDao = DAOFactory.getInstance().getUserTestDao();
        Map<Test,Double> tests = new HashMap<>();
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            List<UserTest> userTests = userTestDao.getAllTestByUser(con,id);
            Test test;
            for (UserTest userTest : userTests) {
                test = testDao.read(con,userTest.getTestId()).orElseThrow(()->new ManagerException("No get test by id" + userTest.getTestId()));
                tests.put(test, userTest.getMark());
            }
            DAOFactory.commitAndClose(con);
        } catch (SQLException e) {
            DAOFactory.rollbackAndClose(con);
            e.printStackTrace();
        }
        return tests;
    }
}
