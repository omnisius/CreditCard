package com.bionic.bardakov.web.config;

import java.util.ResourceBundle;

/**
 * Created by Богдан on 24.04.2014.
 */
public class PageManager {
    private static PageManager instance;
    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "com.bionic.bardakov.web.config.pages";
    public static final String INDEX_PAGE_PATH = "INDEX_PAGE_PATH";


    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String LOCK_PATH = "LOCK_PAGE_PATH";

    public static final String UNLOCK_ACCOUNT_PAGE_PATH = "UNLOCK_ACCOUNT_PAGE_PATH";
    public static final String UPDATE_ACCOUNT_PAGE_PATH = "UPDATE_ACCOUNT_PAGE_PATH";
    public static final String MONEY_PAGE_PATH = "MONEY_PAGE_PATH";

    public static final String USER_ADD_PAGE_PATH = "USER_ADD_PAGE_PATH";
    public static final String PASSWORD_CHANGE_PAGE_PATH = "PASSWORD_CHANGE_PAGE_PATH";
    public static final String MAKE_PAYMENT_PAGE_PATH = "MAKE_PAYMENT_PAGE_PATH";

    public static final String LOGOUT_PAGE_PATH = "LOGOUT_PAGE_PATH";


    public static PageManager getInstance(){
        if (instance == null){
            instance = new PageManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key){
        return resourceBundle.getString(key);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}

