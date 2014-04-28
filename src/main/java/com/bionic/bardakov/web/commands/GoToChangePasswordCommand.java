package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 17.04.14
 * Time: 23:13
 */
public class GoToChangePasswordCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PASSWORD_CHANGE_PAGE_PATH);
    }
}
