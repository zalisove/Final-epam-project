package com.zalisove.db.managers;

import com.zalisove.db.bean.QuestionBean;
import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.QuestionDao;
import com.zalisove.db.entity.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements complex queries to a table Question
 * @author O.S.Kyrychenko
 */
public class QuestionManager {

    private static final Logger LOG = LogManager.getLogger(QuestionManager.class);

    private static QuestionManager instance;

    private QuestionManager() {
    }

    public static QuestionManager getInstance() {
        if (instance == null) {
            instance = new QuestionManager();
        }
        return instance;
    }

    /**
     * Delete update or create  old question by new question
     * @param questionBeans new question for update old question
     * @param con connection on which the operation will be performed
     * @param testId test identifier
     * @author O.S.Kyrychenko
     */
    public void questionBeansUpdate(Connection con, List<QuestionBean>questionBeans, long testId)throws ManagerException{
        QuestionDao questionDao = DAOFactory.getInstance().getQuestionDao();
        AnswerManager answerManager = AnswerManager.getInstance();
        try {
            List<Question> oldQuestions = questionDao.findQuestionByTestId(con,testId);
            boolean toDelete;

            for (Question question : oldQuestions) {
                toDelete = true;
                for (QuestionBean questionBean : questionBeans) {
                    if (question.getId().equals(questionBean.getQuestion().getId())){
                        questionDao.update(con, questionBean.getQuestion());
                        answerManager.updateAnswers(con, questionBean.getAnswers());
                        LOG.debug("Updated question: " + question);
                        toDelete = false;
                        break;
                    }
                }
                if (toDelete){
                    LOG.debug("Deleted question: " + question);
                    questionDao.delete(con,question.getId());
                }
            }

            boolean toCreate;
            for (QuestionBean questionBean : questionBeans) {
                toCreate = true;
                for (Question question : oldQuestions) {
                    if (question.getId().equals(questionBean.getQuestion().getId())){
                        toCreate = false;
                        break;
                    }
                }
                if (toCreate){
                    questionDao.create(con,questionBean.getQuestion());
                    LOG.debug("Created question: " + questionBean.getQuestion());
                    answerManager.createAnswers(con, questionBean.getAnswers());
                }
            }

        } catch (SQLException e) {
            throw new ManagerException("No update questions",e);
        }

    }
}
