package com.zalisove.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Test entity
 * @author O.S.Kyrychenko
 */
public class Test extends Entity{

    private static final long serialVersionUID = -7938908123948217414L;

    private String name;
    private Long time;
    private int complexityId;
    private long requestsNumber;
    private long subjectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getComplexityId() {
        return complexityId;
    }

    public void setComplexityId(int complexityId) {
        this.complexityId = complexityId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getRequestsNumber() {
        return requestsNumber;
    }

    public void setRequestsNumber(long requestsNumber) {
        this.requestsNumber = requestsNumber;
    }

    @Override
    @JsonProperty
    public Long getId() {
        return super.getId();
    }
    @Override
    public String toString() {
        return "Test{" +
                "id = " + getId() +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", complexityId=" + complexityId +
                ", requestsNumber=" + requestsNumber +
                ", subjectId=" + subjectId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Test test = (Test) o;
        return complexityId == test.complexityId && requestsNumber == test.requestsNumber && subjectId == test.subjectId && Objects.equals(name, test.name) && Objects.equals(time, test.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, time, complexityId, requestsNumber, subjectId);
    }
}
