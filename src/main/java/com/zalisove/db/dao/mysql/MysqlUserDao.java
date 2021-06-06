package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.EntityMapper;
import com.zalisove.db.dao.dao_interfases.UserDao;
import com.zalisove.db.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class implementation UserDao
 * @author O.S.Kyrychenko
 */
public class MysqlUserDao implements UserDao {

    private static final String SQL_FIND_USER_BY_ID = "SELECT id,email,password,name,surname,roles_id, is_blocked from user where id = ?";
    private static final String SQL_FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT id,email,password,name,surname,roles_id, is_blocked from user where email = ? and password = ?";
    private static final String SQL_CREATE_USER = "insert into user values (default, ?,?,?,?,?,?);";
    private static final String SQL_UPDATE_USER = "update user set email = ?, password = ?,name = ?,surname = ?, roles_id = ?, is_blocked = ? where id = ? ;";
    private static final String SQL_DELETE_USER = "delete from user where id = ?";
    private static final String SQL_COUNT_RECORDS_USER = "select count(*) from user";
    private static final String SQL_GET_USERS_QUANTITY_FROM = "SELECT id,email,password,name,surname,roles_id, is_blocked from user limit ? offset ?";


    private static final Logger LOG = LogManager.getLogger(MysqlUserDao.class);

    @Override
    public void create(Connection con, User user) throws SQLException {
        int k = 1;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getName());
            pstmt.setString(k++, user.getSurname());
            pstmt.setLong(k++, user.getRoleId());
            pstmt.setBoolean(k, user.isBlocked());

            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public Optional<User> read(Connection con, Long id) throws SQLException {
        User user = null;
        UserMapper mapper = new UserMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs =  pstmt.executeQuery()){
                if (rs.next())
                    user = mapper.mapRow(rs);
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> searchUserByEmailAndPassword(Connection con,String email, String password) throws SQLException {
        User user = null;
        UserMapper mapper = new UserMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            int k = 1;
            pstmt.setString(k++, email);
            pstmt.setString(k, password);
            try (ResultSet rs =  pstmt.executeQuery()){
                if (rs.next())
                    user = mapper.mapRow(rs);
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public long getCountRecords(Connection con) throws SQLException {
        long countRecords = 0L;
        try (PreparedStatement st = con.prepareStatement(SQL_COUNT_RECORDS_USER)) {
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    countRecords = rs.getLong(1);
                }
            }
        }
        return countRecords;
    }

    @Override
    public List<User> getUsers(Connection con, Long quantity, Long from) throws SQLException {
        List<User> users = new ArrayList<>();
        UserMapper mapper = new UserMapper();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_USERS_QUANTITY_FROM)) {
            int k = 1;
            pstmt.setLong(k++,quantity);
            pstmt.setLong(k,from);
            try (ResultSet rs =  pstmt.executeQuery()){
                User user;
                while (rs.next()) {
                    user = mapper.mapRow(rs);
                    if (user != null){
                        users.add(user);
                    }
                }
            }
            LOG.debug(pstmt);
        }
        return users;
    }

    @Override
    public void update(Connection con, User user) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER)) {
            int k = 1;
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getName());
            pstmt.setString(k++, user.getSurname());
            pstmt.setLong(k++, user.getRoleId());
            pstmt.setBoolean(k++, user.isBlocked());
            pstmt.setLong(k, user.getId());
            pstmt.executeUpdate();
        }
    }


    @Override
    public void delete(Connection con, Long id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_USER)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }



    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getLong(Fields.ENTITY__ID));
                user.setEmail(rs.getString(Fields.USER__EMAIL));
                user.setPassword(rs.getString(Fields.USER__PASSWORD));
                user.setName(rs.getString(Fields.USER__NAME));
                user.setSurname(rs.getString(Fields.USER__SURNAME));
                user.setRoleId(rs.getInt(Fields.USER__ROLES_ID));
                user.setBlocked(rs.getBoolean(Fields.USER__IS_BLOCKED));
                return user;
            } catch (SQLException e) {
                LOG.error(e);
            }
            return null;
        }
    }
}
