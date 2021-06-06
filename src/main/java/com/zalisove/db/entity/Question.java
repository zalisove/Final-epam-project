package com.zalisove.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Question entity
 * @author O.S.Kyrychenko
 */
public class Question extends Entity{

    @JsonIgnore
    private static final long serialVersionUID = 3167794500492570354L;
    private String name;
    private long testId;
    private int questionTypeId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    @Override
    @JsonProperty
    public Long getId() {
        return super.getId();
    }
    @Override
    public String toString() {
        return "Question{" +
                "id = " + getId() +
                ", name='" + name + '\'' +
                ", testId=" + testId +
                ", questionTypeId=" + questionTypeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return testId == question.testId && questionTypeId == question.questionTypeId && Objects.equals(name, question.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, testId, questionTypeId);
    }
}
