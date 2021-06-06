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
public class ResultPageCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(ResultPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            try {
                response.sendError(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOG.error("user no login error");
            redirectToStartPage(request, response);
            return null;
        }
        long id;
        try {
            id = Long.parseLong(request.getParameter("id"));
        }catch (NumberFormatException e){
            LOG.error("No id");
            redirectToStartPage(request, response);
            return null;
        }

        UserTestManager userTestManager = UserTestManager.getInstance();
        TestManager testManager = TestManager.getInstance();
        SubjectManager subjectManager = SubjectManager.getInstance();
        try {
            Test test = testManager.read(id).orElseThrow(() -> new ManagerException("test == null"));
            Subject subject = subjectManager.read(test.getSubjectId()).orElseThrow(()-> new ManagerException("subject == null"));
            UserTest userTest = userTestManager.read(user.getId(),id);
            request.setAttribute("userTest", userTest);
            request.setAttribute("test", test);
            request.setAttribute("subject", subject);
            return Path.PAGE__RESULT;
        } catch (ManagerException e) {
            LOG.error("Problem to DB", e);
            redirectToStartPage(request, response);
            return null;
        }
    }

    private void redirectToStartPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + Path.COMMAND__START_PAGE);
        } catch (IOException e) {
            LOG.trace(e);
        }
    }
}
