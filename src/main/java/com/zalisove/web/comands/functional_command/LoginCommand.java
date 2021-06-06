package com.zalisove.web.comands.functional_command;

import com.zalisove.Hashing;
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
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


/**
 * Functional command for login user
 *
 * @author O.S.Kyrychenko
 */
public class LoginCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LOG.trace("email = " + email);
        UserManager userManager = UserManager.getInstance();
        Optional<User> user;
        try {
            user = userManager.searchUserByEmailAndPassword(email, Hashing.hash(password,"SHA-1"));
        } catch (ManagerException | NoSuchAlgorithmException e) {
            LOG.trace("no user email: " + email);
            user = Optional.empty();
        }

        if (user.isPresent()) {
            if (user.get().isBlocked()){
                request.setAttribute("isBlocked",true);
                return Path.PAGE__LOGIN;
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());
            LOG.trace("user email: " + email);
            LOG.trace("user is sesion: " + session.getAttribute("user"));
            LOG.trace("user: " + user.get());
            try {
                LOG.trace("sendRedirect --> " + request.getContextPath() + Path.COMMAND__START_PAGE);
                response.sendRedirect(request.getContextPath() + Path.COMMAND__START_PAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            request.setAttribute("error", true);
            LOG.trace("no user email: " + email);
            return Path.PAGE__LOGIN;
        }
    }
}
