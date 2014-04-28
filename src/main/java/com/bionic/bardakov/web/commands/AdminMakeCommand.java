package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 19:12
 */
public class AdminMakeCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);

        MySQLDAOFactory.getMyDAOuser().updateType(login);
        request.setAttribute("user", login);
        request.setAttribute("smth", "you became an admin)");
        return page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}
