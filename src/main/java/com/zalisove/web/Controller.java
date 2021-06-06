package com.zalisove.web;

import com.zalisove.web.comands.Command;
import com.zalisove.web.comands.CommandContainer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Main servlet controller.
 * @author O.S.Kyrychenko
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

        private static final long serialVersionUID = 2423353715955164816L;

        private static final Logger log = LogManager.getLogger(Controller.class);

        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
            process(request, response);
        }

        protected void doPost(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {
            process(request, response);
        }

        /**
         * Main method of this controller.
         */
        private void process(HttpServletRequest request,
                HttpServletResponse response) throws IOException, ServletException {

            log.debug("Controller starts");

            // extract command name from the request
            String commandName = request.getParameter("command");
            log.trace("Request parameter: command --> " + commandName);

            // obtain command object by its name
            Command command = CommandContainer.get(commandName);
            log.trace("Obtained command --> " + command);

            // execute command and get forward address
            String forward = command.execute(request, response);
            log.trace("Forward address --> " + forward);

            log.trace("Controller finished, now go to forward address --> " + forward);

            // if the forward address is not null go to the address
            if (forward != null) {
                RequestDispatcher disp = request.getRequestDispatcher(forward);
                disp.forward(request, response);
            }
        }

    }
