package com.zalisove.db.entity;

import java.util.Objects;
/**
 * UserTest entity
 * @author O.S.Kyrychenko
 */
public class UserTest {
    private long userId;
    private long testId;
    private double mark;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }


    @Override
    public String toString() {
        return "UserTest{" +
                "userId=" + userId +
                ", testId=" + testId +
                ", mark=" + mark +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTest userTest = (UserTest) o;
        return userId == userTest.userId && testId == userTest.testId && Double.compare(userTest.mark, mark) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, testId, mark);
    }
}
