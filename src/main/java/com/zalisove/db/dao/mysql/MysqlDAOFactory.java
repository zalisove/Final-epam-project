package com.zalisove.db.dao.mysql;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.*;

/**
 * Class implementation DAOFactory
 * @author O.S.Kyrychenko
 */
public class MysqlDAOFactory extends DAOFactory {

    private UserDao userDao;
    private AnswerDao answerDao;
    private TestDao testDao;
    private QuestionDao questionDao;
    private SubjectDao subjectDao;
    private UserTestDao userTestDao;

    private MysqlDAOFactory() {}


    @Override
    public UserDao getUserDao() {
        if (userDao == null){
            userDao = new MysqlUserDao();
        }
        return userDao;
    }

    @Override
    public TestDao getTestDao() {
        if (testDao == null){
            testDao = new MysqlTestDao();
        }
        return testDao;
    }

    @Override
    public AnswerDao getAnswerDao() {
        if (answerDao == null){
            answerDao = new MysqlAnswerDao();
        }
        return answerDao;    }

    @Override
    public QuestionDao getQuestionDao() {
        if (questionDao == null){
            questionDao = new MysqlQuestionDao();
        }
        return questionDao;
    }

    @Override
    public SubjectDao getSubjectDao() {
        if (subjectDao == null){
            subjectDao = new MysqlSubjectDao();
        }
        return subjectDao;
    }

    @Override
    public UserTestDao getUserTestDao() {
        if (userTestDao == null){
            userTestDao = new MysqlUserTestDao();
        }
        return userTestDao;
    }
}
