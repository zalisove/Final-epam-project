package com.zalisove.web.comands.functional_command;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.BufferedReader;
import java.io.IOException;


/**
 * Functional command for update test
 *
 * @author O.S.Kyrychenko
 */
public class UpdateTestCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(UpdateTestCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOG.trace("I'm this");
        StringBuilder jsonString = new StringBuilder();
        String tmp = "";
        BufferedReader bufferedReader;

        try {
            bufferedReader = request.getReader();

            while ((tmp = bufferedReader.readLine()) != null) {
                jsonString.append(tmp);
            }

            LOG.debug(jsonString.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            TestBean testBean = objectMapper.readValue(jsonString.toString(), TestBean.class);
            LOG.debug(testBean);
            if (testBean.getTest().getSubjectId() < 1) {
                JsonNode root = objectMapper.readTree(jsonString.toString());
                String subjectVal = root.get("subjectVal").asText();
                LOG.debug("subject value : " + subjectVal);
                try {
                    SubjectManager subjectManager = SubjectManager.getInstance();
                    Subject subject = new Subject();
                    subject.setName(subjectVal.toLowerCase());
                    subjectManager.create(subject);
                    testBean.getTest().setSubjectId(subject.getId());
                } catch (ManagerException e) {
                    LOG.error("no create subject",e);
                    try {
                        response.sendError(500);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }
            }
            TestManager testManager = TestManager.getInstance();
            testManager.updateTestBean(testBean);

        }catch (IOException e){
            LOG.error("Parse error", e);
            e.printStackTrace();
            try {
                response.sendError(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (ManagerException e) {
            LOG.error("DB error", e);
            e.printStackTrace();
            try {
                response.sendError(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
