package com.zalisove.db.dao;

import com.zalisove.db.dao.dao_interfases.*;
import com.zalisove.db.dao.mysql.MysqlDAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * class realizing pattern abstract factory for DAO
 * @author O.S.Kyrychenko
 */
public abstract class DAOFactory {


    private static final Logger LOG = LogManager.getLogger(DAOFactory.class);
    private static DAOFactory instance;
    private static String daoFactoryFCN = MysqlDAOFactory.class.getName();
    private static DataSource dataSource;

    protected DAOFactory() { }

    public static synchronized DAOFactory getInstance() {
        if (instance == null){
            try {
                Class<?> clazz = Class.forName(DAOFactory.daoFactoryFCN);
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                instance = (DAOFactory) constructor.newInstance();
            } catch (InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException |
                    NoSuchMethodException |
                    ClassNotFoundException e) {
                LOG.fatal(e);
            }
        }
        return instance;
    }


    public static void setDaoFactoryFCN(String daoFactoryFCN) {
        instance = null;
        dataSource = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/final_project_bd");
                return dataSource.getConnection();
            } catch (NamingException e) {
                LOG.error(e);
            }
        }else {
            return dataSource.getConnection();
        }
        throw new SQLException();
    }

    public static void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            LOG.error(ex);
        }
    }

    public static void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            LOG.error(ex);
        }
    }

    public abstract UserDao getUserDao();
    public abstract TestDao getTestDao();
    public abstract AnswerDao getAnswerDao();
    public abstract QuestionDao getQuestionDao();
    public abstract SubjectDao getSubjectDao();
    public abstract UserTestDao getUserTestDao();

}
