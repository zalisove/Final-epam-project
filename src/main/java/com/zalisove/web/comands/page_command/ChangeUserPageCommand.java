package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import com.zalisove.db.Role;
import com.zalisove.db.entity.User;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.UserManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


/**
 * Command to prepare data for
 * display on the change user page
 *
 * @author O.S.Kyrychenko
 */
public class ChangeUserPageCommand extends Command {


    private static final Logger LOG = LogManager.getLogger(ChangeUserPageCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            redirectToStartPage(request, response);
            LOG.debug("User no have session");
            return null;
        }
        User userRequestsInformation = (User) session.getAttribute("user");
        if (userRequestsInformation == null) {
            redirectToStartPage(request, response);
            LOG.debug("Session no have user");
            return null;
        }
        try {
            long id = Long.parseLong(request.getParameter("id"));

            if (Role.getRole(userRequestsInformation).equals(Role.ADMIN)) {
                UserManager userManager = UserManager.getInstance();
                try {
                    User user = userManager.read(id).orElseThrow(() -> new ManagerException("user == null"));
                    LOG.debug("Admin get info about user" + user);
                    request.setAttribute("user", user);
                } catch (ManagerException e) {
                    LOG.debug("DB error no read user",e);
                    redirectToStartPage(request,response);
                    return null;
                }
            }else if (Role.getRole(userRequestsInformation).equals((Role.CLIENT))){
                request.setAttribute("user",userRequestsInformation);
                return Path.PAGE__CHANGE_USER_PAGE;
            }
        } catch (NumberFormatException e) {
            LOG.debug("no have id");
            request.setAttribute("user",userRequestsInformation);
        }
        return Path.PAGE__CHANGE_USER_PAGE;
    }

    private void redirectToStartPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + Path.COMMAND__START_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
