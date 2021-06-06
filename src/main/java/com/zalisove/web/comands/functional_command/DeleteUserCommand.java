package com.zalisove.web.comands.functional_command;

import com.zalisove.Path;
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
 * Functional command for delete user
 *
 * @author O.S.Kyrychenko
 */
public class DeleteUserCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        if (session == null){
            redirectToStartPage(request, response);
            return null;
        }
        User user = (User) session.getAttribute("user");
        if (user == null){
            redirectToStartPage(request, response);
            return null;
        }

        try {
            long id = Long.parseLong(request.getParameter("id"));
            LOG.debug("id deleted user" + id);
            UserManager userManager = UserManager.getInstance();
            userManager.delete(id);
            if (user.getId() == id){
                LOG.debug("Sing out");
                return Path.COMMAND___SIGN_OUT;
            }
            redirectToStartPage(request, response);
        } catch (NumberFormatException | ManagerException e) {
            LOG.debug(e);
            redirectToStartPage(request, response);
        }

        return null;
    }

    private void redirectToStartPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + Path.COMMAND__START_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
