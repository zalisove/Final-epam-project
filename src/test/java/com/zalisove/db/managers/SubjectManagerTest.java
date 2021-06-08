package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.SubjectDao;
import com.zalisove.db.entity.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testDao.TestDAOFactory;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.reset;

class SubjectManagerTest {

    static SubjectManager subjectManager;
    static TestDAOFactory daoFactory;
    static SubjectDao subjectDao;

    @BeforeAll
    static void beforeAll() {
        DAOFactory.setDaoFactoryFCN(TestDAOFactory.class.getName());
        daoFactory = (TestDAOFactory) DAOFactory.getInstance();
        subjectManager = SubjectManager.getInstance();
        subjectDao = mock(SubjectDao.class);

        daoFactory.setConnection(mock(Connection.class));
    }

    @BeforeEach
    void setUp() {
        reset(subjectDao);
    }

    @Test
    void create() throws ManagerException, SQLException {
        daoFactory.setTestSubjectDao(subjectDao);
        subjectManager.create(getSubjectModel(1));
        verify(subjectDao, times(1)).create(any(), any());
    }

    @Test
    void read() throws ManagerException, SQLException {
        daoFactory.setTestSubjectDao(subjectDao);
        subjectManager.read(1L);
        verify(subjectDao, times(1)).read(any(), any());
    }

    @Test
    void update() throws SQLException, ManagerException {
        daoFactory.setTestSubjectDao(subjectDao);
        subjectManager.update(getSubjectModel(1));
        verify(subjectDao, times(1)).update(any(), any());
    }
    @Test
    void delete() throws SQLException, ManagerException {
        daoFactory.setTestSubjectDao(subjectDao);
        subjectManager.delete(1L);
        verify(subjectDao, times(1)).delete(any(), any());
    }



    @Test
    void getAllSubject() throws ManagerException, SQLException {
        daoFactory.setTestSubjectDao(subjectDao);
        subjectManager.getAllSubject();
        verify(subjectDao, times(1)).getAllSubject(any());
    }


    @Test
    void deleteThrowsException() throws SQLException {
        daoFactory.setTestSubjectDao(subjectDao);
        doThrow(SQLException.class).when(subjectDao).delete(any(), any());
        assertThrows(ManagerException.class, () -> subjectManager.delete(1L));
    }

    @Test
    void updateThrowsException() throws SQLException {
        daoFactory.setTestSubjectDao(subjectDao);
        doThrow(SQLException.class).when(subjectDao).update(any(), any());
        assertThrows(ManagerException.class, () -> subjectManager.update(getSubjectModel(1)));
    }

    @Test
    void getAllSubjectThrowsException() throws SQLException {
        daoFactory.setTestSubjectDao(subjectDao);
        when(subjectDao.getAllSubject(any())).thenThrow(SQLException.class);
        assertThrows(ManagerException.class, () -> subjectManager.getAllSubject());
    }

    @Test
    void createThrowsException() throws SQLException {
        daoFactory.setTestSubjectDao(subjectDao);
        doThrow(SQLException.class).when(subjectDao).create(any(), any());
        assertThrows(ManagerException.class, () -> subjectManager.create(getSubjectModel(1)));
    }

    @Test
    void readThrowsException() throws SQLException {
        daoFactory.setTestSubjectDao(subjectDao);
        when(subjectDao.read(any(),any())).thenThrow(SQLException.class);
        assertThrows(ManagerException.class, () -> subjectManager.read(1L));
    }


    private Subject getSubjectModel(long id) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName("name");
        return subject;
    }
}