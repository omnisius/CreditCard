package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.messages.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 09.04.14
 * Time: 13:49
 */

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        Object local = session.getAttribute("local");
        if ((local == null)) local = "ru_RU";
        session.setAttribute("local", local);

        if (MySQLDAOFactory.getMyDAOuser().checkLogin(login, password)) {
            request.setAttribute("user", login);
            request.setAttribute("local", local);
            String smth = MessageManager.getInstance((String) local).getProperty(MessageManager.WELCOME_MESSAGE);
            request.setAttribute("smth", " " + smth);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            request.setAttribute("errorMessage", MessageManager.getInstance((String) local).getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
