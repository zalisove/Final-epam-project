package com.zalisove.db.bean;

import com.zalisove.db.entity.Answer;
import com.zalisove.db.entity.Question;

import java.io.Serializable;
import java.util.List;

/**
 * Bean to represent the question
 * @author O.S.Kyrychenko
 */

public class QuestionBean  implements Serializable {

    private List<Answer> answers;
    private Question question;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionBean{" +
                "answers=" + answers +
                ", question=" + question +
                '}';
    }
}
