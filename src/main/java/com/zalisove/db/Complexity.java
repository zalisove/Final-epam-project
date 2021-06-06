package com.zalisove.db;

import com.zalisove.db.entity.Test;

/**
 * Complexity entity
 * @author O.S.Kyrychenko
 */
public enum Complexity {
    EASY, NORMAL ,HARD;
    public static Complexity getComplexity(Test test){
        int complexityId = test.getComplexityId();
        return Complexity.values()[complexityId-1];
    }
    public static int getComplexityId(Complexity complexity){
        int id = 0;
        for (Complexity value : Complexity.values()) {
            id++;
            if (value == complexity){
                break;
            }
        }
        return id;
    }
}
