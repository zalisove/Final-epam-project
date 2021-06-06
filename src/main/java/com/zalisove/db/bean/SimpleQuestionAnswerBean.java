package com.zalisove.db.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Bean to represent the simple question have ids only and ids answer
 * @author O.S.Kyrychenko
 */
public class SimpleQuestionAnswerBean {

    @JsonProperty("questions_Id")
    private long questionId;

    @JsonProperty("answer_Id")
    private List<Long> answerId;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public List<Long> getAnswerId() {
        return answerId;
    }

    public void setAnswerId(List<Long> answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "SimpleQuestionAnswerBean{" +
                "questionId=" + questionId +
                ", answerId=" + answerId +
                '}';
    }
}
