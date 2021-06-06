package com.zalisove.web.comands.functional_command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zalisove.db.bean.SimpleQuestionAnswerBean;
import com.zalisove.db.bean.SimpleTestBean;
import com.zalisove.db.entity.User;
import com.zalisove.db.entity.UserTest;
import com.zalisove.db.managers.ManagerException;
import com.zalisove.db.managers.TestManager;
import com.zalisove.db.managers.UserTestManager;
import com.zalisove.web.comands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Functional command for check test and save mark
 *
 * @author O.S.Kyrychenko
 */
public class TestCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(TestCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null){
            try {
                response.sendError(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOG.error("user no login error");
            return null;
        }

        try {
            StringBuilder jsonString = new StringBuilder();
            String tmp = "";
            BufferedReader bufferedReader = request.getReader();
            while ((tmp = bufferedReader.readLine()) != null){
                jsonString.append(tmp);
            }
            LOG.debug(jsonString.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleTestBean clientSimpleTestBean = objectMapper.readValue(jsonString.toString(),SimpleTestBean.class);
            LOG.debug(clientSimpleTestBean);

            TestManager testManager = TestManager.getInstance();
            SimpleTestBean serverSimpleTestBean = testManager.getRightTest(clientSimpleTestBean.getTestId());

            UserTestManager userTestManager = UserTestManager.getInstance();

            UserTest userTest = new UserTest();
            userTest.setTestId(clientSimpleTestBean.getTestId());
            userTest.setUserId(user.getId());
            userTest.setMark(review(serverSimpleTestBean,clientSimpleTestBean));
            userTestManager.createOrUpdate(userTest);

        } catch (JsonProcessingException e) {
            try {
                response.sendError(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOG.error("parse error error",e);

        } catch (IOException e) {
            try {
                response.sendError(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOG.error("read error error",e);
        } catch (ManagerException e) {
            try {
                response.sendError(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOG.error("data base error error",e);
        }
        return null;
    }

    private double review(SimpleTestBean right, SimpleTestBean checked){
        int countQuestion = right.getQuestionsAnswerID().size();
        int countRightAnswer = 0;



        List<SimpleQuestionAnswerBean> sortRight = right.getQuestionsAnswerID().stream()
                .sorted(Comparator.comparingLong(SimpleQuestionAnswerBean::getQuestionId))
                .collect(Collectors.toList());
        List<SimpleQuestionAnswerBean> sortChecked = checked.getQuestionsAnswerID().stream()
                .sorted(Comparator.comparingLong(SimpleQuestionAnswerBean::getQuestionId))
                .collect(Collectors.toList());


        LOG.debug("sortRight: " + sortRight);
        LOG.debug("sortChecked: " + sortChecked);
        for (int i = 0; i < sortRight.size(); i++) {
            if (sortChecked.get(i).getAnswerId().equals(sortRight.get(i).getAnswerId())){
                ++countRightAnswer;
            }
        }
        double mark;
        if(countQuestion == 0) {
            mark = 100;
        } else {
            mark = (double)(countRightAnswer * 100)/countQuestion;
        }
        LOG.debug(mark);
        return mark;
    }

}
