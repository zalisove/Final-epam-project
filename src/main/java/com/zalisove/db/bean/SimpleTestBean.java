package com.zalisove.db.bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Bean to represent the simple test have ids only
 * @author O.S.Kyrychenko
 */
public class SimpleTestBean {

    @JsonProperty("id")
    private long testId;


    @JsonProperty("questionsId_answerId")
    private List<SimpleQuestionAnswerBean> questionsAnswerID;

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public List<SimpleQuestionAnswerBean> getQuestionsAnswerID() {
        return questionsAnswerID;
    }

    public void setQuestionsAnswerID(List<SimpleQuestionAnswerBean> questionsAnswerID) {
        this.questionsAnswerID = questionsAnswerID;
    }

    @Override
    public String toString() {
        return "SimpleTestBean{" +
                "testId=" + testId +
                ", questionsAnswerID=" + questionsAnswerID +
                '}';
    }
}
