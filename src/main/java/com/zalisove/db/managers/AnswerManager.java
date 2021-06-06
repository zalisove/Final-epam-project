package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.AnswerDao;
import com.zalisove.db.entity.Answer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Class that implements complex queries to a table Answer
 * @author O.S.Kyrychenko
 */
public class AnswerManager {
    private static final Logger LOG = LogManager.getLogger(AnswerManager.class);

    private static AnswerManager instance;

    private AnswerManager() {
    }

    public static AnswerManager getInstance() {
        if (instance == null) {
            instance = new AnswerManager();
        }
        return instance;
    }


    /**
     * Delete update or create old answers by new answers
     * @param newAnswers new answer for update old answers
     * @param con connection on which the operation will be performed
     * @author O.S.Kyrychenko
     */
    public void updateAnswers(Connection con, List<Answer> newAnswers) throws ManagerException {
        AnswerDao answerDao = DAOFactory.getInstance().getAnswerDao();
        if (newAnswers.isEmpty()){
            return;
        }
        try {
            List<Answer> oldAnswers = answerDao.findAnswerByQuestionId(con, newAnswers.get(0).getQuestionId());
            boolean toDelete;
            for (Answer oldAnswer : oldAnswers) {
                toDelete = true;
                for (Answer newAnswer : newAnswers) {
                    if (oldAnswer.getId().equals(newAnswer.getId())) {
                        answerDao.update(con, newAnswer);
                        LOG.debug("Updated answer: " + newAnswer);
                        toDelete = false;
                        break;
                    }
                }
                if (toDelete) {
                    LOG.debug("Deleted answer: " + oldAnswer);
                    answerDao.delete(con, oldAnswer.getId());
                }
            }
            boolean toCreate;
            for (Answer newAnswer : newAnswers) {
                toCreate = true;
                for (Answer oldAnswer : oldAnswers) {
                    if (oldAnswer.getId().equals(newAnswer.getId())) {
                        toCreate = false;
                        break;
                    }
                }
                if (toCreate) {
                    LOG.debug("Created answer: " + newAnswer);
                    answerDao.create(con, newAnswer);
                }
            }
        } catch (SQLException e) {
            LOG.error("No update answers",e);
            throw new ManagerException("No update answers", e);
        }
    }

    /**
     * Create many answers
     * @param answers answers for create
     * @param con connection on which the operation will be performed
     * @author O.S.Kyrychenko
     */
    public void createAnswers(Connection con, List<Answer> answers) throws ManagerException{
        AnswerDao answerDao = DAOFactory.getInstance().getAnswerDao();
        try {
            for (Answer answer : answers) {
                answerDao.create(con, answer);
            }
        } catch (SQLException e) {
            LOG.error("No create answers",e);
            throw new ManagerException("No create Answer",e);
        }
    }

}
