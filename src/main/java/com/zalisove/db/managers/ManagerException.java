package com.zalisove.db.managers;


/**
 * High-level exclusion
 * causes an error in the class manager
 * @author O.S.Kyrychenko
 */
public class ManagerException extends Exception{
    public ManagerException(String massage, Throwable throwable) {
        super(massage, throwable);
    }
    public ManagerException(String massage) {
        super(massage);
    }
}
