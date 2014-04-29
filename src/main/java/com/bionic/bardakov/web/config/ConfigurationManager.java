/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.bardakov.web.config;

import java.util.ResourceBundle;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 17:09
 */
public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    
    private static final String BUNDLE_NAME = "com.bionic.bardakov.web.config.config";
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String CARD_VIEW_PAGE_PATH = "CARD_VIEW_PAGE_PATH";
    public static final String INDEX_PAGE_PATH = "INDEX_PAGE_PATH";

    public static final String UNLOCK_ACCOUNT_PAGE_PATH = "UNLOCK_ACCOUNT_PAGE_PATH";
    public static final String UPDATE_ACCOUNT_PAGE_PATH = "UPDATE_ACCOUNT_PAGE_PATH";
    public static final String MONEY_PAGE_PATH = "MONEY_PAGE_PATH";
    public static final String ACCOUNT_ERROR_PAGE_PATH = "ACCOUNT_ERROR_PAGE_PATH";

    public static final String LOCK_PATH = "LOCK_PATH";
    public static final String USER_ADD_PAGE_PATH = "USER_ADD_PAGE_PATH";
    public static final String PASSWORD_CHANGE_PAGE_PATH = "PASSWORD_CHANGE_PAGE_PATH";
    public static final String MAKE_PAYMENT_PAGE_PATH = "MAKE_PAYMENT_PAGE_PATH";
    public static final String ACCOUNT_PAGE_PATH = "ACCOUNT_PAGE_PATH";
    public static final String ADMIN_ACCOUNT_PAGE_PATH = "ADMIN_ACCOUNT_PAGE_PATH";
    public static final String LOGOUT_PAGE_PATH = "LOGOUT_PAGE_PATH";
    public static final String CARD_ERROR_PAGE_PATH = "CARD_ERROR_PAGE_PATH";
    public static final String REGISTR_ERROR_PAGE_PATH = "REGISTR_ERROR_PAGE_PATH";
    public static ConfigurationManager getInstance(){
        if (instance == null){
            instance = new ConfigurationManager();
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
