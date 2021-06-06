package com.zalisove.db.managers;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.dao_interfases.SubjectDao;
import com.zalisove.db.entity.Subject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;
import java.util.Optional;

/**
 * Class that implements complex queries to a table Subject
 * @author O.S.Kyrychenko
 */
public class SubjectManager implements CRUD<Subject,Long>{
    private static SubjectManager instance;
    private SubjectDao subjectDao;
    private SubjectManager() {
        subjectDao = DAOFactory.getInstance().getSubjectDao();
    }

    public static SubjectManager getInstance() {
        if (instance == null){
            instance = new SubjectManager();
        }
        return instance;
    }


    /**
     * Create Subject.
     *
     * @param entity entity to create.
     *
     * @throws ManagerException when throws SQLException
     */
    @Override
    public void create(Subject entity) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            subjectDao.create(con,entity);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no create Subject",throwables);
        }
    }


    /**
     * Read Subject by primary key.
     *
     * @param primaryKey subject primary key.
     * @return <code>Optional<Subject><code/> subject
     * @throws ManagerException when throws SQLException
     */
    @Override
    public Optional<Subject> read(Long primaryKey) throws ManagerException {
        Optional<Subject> entity;
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            entity = subjectDao.read(con,primaryKey);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no read Subject",throwables);
        }
        return entity;
    }

    /**
     * Update Subject.
     *
     * @param entity entity to update.
     *
     * @throws ManagerException when throws SQLException
     */
    @Override
    public void update(Subject entity) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            subjectDao.update(con,entity);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no update Subject",throwables);
        }
    }

    /**
     * Delete Subject.
     *
     * @param primaryKey subject primary key.
     *
     * @throws ManagerException when throws SQLException
     */

    @Override
    public void delete(Long primaryKey) throws ManagerException {
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            subjectDao.delete(con,primaryKey);
            DAOFactory.commitAndClose(con);
        } catch (SQLException throwables) {
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no update Subject",throwables);
        }
    }


    /**
     * Get all Subject.
     *
     * @return <code>List<Subject><code/> subjects
     * @throws ManagerException when throws SQLException
     */
    public List<Subject> getAllSubject() throws ManagerException{
        List<Subject> subjects;
        Connection con = null;
        try {
            con = DAOFactory.getConnection();
            subjects = subjectDao.getAllSubject(con);
            DAOFactory.commitAndClose(con);
            return subjects;
        }catch (SQLException throwables){
            DAOFactory.rollbackAndClose(con);
            throw new ManagerException("no update Subject",throwables);
        }
    }
}
