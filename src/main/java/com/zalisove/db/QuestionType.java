package com.zalisove.db;

import com.zalisove.db.entity.Question;

/**
 * QuestionType entity
 * @author O.S.Kyrychenko
 */
public enum QuestionType {
    ONE_ANSWER, MANY_ANSWER;

    public static QuestionType getQuestionType(Question question){
        int questionTypeId = question.getQuestionTypeId();
        return QuestionType.values()[questionTypeId-1];
    }
}
