package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import com.zalisove.db.entity.Subject;
import com.zalisove.db.entity.Test;
import com.zalisove.db.entity.User;
import com.zalisove.db.entity.UserTest;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.SubjectManager;
import com.zalisove.db.managers.TestManager;
import com.zalisove.db.managers.UserTestManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Command to prepare data for
 * display on result page
 *
 * @author O.S.Kyrychenko
 */
public class TopUserPageCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(TopUserPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return Path.PAGE__TOP_USER;
    }

}
