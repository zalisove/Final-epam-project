package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.EntityMapper;
import com.zalisove.db.dao.dao_interfases.SubjectDao;
import com.zalisove.db.entity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Class implementation SubjectDao
 * @author O.S.Kyrychenko
 */
public class MysqlSubjectDao implements SubjectDao {

    private static final String SQL_CREATE_SUBJECT= "insert into subject values (default, ?)";
    private static final String SQL_FIND_SUBJECT_BY_ID = "SELECT id,name from subject where id = ?";
    private static final String SQL_GET_All_SUBJECT = "SELECT id,name from subject";
    private static final String SQL_UPDATE_SUBJECT = "update subject set name = ? where id = ? ";
    private static final String SQL_DELETE_SUBJECT = "delete from subject where id = ?";

    private static final Logger LOG = LogManager.getLogger(MysqlSubjectDao.class);

    @Override
    public void create(Connection con, Subject subject) throws SQLException {
        int k = 1;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_SUBJECT, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(k, subject.getName());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    subject.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public Optional<Subject> read(Connection con, Long id) throws SQLException {
        Subject subject = null;
        SubjectMapper mapper = new SubjectMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_SUBJECT_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    subject = mapper.mapRow(rs);
            }
        }
        return Optional.ofNullable(subject);
    }


    @Override
    public void update(Connection con, Subject subject) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_SUBJECT)) {
            int k = 1;
            pstmt.setString(k, subject.getName());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Connection con, Long id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_SUBJECT)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Subject> getAllSubject(Connection con) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        SubjectMapper mapper = new SubjectMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_All_SUBJECT)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                Subject subject;
                while (rs.next()){
                    subject = mapper.mapRow(rs);
                    if (subject!=null){
                        subjects.add(subject);
                    }
                }
            }
        }
        return subjects;
    }

    private static class SubjectMapper implements EntityMapper<Subject> {

        @Override
        public Subject mapRow(ResultSet rs) {
            try {
                Subject subject = new Subject();
                subject.setId(rs.getLong(Fields.ENTITY__ID));
                subject.setName(rs.getString(Fields.SUBJECT__NAME));
                return subject;
            } catch (SQLException e) {
                LOG.error(e);
            }
            return null;
        }
    }

}
