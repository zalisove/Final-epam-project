package com.zalisove.web.filter;

import com.zalisove.Path;
import com.zalisove.db.Role;
import com.zalisove.db.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Security filter.
 *
 * @author O.S.Kyrychenko
 */
@WebFilter("/controller")
public class AccessFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger(AccessFilter.class);

    private static final List<String> clientCommands = new ArrayList<>();

    private static final List<String> adminCommands = new ArrayList<>();

    private static final List<String> generalCommands = new ArrayList<>();


    static {
        // general Commands
        generalCommands.add("start_page");
        generalCommands.add("set_locale");
        generalCommands.add("login_page");
        generalCommands.add("registration_page");
        generalCommands.add("login");
        generalCommands.add("registration");

        // client Commands
        clientCommands.add("change_user_page");
        clientCommands.add("start_page");
        clientCommands.add("set_locale");
        clientCommands.add("user_page");
        clientCommands.add("test_page");
        clientCommands.add("test_result_page");
        clientCommands.add("sign_out");
        clientCommands.add("test_check");
        clientCommands.add("update_user_data");


        // admin Commands
        adminCommands.add("user_page");
        adminCommands.add("set_locale");
        adminCommands.add("admin_users_page");
        adminCommands.add("update_user_data");
        adminCommands.add("start_page");
        adminCommands.add("sign_out");
        adminCommands.add("create_test");
        adminCommands.add("update_test");
        adminCommands.add("delete_test");
        adminCommands.add("change_user_page");
        adminCommands.add("delete_user");
        adminCommands.add("create_test_page");
        adminCommands.add("update_test_page");
        adminCommands.add("get_top_users");
        adminCommands.add("top_users_page");




        LOG.debug("Command container was successfully initialized");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession(false);
        String command = request.getParameter("command");
        User user = null;
        if (httpSession != null){
            user = (User) httpSession.getAttribute("user");
        }

        LOG.debug("Access Filter start: " + command);

        if (httpSession == null || user == null){
            LOG.debug("Not authorized: " + command);
            if (getGeneralCommands(command)) {
                LOG.debug("Not authorized  command: " + command);
                chain.doFilter(request, response);
            }else {
                redirectToLoginPage(httpRequest, httpResponse);
            }
        }else if (Role.getRole(user).equals(Role.ADMIN)){
            LOG.debug("Admin: " + command);
            if (getAdminCommands(command)) {
                chain.doFilter(request, response);
            }else {
                redirectToStartPage(httpRequest, httpResponse);
            }
        }else if (Role.getRole(user).equals(Role.CLIENT)){
            LOG.debug("Client: " + command);
            if (getClientCommands(command)) {
                chain.doFilter(request, response);
            }else {
                redirectToStartPage(httpRequest, httpResponse);
            }
        }else {
            redirectToStartPage(httpRequest, httpResponse);
        }
    }

    /**
     * Redirect to login page
     * @param  httpRequest request
     * @param  httpResponse response
     */
    private void redirectToLoginPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        try {
            httpResponse.sendRedirect(httpRequest.getContextPath() + Path.COMMAND__LOGIN_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Redirect to start page
     * @param  httpRequest request
     * @param  httpResponse response
     */
    private void redirectToStartPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        try {
            httpResponse.sendRedirect(httpRequest.getContextPath() + Path.COMMAND__START_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Search command in client list
     * @param  commandName command name
     */
    public static boolean getClientCommands(String commandName) {
        if (commandName == null) {
            LOG.trace("Command not found, name --> " + commandName);
            return false;
        }
        return clientCommands.contains(commandName);
    }

    /**
     * Search command in admin list
     * @param  commandName command name
     */
    public static boolean getAdminCommands(String commandName) {
        if (commandName == null) {
            LOG.trace("Command not found, name --> " + commandName);
            return false;
        }
        return adminCommands.contains(commandName);
    }

    /**
     * Search command in general list
     * @param  commandName command name
     */
    public static boolean getGeneralCommands(String commandName) {
        if (commandName == null ) {
            LOG.trace("Command not found, name --> " + commandName);
            return false;
        }
        return generalCommands.contains(commandName);
    }
}
