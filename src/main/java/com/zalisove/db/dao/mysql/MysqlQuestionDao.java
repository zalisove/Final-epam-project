package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.EntityMapper;
import com.zalisove.db.dao.dao_interfases.QuestionDao;
import com.zalisove.db.entity.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Class implementation QuestionDao
 * @author O.S.Kyrychenko
 */
public class MysqlQuestionDao implements QuestionDao {

    private static final String SQL_CREATE_QUESTION = "insert into question values (default, ?,?,?)";
    private static final String SQL_FIND_QUESTION_BY_ID = "SELECT id,name,test_id,question_type_id from question where id = ?";
    private static final String SQL_FIND_QUESTIONS_BY_TEST_ID = "SELECT id,name,test_id,question_type_id from question where test_id = ?";
    private static final String SQL_UPDATE_QUESTION = "update question set name = ?, test_id = ?,question_type_id = ? where id = ? ";
    private static final String SQL_DELETE_QUESTION = "delete from question where id = ?";

    private static final Logger LOG = LogManager.getLogger(MysqlQuestionDao.class);

    @Override
    public void create(Connection con,  Question question) throws SQLException {
        int k = 1;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_QUESTION, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(k++, question.getName());
            pstmt.setLong(k++, question.getTestId());
            pstmt.setInt(k, question.getQuestionTypeId());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    question.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public Optional<Question> read(Connection con, Long id) throws SQLException {
        Question question = null;
        QuestionMapper mapper = new QuestionMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_QUESTION_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    question = mapper.mapRow(rs);
            }
        }
        return Optional.ofNullable(question);
    }


    @Override
    public void update(Connection con, Question question) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_QUESTION)) {
            int k = 1;
            pstmt.setString(k++, question.getName());
            pstmt.setLong(k++, question.getTestId());
            pstmt.setInt(k++, question.getQuestionTypeId());
            pstmt.setLong(k, question.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Connection con, Long id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_QUESTION)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Question> findQuestionByTestId(Connection con, Long id) throws SQLException {
        List<Question> questions = new ArrayList<>();
        QuestionMapper mapper = new QuestionMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_QUESTIONS_BY_TEST_ID)) {
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()){
                    questions.add(mapper.mapRow(rs));
                }
            }
        }
        return questions;
    }

    private static class QuestionMapper implements EntityMapper<Question> {

        @Override
        public Question mapRow(ResultSet rs) {
            try {
                Question question = new Question();
                question.setId(rs.getLong(Fields.ENTITY__ID));
                question.setName(rs.getString(Fields.QUESTION__NAME));
                question.setTestId(rs.getLong(Fields.QUESTION__TEST__ID));
                question.setQuestionTypeId(rs.getInt(Fields.QUESTION__TYPE__ID));
                return question;
            } catch (SQLException e) {
                LOG.error(e);
            }
            return null;
        }
    }
}
