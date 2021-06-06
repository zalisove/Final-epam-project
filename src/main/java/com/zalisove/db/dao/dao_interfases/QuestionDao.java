package com.zalisove.db.dao.dao_interfases;

import com.zalisove.db.entity.Question;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;

/**
 * Defines a general contract for means of access to an object Question
 * @author O.S.Kyrychenko
 */
public interface QuestionDao extends Dao<Question,Long>{

    /**
     * Find all question by test identifier
     *
     * @param con connection on which the operation will be performed
     * @param id test identifier
     * @return <code>List<Question></code> questions to the test on the identifier.
     *
     * @throws SQLTimeoutException when the driver has determined that the
     * timeout value that was specified by the {@code setQueryTimeout}
     * method has been exceeded and has at least attempted to cancel
     * the currently running {@code Statement}
     */
    List<Question> findQuestionByTestId(Connection con, Long id) throws SQLException;
}
