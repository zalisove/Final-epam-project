package com.zalisove.web.comands.functional_command;

import com.zalisove.Path;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.TestManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Functional command for delete test
 *
 * @author O.S.Kyrychenko
 */
public class DeleteTestCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(DeleteTestCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        long id;
        try {
            id = Long.parseLong(request.getParameter("id"));

            TestManager testManager = TestManager.getInstance();
            testManager.delete(id);

        }catch (NumberFormatException e){
            LOG.error("no parse id",e);
        } catch (ManagerException e) {
            LOG.error("no delete test",e);
        }finally {
            try {
                response.sendRedirect(request.getContextPath() + Path.COMMAND__START_PAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
