package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.messages.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oper4 on 17.04.14.
 */
public class GoToRegistrationCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "USER_ADD_PAGE_PATH");
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_ADD_PAGE_PATH);
    }
}
