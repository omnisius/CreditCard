/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.bardakov.web.messages;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 17:09
 */
public class MessageManager {
    private static MessageManager instance;
    private ResourceBundle resourceBundle;
    private static final Logger logger = Logger.getLogger(MessageManager.class);
    
    private static final String BUNDLE_NAME_UA = "com.bionic.bardakov.web.messages.messages_uk_UA";
    private static final String BUNDLE_NAME_RU = "com.bionic.bardakov.web.messages.messages_ru_RU";
    private static final String BUNDLE_NAME_US = "com.bionic.bardakov.web.messages.messages_en_US";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
    public static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE";

    public static final String UPDATE_ACCOUNT_ERROR_MESSAGE = "UPDATE_ACCOUNT_ERROR_MESSAGE";
    public static final String GO_TO_ACCOUNT_ERROR_MESSAGE = "GO_TO_ACCOUNT_ERROR_MESSAGE";
    public static final String CARD_GET_ERROR_MESSAGE = "CARD_GET_ERROR_MESSAGE";
    public static final String USER_ADD_ERROR_MESSAGE = "USER_ADD_ERROR_MESSAGE";
    public static final String MAKE_PAYMENT_ERROR_MESSAGE = "MAKE_PAYMENT_ERROR_MESSAGE";
    public static final String MONEY_ERROR_MESSAGE = "MONEY_ERROR_MESSAGE";
    public static final String CARD_VIEW_ERROR_MESSAGE = "CARD_VIEW_ERROR_MESSAGE";
    public static final String ACCOUNT_UNLOCK_ERROR_MESSAGE = "ACCOUNT_UNLOCK_ERROR_MESSAGE";

    public static final String WELCOME_MESSAGE = "WELCOME_MESSAGE";
    public static final String LOCK_THIS_ACCOUNT = "LOCK_THIS_ACCOUNT";
    public static final String UNLOCK_THIS_ACCOUNT = "UNLOCK_THIS_ACCOUNT";
    public static final String UPDATE_THIS_ACCOUNT = "UPDATE_THIS_ACCOUNT";
    public static final String NEW_CARD_MESSAGE = "NEW_CARD_MESSAGE";
    public static final String NEW_ACCOUNT_MESSAGE = "NEW_ACCOUNT_MESSAGE";
    public static final String NEW_PASSWORD_MESSAGE = "NEW_PASSWORD_MESSAGE";
    public static final String PAY_MESSAGE = "PAY_MESSAGE";

    public static MessageManager getInstance(String local){
        if (instance == null){
            instance = new MessageManager();
            if(local.equals("uk_UA")) {
                instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME_UA);
            }if(local.equals("ru_RU")){
                instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME_RU);
            }if(local.equals("en_US")) {
                instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME_US);
            }else{
                logger.info("local is " + local );
            }
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
