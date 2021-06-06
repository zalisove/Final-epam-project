package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import com.zalisove.db.bean.TestBean;
import com.zalisove.db.entity.Subject;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.SubjectManager;
import com.zalisove.db.managers.TestManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;


/**
 * Command to prepare data for
 * display on update test page
 *
 * @author O.S.Kyrychenko
 */
public class UpdateTestPageCommand extends Command {


    private static final Logger LOG = LogManager.getLogger(UpdateTestPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        SubjectManager subjectManager = SubjectManager.getInstance();

        long id;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            LOG.error("No pars testId");
            return null;
        }
        try {
            TestManager testManager = TestManager.getInstance();
            TestBean testBean = testManager.getTestBean(id);
            request.setAttribute("testBean",testBean);
        } catch (ManagerException e) {
            LOG.error("No pars testId");
            return null;
        }

        List<Subject> subjects;
        try {
            subjects = subjectManager.getAllSubject();
        } catch (ManagerException e) {
            LOG.error("no get subjects", e);
            subjects = Collections.emptyList();
        }
        request.setAttribute("subjects", subjects);
        return Path.PAGE__UPDATE_TEST;


    }
}
