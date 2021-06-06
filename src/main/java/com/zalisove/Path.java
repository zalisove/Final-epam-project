package com.zalisove;

import java.util.HashMap;
import java.util.Map;

/**
 * Path holder (jsp pages, controller commands).
 * @author O.S.Kyrychenko
 */
public class Path {
	// pages
	public static final String PAGE__START = "WEB-INF/jsp/index.jsp";
	public static final String PAGE__LOGIN= "WEB-INF/jsp/login.jsp";
	public static final String PAGE__REGISTRATION= "WEB-INF/jsp/registration.jsp";
	public static final String PAGE__TEST= "WEB-INF/jsp/passing_test.jsp";
	public static final String PAGE__RESULT= "WEB-INF/jsp/result.jsp";
	public static final String PAGE__CREATE_TEST= "WEB-INF/jsp/test_create.jsp";
	public static final String PAGE__UPDATE_TEST= "WEB-INF/jsp/test_update.jsp";
	public static final String PAGE__USER_PAGE= "WEB-INF/jsp/user_page.jsp";
	public static final String PAGE__CHANGE_USER_PAGE= "WEB-INF/jsp/change_user_data.jsp";
	public static final String PAGE__ADMIN_USERS= "WEB-INF/jsp/admin_users.jsp";

	// sort command
	public static final String SORT_BY_NAME = "name";
	public static final String SORT_BY_COMPLEXITY = "complexity";
	public static final String SORT_BY_REQUEST_NUMBER= "requests_number";
	// reverse sort command
	public static final String SORT_BY_NAME_REVERSE = "name_reverse";
	public static final String SORT_BY_COMPLEXITY_REVERSE = "complexity_reverse";
	public static final String SORT_BY_REQUEST_NUMBER_REVERSE = "requests_number_reverse";

	public static final Map<String, String> sorted = new HashMap();

	static {
		sorted.put(null,"index.not_sort");
		sorted.put(SORT_BY_NAME,"index.sort_from_a_to_z");
		sorted.put(SORT_BY_COMPLEXITY,"index.sort_from_easy_to_hard");
		sorted.put(SORT_BY_REQUEST_NUMBER,"index.sort_from_not_popular_to_popular");
		sorted.put(SORT_BY_NAME_REVERSE,"index.sort_from_z_to_a");
		sorted.put(SORT_BY_COMPLEXITY_REVERSE,"index.sort_from_hard_to_easy");
		sorted.put(SORT_BY_REQUEST_NUMBER_REVERSE,"index.sort_from_popular_to_not_popular");
	}

	public static final String COMMAND__LOGIN_PAGE = "/controller?command=login_page";
	public static final String COMMAND__START_PAGE = "/controller?command=start_page";
	public static final String COMMAND__REGISTRATION_PAGE = "/controller?command=registration_page";
	public static final String COMMAND__CREATE_TEST_PAGE = "/controller?command=create_test_page";
	public static final String COMMAND__UPDATE_TEST_PAGE = "/controller?command=update_test_page";
	public static final String COMMAND___USER_PAGE= "/controller?command=user_page";
	public static final String COMMAND___CHANGE_USER_PAGE= "change_user_page";
	public static final String COMMAND___ADMIN_USER_PAGE= "/controller?command=admin_users_page";


	public static final String COMMAND__LOGIN = "login";
	public static final String COMMAND___REGISTRATION = "registration";
	public static final String COMMAND___SIGN_OUT = "/controller?command=sign_out";
	public static final String COMMAND___TEST= "/controller?command=test_check";
	public static final String COMMAND___CREATE_TEST= "/controller?command=create_test";
	public static final String COMMAND___UPDATE_TEST= "/controller?command=update_test";
	public static final String COMMAND___DELETE_TEST= "/controller?command=delete_test";
	public static final String COMMAND___DELETE_USER= "delete_user";
	public static final String COMMAND___UPDATE_USER_DATA= "update_user_data";
	public static final String COMMAND___SET_LOCALE= "set_locale";



}