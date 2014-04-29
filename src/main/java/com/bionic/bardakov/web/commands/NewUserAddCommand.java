package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.entities.User;
import com.bionic.bardakov.web.messages.MessageManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 17:02
 */
public class NewUserAddCommand implements ActionCommand {
    private static final String PARAM_NAME_ID = "id";
    private static final String PARAM_NAME_FIO = "fio";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession();
        Object local= session.getAttribute("local");
        if((local == null))   local = "ru_RU";
        session.setAttribute("local",local);
        try {
            long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
            String fio = request.getParameter(PARAM_NAME_FIO);
            String login = request.getParameter(PARAM_NAME_LOGIN);
            String password = request.getParameter(PARAM_NAME_PASSWORD);
            String smth = MessageManager.getInstance((String) local).getProperty(MessageManager.WELCOME_MESSAGE);

            session.setAttribute("login",login);

            User user = new User(id, fio, login, password, null);
            MySQLDAOFactory.getMyDAOuser().add(user);
            request.setAttribute("user", login);
            request.setAttribute("smth", smth);
            request.setAttribute("page", "MAIN_PAGE_PATH");
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", MessageManager.getInstance((String) local).getProperty(MessageManager.USER_ADD_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTR_ERROR_PAGE_PATH);
        }
        return page;
    }
}
