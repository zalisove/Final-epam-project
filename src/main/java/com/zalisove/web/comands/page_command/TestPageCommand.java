package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import com.zalisove.db.bean.TestBean;
import com.zalisove.db.entity.Test;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.TestManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Command to prepare data for
 * display on test page
 *
 * @author O.S.Kyrychenko
 */
public class TestPageCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(TestPageCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        TestManager testManager = TestManager.getInstance();

        long id =- 1;
        try {
            id = Long.parseLong(request.getParameter("id"));
        }catch (NumberFormatException e){
            LOG.error("id no fount"+ request.getParameter("id"));
            id = -1;
        }
        TestBean testBean;
        try {
           testBean = testManager.getTestBean(id);
           Test test = testManager.read(id).orElseThrow(() -> new ManagerException("No update test"));
           test.setRequestsNumber(test.getRequestsNumber() + 1);
           testManager.update(test);
        } catch (ManagerException e) {
            LOG.error(e.getMessage());
            return Path.COMMAND__START_PAGE;
        }
        request.setAttribute("test",testBean);
        return Path.PAGE__TEST;
    }
}
