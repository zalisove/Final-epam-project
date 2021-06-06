package com.zalisove.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


/**
 * Answer entity
 * @author O.S.Kyrychenko
 */
public class Answer extends Entity{

    @JsonIgnore
    private static final long serialVersionUID = 4833901696223830741L;
    private String name;
    private boolean trueAnswer;
    private long questionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(boolean trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Override
    @JsonProperty
    public Long getId() {
        return super.getId();
    }
    @Override
    public String toString() {
        return "Answer{" +
                "id = " + getId() +
                ", name='" + name + '\'' +
                ", trueAnswer=" + trueAnswer +
                ", questionId=" + questionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answer answer = (Answer) o;
        return trueAnswer == answer.trueAnswer && questionId == answer.questionId && Objects.equals(name, answer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, trueAnswer, questionId);
    }
}
