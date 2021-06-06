package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import com.zalisove.db.entity.User;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.UserManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;


/**
 * Command to prepare data for display on the admin user page
 *
 * @author O.S.Kyrychenko
 */
public class AdminUsersPageCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(AdminUsersPageCommand.class);
    private static final long QUANTITY = 20;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        LOG.debug("Admin users page");

        int page;
        long countRecords;
        List<User> users;

        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page == 0) page = 1;
        } catch (NumberFormatException e) {
            page = 1;
        }
        UserManager userManager = UserManager.getInstance();
        try {
            countRecords = userManager.getCountRecords();
        } catch (ManagerException e) {
            countRecords = 0;
        }
        try {
            users =  userManager.getUsers(QUANTITY,(page-1)*QUANTITY);
        } catch (ManagerException e) {
            users = Collections.emptyList();
        }

        long pageCount = (long) Math.ceil((double) countRecords / QUANTITY);


        LOG.trace(page);
        LOG.trace(pageCount);

        request.setAttribute("users",users);
        request.setAttribute("page", page);
        request.setAttribute("pageCount", pageCount);


        return Path.PAGE__ADMIN_USERS;
    }
}
