package com.zalisove.db.dao.dao_interfases;

import com.zalisove.db.entity.Answer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;


/**
 * Defines a general contract for means of access to an object Answer
 * @author O.S.Kyrychenko
 */
public interface AnswerDao extends Dao<Answer,Long> {

    /**
     * Find all answer by question identifier
     *
     * @param con connection on which the operation will be performed
     * @param id question identifier
     * @return <code>List<Answer></code> answers to the question on the identifier.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Answer> findAnswerByQuestionId(Connection con, Long id) throws SQLException;

    /**
     * Find only right answer by question identifier
     *
     * @param con connection on which the operation will be performed
     * @param id question identifier
     * @return <code>List<Answer></code> right answers to the question on the identifier.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Answer> findRightAnswerByQuestionId(Connection con, Long id) throws SQLException;

}
