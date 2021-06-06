package com.zalisove.web.comands.page_command;

import com.zalisove.db.Fields;
import com.zalisove.db.entity.Subject;
import com.zalisove.db.entity.Test;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.SubjectManager;
import com.zalisove.db.managers.TestManager;
import com.zalisove.Path;
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
 * display on start page
 *
 * @author O.S.Kyrychenko
 */
public class StartPageCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(StartPageCommand.class);


    private static final long QUANTITY = 20;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        SubjectManager subjectManager = SubjectManager.getInstance();
        TestManager testManager = TestManager.getInstance();

        List<Subject> subjects;
        List<Test> tests;
        long countRecords;
        long subjectId;
        String sortCommand = request.getParameter("sort");
        int page;

        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page == 0) page = 1;
        } catch (NumberFormatException e) {
            page = 1;
        }
        try {
            subjectId = Long.parseLong(request.getParameter("subject"));
        } catch (NumberFormatException e) {
            subjectId = -1;
        }

        countRecords = getCountRecords(testManager, subjectId);

        try {
            subjects = subjectManager.getAllSubject();
        } catch (ManagerException e) {
            subjects = Collections.emptyList();
            e.printStackTrace();
        }

        tests = getTests(testManager, sortCommand, subjectId, page);

        long pageCount = (long) Math.ceil((double) countRecords / QUANTITY);


        LOG.trace(sortCommand);
        LOG.trace(subjectId);
        LOG.trace(page);
        LOG.trace(pageCount);

        request.setAttribute("subjects", subjects);
        request.setAttribute("tests", tests);
        request.setAttribute("page", page);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("sortCommand", sortCommand);


        return Path.PAGE__START;
    }

    private long getCountRecords(TestManager testManager, long subjectId) {
        long countRecords;
        try {
            if (subjectId < 0) {
                countRecords = testManager.countRecords();
            } else {
                countRecords = testManager.countRecords(subjectId);
            }
            if (countRecords == 0) countRecords = 1;
        } catch (ManagerException e) {
            countRecords = 1;
        }
        return countRecords;
    }

    private List<Test> getTests(TestManager testManager, String sortCommand, long subjectId, int page) {
        List<Test> tests;
        try {
            if (subjectId < 0) {
                if (sortCommand == null) {
                    tests = testManager.getAllTest(QUANTITY, (page - 1) * QUANTITY);
                } else if (sortCommand.equals(Path.SORT_BY_NAME)) {
                    tests = testManager.getAllTest(Fields.TEST__NAME, QUANTITY, (page - 1) * QUANTITY, false);
                } else if (sortCommand.equals(Path.SORT_BY_COMPLEXITY)) {
                    tests = testManager.getAllTest(Fields.TEST__COMPLEXITY_ID, QUANTITY, (page - 1) * QUANTITY, false);
                } else if (sortCommand.equals(Path.SORT_BY_REQUEST_NUMBER)) {
                    tests = testManager.getAllTest(Fields.TEST__REQUESTS_NUMBER, QUANTITY, (page - 1) * QUANTITY, false);
                }  else if (sortCommand.equals(Path.SORT_BY_NAME_REVERSE)) {
                    tests = testManager.getAllTest(Fields.TEST__NAME, QUANTITY, (page - 1) * QUANTITY, true);
                } else if (sortCommand.equals(Path.SORT_BY_COMPLEXITY_REVERSE)) {
                    tests = testManager.getAllTest(Fields.TEST__COMPLEXITY_ID, QUANTITY, (page - 1) * QUANTITY, true);
                } else if (sortCommand.equals(Path.SORT_BY_REQUEST_NUMBER_REVERSE)) {
                    tests = testManager.getAllTest(Fields.TEST__REQUESTS_NUMBER, QUANTITY, (page - 1) * QUANTITY, true);
                } else {
                    tests = testManager.getAllTest(QUANTITY, (page - 1) * QUANTITY);
                }
            } else {
                if (sortCommand == null) {
                    tests = testManager.getAllTest(subjectId, QUANTITY, (page - 1) * QUANTITY);
                } else if (sortCommand.equals(Path.SORT_BY_NAME)) {
                    tests = testManager.getAllTest(Fields.TEST__NAME, subjectId, QUANTITY, (page - 1) * QUANTITY, false);
                } else if (sortCommand.equals(Path.SORT_BY_COMPLEXITY)) {
                    tests = testManager.getAllTest(Fields.TEST__COMPLEXITY_ID, subjectId, QUANTITY, (page - 1) * QUANTITY, false);
                } else if (sortCommand.equals(Path.SORT_BY_REQUEST_NUMBER)) {
                    tests = testManager.getAllTest(Fields.TEST__REQUESTS_NUMBER, subjectId, QUANTITY, (page - 1) * QUANTITY, false);
                } else if (sortCommand.equals(Path.SORT_BY_NAME_REVERSE)) {
                    tests = testManager.getAllTest(Fields.TEST__NAME, subjectId, QUANTITY, (page - 1) * QUANTITY, true);
                } else if (sortCommand.equals(Path.SORT_BY_COMPLEXITY_REVERSE)) {
                    tests = testManager.getAllTest(Fields.TEST__COMPLEXITY_ID, subjectId, QUANTITY, (page - 1) * QUANTITY, true);
                } else if (sortCommand.equals(Path.SORT_BY_REQUEST_NUMBER_REVERSE)) {
                    tests = testManager.getAllTest(Fields.TEST__REQUESTS_NUMBER, subjectId, QUANTITY, (page - 1) * QUANTITY, true);
                } else {
                    tests = testManager.getAllTest(subjectId, QUANTITY, (page - 1) * QUANTITY);
                }
            }
        } catch (ManagerException e) {
            tests = Collections.emptyList();
            e.printStackTrace();
        }
        return tests;
    }
}
