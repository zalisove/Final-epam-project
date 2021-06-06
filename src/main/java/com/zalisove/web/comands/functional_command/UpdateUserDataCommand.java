package com.zalisove.web.comands.functional_command;

import com.zalisove.Hashing;
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
import java.security.NoSuchAlgorithmException;

/**
 * Functional command for update user data
 *
 * @author O.S.Kyrychenko
 */
public class UpdateUserDataCommand extends Command {


    private static final Logger LOG = LogManager.getLogger(UpdateUserDataCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {


        HttpSession session = request.getSession();
        if (session == null) {
            try {
                response.sendError(401);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            try {
                response.sendError(401);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        try {
            long id = Long.parseLong(request.getParameter("id"));
            UserManager userManager = UserManager.getInstance();
            User updatedUser = userManager.read(id).orElseThrow(() -> new ManagerException("User == null id: " + id));

            if(user.getId() != updatedUser.getId()
                    && Role.getRole(user).equals(Role.ADMIN)
                    && Role.getRole(updatedUser).equals(Role.ADMIN)){
                try {
                    response.sendError(400);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                return null;
            }
            if (Role.getRole(user).equals(Role.ADMIN) && id != user.getId()) {
                LOG.debug("Admin update yourself");
                adminUpdateUser(request, userManager, updatedUser);
                return null;
            }else if (id == user.getId()){
                LOG.debug("Update yourself");
                updateAboutYourself(request, userManager, updatedUser);
                session.setAttribute("user",updatedUser);
                return null;
            }
        } catch (NumberFormatException e) {
            LOG.error("Error to update user date",e);
        } catch (ManagerException e) {
            LOG.error("Duplicate email",e);
            try {
                response.sendError(400);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return null;
    }

    private void adminUpdateUser(HttpServletRequest request, UserManager userManager, User updatedUser) throws ManagerException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Role role = Role.getRole(Integer.parseInt(request.getParameter("role")));
        boolean isBlocked = request.getParameter("isBlocked") != null && request.getParameter("isBlocked").equals("on");

        if (email != null && !email.isEmpty()) {
            updatedUser.setEmail(email);
        }
        if (name != null && !name.isEmpty()) {
            updatedUser.setName(name);
        }
        if (surname != null && !surname.isEmpty()) {
            updatedUser.setSurname(surname);
        }
        if (role != null) {
            updatedUser.setRoleId(Role.getRoleId(role));
        }
        updatedUser.setBlocked(isBlocked);

        userManager.update(updatedUser);
    }
    private void updateAboutYourself(HttpServletRequest request, UserManager userManager, User updatedUser) throws ManagerException {

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        if (email != null && !email.isEmpty()) {
            updatedUser.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            try {
                updatedUser.setPassword(Hashing.hash(password,"SHA-1"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        if (name != null && !name.isEmpty()) {
            updatedUser.setName(name);
        }
        if (surname != null && !surname.isEmpty()) {
            updatedUser.setSurname(surname);
        }
        userManager.update(updatedUser);
    }
}
