package com.zalisove.db;

/**
 * Holder for fields names of DB tables and beans.
 *
 * @author O.S.Kyrychenko
 */
public final class Fields {
    private Fields(){}

    // entities
    public static final String ENTITY__ID = "id";

    public static final String USER__EMAIL = "email";
    public static final String USER__PASSWORD = "password";
    public static final String USER__NAME = "name";
    public static final String USER__SURNAME = "surname";
    public static final String USER__ROLES_ID = "roles_id";
    public static final String USER__IS_BLOCKED = "is_blocked";

    public static final String TEST__NAME = "name";
    public static final String TEST__TIME = "time";
    public static final String TEST__COMPLEXITY_ID= "complexity_id";
    public static final String TEST__SUBJECT_ID= "subject_id";
    public static final String TEST__REQUESTS_NUMBER = "requests_number";

    public static final String QUESTION__NAME = "name";
    public static final String QUESTION__TEST__ID = "test_id";
    public static final String QUESTION__TYPE__ID = "question_type_id";

    public static final String ANSWER__NAME = "name";
    public static final String ANSWER_TRUE = "true_answer";
    public static final String ANSWER__QUESTION_ID = "question_id";



    public static final String SUBJECT__NAME = "name";


    public static final String USER_TEST__USER_ID = "user_id";
    public static final String USER_TEST__TEST_ID = "test_id";
    public static final String USER_TEST__MARK = "mark";
    public static final String USER_TEST__WRITING_DATE = "writing_time";



    public static final String MENU_ITEM__NAME = "name";
    public static final String MENU_ITEM__CATEGORY_ID = "category_id";

    // beans
    public static final String USER_ORDER_BEAN__ORDER_ID = "id";
    public static final String USER_ORDER_BEAN__USER_FIRST_NAME = "first_name";
    public static final String USER_ORDER_BEAN__USER_LAST_NAME = "last_name";
    public static final String USER_ORDER_BEAN__ORDER_BILL = "bill";
    public static final String USER_ORDER_BEAN__STATUS_NAME = "name";

}
