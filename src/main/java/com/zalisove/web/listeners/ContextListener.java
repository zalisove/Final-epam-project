package com.zalisove.web.listeners;

import com.zalisove.db.dao.DAOFactory;
import com.zalisove.db.dao.mysql.MysqlDAOFactory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Context listener.
 *
 * @author O.S.Kyrychenko
 */
@WebListener
public class ContextListener implements ServletContextListener {


    private static final Logger LOG = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Initialize context listener");
        try {
            DAOFactory.setDaoFactoryFCN(MysqlDAOFactory.class.getName());
            Connection connection = DAOFactory.getInstance().getConnection();
            if (connection == null){
                LOG.info("connection equals null");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOG.info("SQL exception");
            throw new RuntimeException();
        }
        initI18N(sce.getServletContext());
    }

    /**
     * Initializes i18n subsystem.
     */
    private void initI18N(ServletContext servletContext) {
        LOG.debug("I18N subsystem initialization started");

        String localesValue = servletContext.getInitParameter("locales");
        if (localesValue == null || localesValue.isEmpty()) {
            LOG.warn("'locales' init parameter is empty, the default encoding will be used");
        } else {
            List<String> locales = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(localesValue);
            while (st.hasMoreTokens()) {
                String localeName = st.nextToken();
                locales.add(localeName);
            }

            LOG.debug("Application attribute set: locales --> " + locales);
            servletContext.setAttribute("locales", locales);
        }
        LOG.debug("I18N subsystem initialization finished");
    }
}
