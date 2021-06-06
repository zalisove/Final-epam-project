package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.EntityMapper;
import com.zalisove.db.dao.dao_interfases.UserTestDao;
import com.zalisove.db.entity.UserTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class implementation UserTestDao
 * @author O.S.Kyrychenko
 */
public class MysqlUserTestDao implements UserTestDao {

    private static final Logger LOG = LogManager.getLogger(MysqlUserTestDao.class);
    private static final String SQL_CREATE_USER_TEST = "insert into user_has_test values (?, ?, ?);";
    private static final String SQL_UPDATE_USER_TEST = "update user_has_test set mark = ? where user_id = ? and test_id = ? ";
    private static final String SQL_FIND_USER_TEST_BY_IDS = "SELECT user_id, test_id, mark  from user_has_test where user_id  = ? and test_id = ?";
    private static final String SQL_FIND_USER_TEST_BY_USER_ID = "SELECT user_id, test_id, mark  from user_has_test where user_id  = ?";


    @Override
    public void create(Connection con, UserTest userTest) throws SQLException {
        int k = 1;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_USER_TEST)) {
            pstmt.setLong(k++, userTest.getUserId());
            pstmt.setLong(k++, userTest.getTestId());
            pstmt.setDouble(k, userTest.getMark());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Connection con, UserTest userTest) throws SQLException {
        int k = 1;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER_TEST)) {
            pstmt.setDouble(k++, userTest.getMark());
            pstmt.setLong(k++, userTest.getUserId());
            pstmt.setLong(k, userTest.getTestId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Optional<UserTest> read(Connection con, long userId, long testId) throws SQLException {
        UserTest userTest = null;
        UserTestMapper mapper = new UserTestMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_TEST_BY_IDS)) {
            int k = 1;
            pstmt.setLong(k++, userId);
            pstmt.setLong(k, testId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    userTest = mapper.mapRow(rs);
            }
            LOG.debug(pstmt);
        }
        return Optional.ofNullable(userTest);
    }

    @Override
    public List<UserTest> getAllTestByUser(Connection con, long testId) throws SQLException {
        List<UserTest> userTests = new ArrayList<>();

        UserTestMapper mapper = new UserTestMapper();

        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_TEST_BY_USER_ID)) {
            pstmt.setLong(1, testId);
            try (ResultSet rs = pstmt.executeQuery()) {
                UserTest userTest = null;
                while (rs.next()) {
                    userTest = mapper.mapRow(rs);
                    if (userTest != null){
                        userTests.add(userTest);
                    }
                }
            }
            LOG.debug(pstmt);
        }
        return userTests;
    }

    private static class UserTestMapper implements EntityMapper<UserTest> {

        @Override
        public UserTest mapRow(ResultSet rs) {
            try {
                UserTest userTest = new UserTest();
                userTest.setUserId(rs.getLong(Fields.USER_TEST__USER_ID));
                userTest.setTestId(rs.getLong(Fields.USER_TEST__TEST_ID));
                userTest.setMark(rs.getDouble(Fields.USER_TEST__MARK));
                return userTest;
            } catch (SQLException e) {
                LOG.error(e);
            }
            return null;
        }
    }
}
