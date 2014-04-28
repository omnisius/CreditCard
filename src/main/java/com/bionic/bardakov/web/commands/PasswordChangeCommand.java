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
 * Date: 07.04.14
 * Time: 19:22
 */
public class PasswordChangeCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        HttpSession session = request.getSession();
        String local= (String) session.getAttribute("local");
        System.out.println(local);
        String smth = MessageManager.getInstance(local).getProperty(MessageManager.NEW_PASSWORD_MESSAGE);

        MySQLDAOFactory.getMyDAOuser().updatePassword(password, login);
        request.setAttribute("user",login );
        request.setAttribute("smth", smth );
        request.setAttribute("local", local);
        return page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}

