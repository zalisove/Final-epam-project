package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.AnswerDao;
import com.zalisove.db.dao.dao_interfases.EntityMapper;
import com.zalisove.db.entity.Answer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class implementation AnswerDao
 * @author O.S.Kyrychenko
 */
public class MysqlAnswerDao implements AnswerDao {

    private static final String SQL_CREATE_ANSWER = "insert into answer values (default, ?,?,?)";
    private static final String SQL_FIND_ANSWER_BY_ID = "SELECT id,name,true_answer,question_id from answer where id = ?";
    private static final String SQL_FIND_ANSWER_BY_QUESTION__ID = "SELECT id,name,true_answer,question_id from answer where question_id = ?";
    private static final String SQL_FIND_RIGHT_ANSWER_BY_QUESTION__ID = "SELECT id,name,true_answer,question_id from answer where question_id = ? and true_answer = true";
    private static final String SQL_UPDATE_ANSWER = "update answer set name = ?, true_answer = ?,question_id = ? where id = ? ";
    private static final String SQL_DELETE_ANSWER = "delete from answer where id = ?";

    private static final Logger LOG = LogManager.getLogger(MysqlAnswerDao.class);

    @Override
    public void create(Connection con,  Answer answer) throws SQLException {
        int k = 1;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_ANSWER, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(k++, answer.getName());
            pstmt.setBoolean(k++, answer.isTrueAnswer());
            pstmt.setLong(k, answer.getQuestionId());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    answer.setId(rs.getLong(1));
                }
            }
            LOG.debug(pstmt);
        }
    }

    @Override
    public Optional<Answer> read(Connection con, Long id) throws SQLException {
        Answer answer = null;
        AnswerMapper mapper = new AnswerMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ANSWER_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    answer = mapper.mapRow(rs);
            }
        }
        return Optional.ofNullable(answer);
    }


    @Override
    public void update(Connection con, Answer answer) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_ANSWER)) {
            int k = 1;
            pstmt.setString(k++, answer.getName());
            pstmt.setBoolean(k++, answer.isTrueAnswer());
            pstmt.setLong(k++, answer.getQuestionId());
            pstmt.setLong(k, answer.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Connection con, Long id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_ANSWER)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Answer> findAnswerByQuestionId (Connection con, Long id) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        AnswerMapper mapper = new AnswerMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ANSWER_BY_QUESTION__ID)) {
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()){
                    answers.add(mapper.mapRow(rs));
                }
            }
        }
        return answers;
    }

    @Override
    public List<Answer> findRightAnswerByQuestionId(Connection con, Long id) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        AnswerMapper mapper = new AnswerMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_RIGHT_ANSWER_BY_QUESTION__ID)) {
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()){
                    answers.add(mapper.mapRow(rs));
                }
            }
        }
        return answers;
    }

    private static class AnswerMapper implements EntityMapper<Answer> {

        @Override
        public Answer mapRow(ResultSet rs) {
            try {
                Answer answer = new Answer();
                answer.setId(rs.getLong(Fields.ENTITY__ID));
                answer.setName(rs.getString(Fields.ANSWER__NAME));
                answer.setTrueAnswer(rs.getBoolean(Fields.ANSWER_TRUE));
                answer.setQuestionId(rs.getInt(Fields.ANSWER__QUESTION_ID));
                return answer;
            } catch (SQLException e) {
                LOG.error(e);
            }
            return null;
        }
    }

}
