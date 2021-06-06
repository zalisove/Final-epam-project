package com.zalisove.db.dao.mysql;

import com.zalisove.db.Fields;
import com.zalisove.db.dao.dao_interfases.SubjectDao;
import com.zalisove.db.dao.dao_interfases.TestDao;
import com.zalisove.db.entity.Answer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MysqlTestDaoTest {

    private static final String SQL_CREATE_TEST = "insert into test values (default, ?,?,?,?,?)";
    private static final String SQL_FIND_TEST_BY_ID = "SELECT id,name,time,complexity_id,subject_id,requests_number from test where id = ?";
    private static final String SQL_GET_ALL_TEST = "SELECT id,name,time,complexity_id,subject_id,requests_number from test";

    private static final String SQL_GET_ALL_TEST_QUANTITY_FROM = "SELECT id,name,time,complexity_id,subject_id,requests_number from test limit ? offset ?";
    private static final String SQL_GET_ALL_TEST_SUBJECT_ID_QUANTITY_FROM = "SELECT id,name,time,complexity_id,subject_id,requests_number from test where subject_id = ? limit ? offset ?";

    private static final String SQL_GET_ALL_TEST_ORDER_BY_QUANTITY_FROM = "SELECT id,name,time,complexity_id,subject_id,requests_number from test order by ^ limit ? offset ?";
    private static final String SQL_GET_ALL_TEST_ORDER_BY_QUANTITY_FROM_REVERS = "SELECT id,name,time,complexity_id,subject_id,requests_number from test order by ^ desc limit ? offset ?";

    private static final String SQL_GET_ALL_TEST_ORDER_BY_SUBJECT_ID_QUANTITY_FROM = "SELECT id,name,time,complexity_id,subject_id,requests_number from test where subject_id = ? order by ^ limit ? offset ?";
    private static final String SQL_GET_ALL_TEST_ORDER_BY_SUBJECT_ID_QUANTITY_FROM_REVERS = "SELECT id,name,time,complexity_id,subject_id,requests_number from test where subject_id = ?  order by ^ desc limit ? offset ?";

    private static final String SQL_COUNT_RECORDS_TEST = "select count(*) from test";

    private static final String SQL_COUNT_RECORDS_BY_SUBJECT_ID_TEST = "select count(*) from test where subject_id = ?";

    TestDao testDao = new MysqlTestDao();

    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    @BeforeAll
    public static void beforeClass(){
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
    }
    @Test
    void createTest() throws SQLException {
        when(connection.prepareStatement(SQL_CREATE_TEST, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);

        com.zalisove.db.entity.Test actual = getTestModel();

        testDao.create(connection,actual);

        com.zalisove.db.entity.Test expected = getTestModel();

        assertEquals(expected,actual);
    }



    @Test
    void read() throws SQLException {
        when(connection.prepareStatement(SQL_FIND_TEST_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.TEST__NAME)).thenReturn("name");
        when(resultSet.getInt(Fields.TEST__COMPLEXITY_ID)).thenReturn(1);
        when(resultSet.getLong(Fields.TEST__SUBJECT_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__REQUESTS_NUMBER)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__TIME)).thenReturn(1L);

        com.zalisove.db.entity.Test actual = testDao.read(connection,1L).get();

        com.zalisove.db.entity.Test expected = getTestModel();

        assertEquals(expected,actual);

    }

    @Test
    void getAllTestTest() throws SQLException {
        when(connection.prepareStatement(SQL_GET_ALL_TEST)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.TEST__NAME)).thenReturn("name");
        when(resultSet.getInt(Fields.TEST__COMPLEXITY_ID)).thenReturn(1);
        when(resultSet.getLong(Fields.TEST__SUBJECT_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__REQUESTS_NUMBER)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__TIME)).thenReturn(1L);

        List<com.zalisove.db.entity.Test> actual = testDao.getAllTest(connection);

        List<com.zalisove.db.entity.Test> expected = new ArrayList<>();
        expected.add(getTestModel());

        assertEquals(expected,actual);
    }

    @Test
    void getAllTestQuantityFromTest() throws SQLException {
        when(connection.prepareStatement(SQL_GET_ALL_TEST_QUANTITY_FROM)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.TEST__NAME)).thenReturn("name");
        when(resultSet.getInt(Fields.TEST__COMPLEXITY_ID)).thenReturn(1);
        when(resultSet.getLong(Fields.TEST__SUBJECT_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__REQUESTS_NUMBER)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__TIME)).thenReturn(1L);

        List<com.zalisove.db.entity.Test> actual = testDao.getAllTest(connection,1L,1L);

        List<com.zalisove.db.entity.Test> expected = new ArrayList<>();
        expected.add(getTestModel());

        assertEquals(expected,actual);
    }

    @Test
    void getAllTestSubjectQuantityFromTest() throws SQLException {
        when(connection.prepareStatement(SQL_GET_ALL_TEST_SUBJECT_ID_QUANTITY_FROM)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.TEST__NAME)).thenReturn("name");
        when(resultSet.getInt(Fields.TEST__COMPLEXITY_ID)).thenReturn(1);
        when(resultSet.getLong(Fields.TEST__SUBJECT_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__REQUESTS_NUMBER)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__TIME)).thenReturn(1L);

        List<com.zalisove.db.entity.Test> actual = testDao.getAllTest(connection,1L,1L,1L);

        List<com.zalisove.db.entity.Test> expected = new ArrayList<>();
        expected.add(getTestModel());

        assertEquals(expected,actual);
    }

    @Test
    void getAllTestOrderByQuantityFromTest() throws SQLException {

        when(connection.prepareStatement(SQL_GET_ALL_TEST_ORDER_BY_QUANTITY_FROM.replace("^", "name"))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.TEST__NAME)).thenReturn("name");
        when(resultSet.getInt(Fields.TEST__COMPLEXITY_ID)).thenReturn(1);
        when(resultSet.getLong(Fields.TEST__SUBJECT_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__REQUESTS_NUMBER)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__TIME)).thenReturn(1L);

        List<com.zalisove.db.entity.Test> actual = testDao.getAllTest(connection,"name",1L,1L,false);

        List<com.zalisove.db.entity.Test> expected = new ArrayList<>();
        expected.add(getTestModel());

        assertEquals(expected,actual);
    }

    @Test
    void getAllTestOrderByQuantityFromReversTest() throws SQLException {

        when(connection.prepareStatement(SQL_GET_ALL_TEST_ORDER_BY_QUANTITY_FROM_REVERS.replace("^", "name"))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.TEST__NAME)).thenReturn("name");
        when(resultSet.getInt(Fields.TEST__COMPLEXITY_ID)).thenReturn(1);
        when(resultSet.getLong(Fields.TEST__SUBJECT_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__REQUESTS_NUMBER)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__TIME)).thenReturn(1L);

        List<com.zalisove.db.entity.Test> actual = testDao.getAllTest(connection,"name",1L,1L,true);

        List<com.zalisove.db.entity.Test> expected = new ArrayList<>();
        expected.add(getTestModel());

        assertEquals(expected,actual);
    }

    @Test
    void getAllTestOrderBySubjectIdQuantityFromTest() throws SQLException {
        when(connection.prepareStatement(SQL_GET_ALL_TEST_ORDER_BY_SUBJECT_ID_QUANTITY_FROM.replace("^", "name"))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.TEST__NAME)).thenReturn("name");
        when(resultSet.getInt(Fields.TEST__COMPLEXITY_ID)).thenReturn(1);
        when(resultSet.getLong(Fields.TEST__SUBJECT_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__REQUESTS_NUMBER)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__TIME)).thenReturn(1L);

        List<com.zalisove.db.entity.Test> actual = testDao.getAllTest(connection,"name",1L,1L,1L,false);

        List<com.zalisove.db.entity.Test> expected = new ArrayList<>();
        expected.add(getTestModel());

        assertEquals(expected,actual);
    }
    @Test
    void getAllTestOrderBySubjectIdQuantityFromReversTest() throws SQLException {
        when(connection.prepareStatement(SQL_GET_ALL_TEST_ORDER_BY_SUBJECT_ID_QUANTITY_FROM_REVERS.replace("^", "name"))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getLong(Fields.ENTITY__ID)).thenReturn(1L);
        when(resultSet.getString(Fields.TEST__NAME)).thenReturn("name");
        when(resultSet.getInt(Fields.TEST__COMPLEXITY_ID)).thenReturn(1);
        when(resultSet.getLong(Fields.TEST__SUBJECT_ID)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__REQUESTS_NUMBER)).thenReturn(1L);
        when(resultSet.getLong(Fields.TEST__TIME)).thenReturn(1L);

        List<com.zalisove.db.entity.Test> actual = testDao.getAllTest(connection,"name",1L,1L,1L,true);

        List<com.zalisove.db.entity.Test> expected = new ArrayList<>();
        expected.add(getTestModel());

        assertEquals(expected,actual);
    }

    @Test
    void countRecords() throws SQLException {
        when(connection.prepareStatement(SQL_COUNT_RECORDS_TEST)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);
        Long actual = testDao.countRecords(connection);
        assertEquals(1L,actual);
    }

    @Test
    void countRecordsBySubject() throws SQLException {
        when(connection.prepareStatement(SQL_COUNT_RECORDS_BY_SUBJECT_ID_TEST)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);
        Long actual = testDao.countRecords(connection,1);
        assertEquals(1L,actual);
    }

    private com.zalisove.db.entity.Test getTestModel() {
        com.zalisove.db.entity.Test test = new com.zalisove.db.entity.Test();
        test.setSubjectId(1);
        test.setRequestsNumber(1);
        test.setName("name");
        test.setId(1L);
        test.setTime(1L);
        test.setComplexityId(1);
        return test;
    }
}