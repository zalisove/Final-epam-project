package com.zalisove.web.comands.functional_command;

import com.zalisove.Path;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Functional command for sign out user
 *
 * @author O.S.Kyrychenko
 */
public class SignOutCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession(false);
        if (session !=  null){
            session.invalidate();
        }
        try {
            response.sendRedirect(request.getContextPath() + Path.COMMAND__START_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
