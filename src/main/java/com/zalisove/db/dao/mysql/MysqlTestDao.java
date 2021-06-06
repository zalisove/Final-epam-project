package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.EntityMapper;
import com.zalisove.db.dao.dao_interfases.TestDao;
import com.zalisove.db.entity.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Class implementation TestDao
 * @author O.S.Kyrychenko
 */
public class MysqlTestDao implements TestDao {

    private static final String SQL_CREATE_TEST = "insert into test values (default, ?,?,?,?,?)";
    private static final String SQL_FIND_TEST_BY_ID = "SELECT id,name,time,complexity_id,subject_id,requests_number from test where id = ?";
    private static final String SQL_GET_ALL_TEST = "SELECT id,name,time,complexity_id,subject_id,requests_number from test";
    private static final String SQL_UPDATE_TEST = "update test set name = ?, time = ?,complexity_id = ?, subject_id = ?, requests_number = ? where id = ? ";
    private static final String SQL_DELETE_TEST = "delete from test where id = ?";

    private static final String SQL_GET_ALL_TEST_QUANTITY_FROM = "SELECT id,name,time,complexity_id,subject_id,requests_number from test limit ? offset ?";
    private static final String SQL_GET_ALL_TEST_SUBJECT_ID_QUANTITY_FROM = "SELECT id,name,time,complexity_id,subject_id,requests_number from test where subject_id = ? limit ? offset ?";

    private static final String SQL_GET_ALL_TEST_ORDER_BY_QUANTITY_FROM = "SELECT id,name,time,complexity_id,subject_id,requests_number from test order by ^ limit ? offset ?";
    private static final String SQL_GET_ALL_TEST_ORDER_BY_QUANTITY_FROM_REVERS = "SELECT id,name,time,complexity_id,subject_id,requests_number from test order by ^ desc limit ? offset ?";

    private static final String SQL_GET_ALL_TEST_ORDER_BY_SUBJECT_ID_QUANTITY_FROM = "SELECT id,name,time,complexity_id,subject_id,requests_number from test where subject_id = ? order by ^ limit ? offset ?";
    private static final String SQL_GET_ALL_TEST_ORDER_BY_SUBJECT_ID_QUANTITY_FROM_REVERS = "SELECT id,name,time,complexity_id,subject_id,requests_number from test where subject_id = ?  order by ^ desc limit ? offset ?";

    private static final String SQL_COUNT_RECORDS_TEST = "select count(*) from test";

    private static final String SQL_COUNT_RECORDS_BY_SUBJECT_ID_TEST = "select count(*) from test where subject_id = ?";

    private static final Logger LOG = LogManager.getLogger(MysqlTestDao.class);

    @Override
    public void create(Connection con, Test test) throws SQLException {
        int k = 1;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_TEST, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(k++, test.getName());
            pstmt.setLong(k++, test.getTime());
            pstmt.setLong(k++, test.getRequestsNumber());
            pstmt.setInt(k++, test.getComplexityId());
            pstmt.setLong(k, test.getSubjectId());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    test.setId(rs.getLong(1));
                }
            }
            LOG.debug("Created test" + test);
            LOG.debug(pstmt);
        }
    }

    @Override
    public Optional<Test> read(Connection con, Long id) throws SQLException {
        Test test = null;
        TestMapper mapper = new TestMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_TEST_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    test = mapper.mapRow(rs);
            }
        }
        return Optional.ofNullable(test);
    }


    @Override
    public void update(Connection con, Test test) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_TEST)) {
            int k = 1;
            pstmt.setString(k++, test.getName());
            pstmt.setLong(k++, test.getTime());
            pstmt.setInt(k++, test.getComplexityId());
            pstmt.setLong(k++, test.getSubjectId());
            pstmt.setLong(k++, test.getRequestsNumber());
            pstmt.setLong(k, test.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Connection con, Long id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_TEST)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Test> getAllTest(Connection con) throws SQLException {
        List<Test> tests = new ArrayList<>();
        TestMapper mapper = new TestMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_TEST)) {
            initTestList(tests, mapper, pstmt);
            LOG.trace(pstmt);
        }
        return tests;
    }

    @Override
    public List<Test> getAllTest(Connection con, Long quantity, Long from) throws SQLException {
        List<Test> tests = new ArrayList<>();
        TestMapper mapper = new TestMapper();
        return getTests(con, quantity, from, tests, mapper, SQL_GET_ALL_TEST_QUANTITY_FROM);
    }



    @Override
    public List<Test> getAllTest(Connection con, long subjectId, Long quantity, Long from) throws SQLException {
        List<Test> tests = new ArrayList<>();
        TestMapper mapper = new TestMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_TEST_SUBJECT_ID_QUANTITY_FROM)) {
            int k = 1;
            pstmt.setLong(k++, subjectId);
            pstmt.setLong(k++, quantity);
            pstmt.setLong(k, from);
            initTestList(tests, mapper, pstmt);
            LOG.trace(pstmt);
        }
        return tests;
    }

    @Override
    public List<Test> getAllTest(Connection con, String orderBy, Long quantity, Long from, boolean revers) throws SQLException {
        List<Test> tests = new ArrayList<>();
        TestMapper mapper = new TestMapper();
        String query;
        if (!revers) {
            query = SQL_GET_ALL_TEST_ORDER_BY_QUANTITY_FROM.replace("^", orderBy);
        } else query = SQL_GET_ALL_TEST_ORDER_BY_QUANTITY_FROM_REVERS.replace("^", orderBy);

        return getTests(con, quantity, from, tests, mapper, query);
    }

    @Override
    public List<Test> getAllTest(Connection con, String orderBy, long subjectId, Long quantity, Long from, boolean revers) throws SQLException {
        List<Test> tests = new ArrayList<>();
        TestMapper mapper = new TestMapper();
        String query;
        if (!revers) {
            query = SQL_GET_ALL_TEST_ORDER_BY_SUBJECT_ID_QUANTITY_FROM.replace("^", orderBy);
        } else query = SQL_GET_ALL_TEST_ORDER_BY_SUBJECT_ID_QUANTITY_FROM_REVERS.replace("^", orderBy);

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            int k = 1;
            pstmt.setLong(k++, subjectId);
            pstmt.setLong(k++, quantity);
            pstmt.setLong(k, from);
            initTestList(tests, mapper, pstmt);
            LOG.trace(pstmt);
        }
        return tests;
    }

    @Override
    public Long countRecords(Connection con) throws SQLException {
        long countRecords = 0L;
        try (PreparedStatement st = con.prepareStatement(SQL_COUNT_RECORDS_TEST)) {
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    countRecords = rs.getLong(1);
                }
            }
        }
        return countRecords;
    }
    @Override
    public Long countRecords(Connection con, long subjectId) throws SQLException {
        long countRecords = 0L;
        try (PreparedStatement st = con.prepareStatement(SQL_COUNT_RECORDS_BY_SUBJECT_ID_TEST)) {
            st.setLong(1,subjectId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    countRecords = rs.getLong(1);
                }
            }
        }
        return countRecords;
    }
    private List<Test> getTests(Connection con, Long quantity, Long from, List<Test> tests, TestMapper mapper, String sqlGetAllTestQuantityFrom) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(sqlGetAllTestQuantityFrom)) {
            int k = 1;
            pstmt.setLong(k++, quantity);
            pstmt.setLong(k, from);
            initTestList(tests, mapper, pstmt);
            LOG.trace(pstmt);
        }
        return tests;
    }
    private void initTestList(List<Test> tests, TestMapper mapper, PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            Test test;
            while (rs.next()) {
                test = mapper.mapRow(rs);
                if (test != null) {
                    tests.add(test);
                }
            }
        }
    }


    private static class TestMapper implements EntityMapper<Test> {

        @Override
        public Test mapRow(ResultSet rs) {
            try {
                Test test = new Test();
                test.setId(rs.getLong(Fields.ENTITY__ID));
                test.setName(rs.getString(Fields.TEST__NAME));
                test.setComplexityId(rs.getInt(Fields.TEST__COMPLEXITY_ID));
                test.setSubjectId(rs.getLong(Fields.TEST__SUBJECT_ID));
                test.setRequestsNumber(rs.getLong(Fields.TEST__REQUESTS_NUMBER));
                test.setTime(rs.getLong(Fields.TEST__TIME));
                return test;
            } catch (SQLException e) {
                LOG.error(e);
            }
            return null;
        }
    }
}
