package com.zalisove.web.comands.functional_command;

import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.core.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
/**
 * Functional command for set locale
 *
 * @author O.S.Kyrychenko
 */
public class SetLocaleCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(SetLocaleCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String localeToSet = request.getParameter("localeToSet");
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            LOG.debug(localeToSet);
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
        }

        String referer = request.getHeader("referer");
        LOG.debug(referer);
        String pageFromWhichSent = request.getHeader("referer").substring(referer.indexOf(request.getContextPath()));
        LOG.debug(pageFromWhichSent);
        try {
            response.sendRedirect(pageFromWhichSent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
