package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Command to prepare data for
 * display on registration page
 *
 * @author O.S.Kyrychenko
 */
public class RegistrationPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        return Path.PAGE__REGISTRATION;
    }
}
