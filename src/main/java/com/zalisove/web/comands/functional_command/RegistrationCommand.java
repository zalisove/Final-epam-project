package com.zalisove.web.comands.functional_command;

import com.zalisove.Hashing;
import com.zalisove.Path;
import com.zalisove.db.Role;
import com.zalisove.db.entity.User;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.UserManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Functional command for registration user
 *
 * @author O.S.Kyrychenko
 */
public class RegistrationCommand extends Command {
    private static final Logger LOG = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            UserManager userManager = UserManager.getInstance();
            User user = new User();
            user.setEmail(request.getParameter("email"));

            user.setPassword(Hashing.hash(request.getParameter("password"), "SHA-1"));

            user.setName(request.getParameter("name"));

            user.setSurname(request.getParameter("surname"));

            user.setRoleId(Role.getRoleId(Role.CLIENT));//Client
            userManager.create(user);

            request.getSession().setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + Path.COMMAND__START_PAGE);
            return null;

        } catch (ManagerException | IOException | NoSuchAlgorithmException e) {
            request.setAttribute("error", true);
            LOG.trace(e);
            e.printStackTrace();
            return Path.PAGE__REGISTRATION;
        }
    }
}
