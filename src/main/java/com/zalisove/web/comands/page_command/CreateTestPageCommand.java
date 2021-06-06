package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import com.zalisove.db.entity.Subject;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.SubjectManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Command to prepare data for
 * display on the create test page
 *
 * @author O.S.Kyrychenko
 */
public class CreateTestPageCommand extends Command {


    private static final Logger LOG = LogManager.getLogger(CreateTestPageCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        SubjectManager subjectManager = SubjectManager.getInstance();
        List<Subject> subjects;
        try {
            subjects = subjectManager.getAllSubject();
        } catch (ManagerException e) {
            LOG.error("no get subjects",e);
            subjects = Collections.emptyList();
        }
        request.setAttribute("subjects",subjects);
        return Path.PAGE__CREATE_TEST;
    }
}
