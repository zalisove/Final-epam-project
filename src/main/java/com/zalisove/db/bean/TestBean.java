package com.zalisove.db.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zalisove.db.entity.Test;

import java.io.Serializable;
import java.util.List;

/**
 * Bean to represent the test
 * @author O.S.Kyrychenko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestBean  implements Serializable {

    private List<QuestionBean> questionBeans;

    private Test test;

    public List<QuestionBean> getQuestionBeans() {
        return questionBeans;
    }

    public void setQuestionBeans(List<QuestionBean> questionBeans) {
        this.questionBeans = questionBeans;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "questionBeans=" + questionBeans +
                ", test=" + test +
                '}';
    }
}
