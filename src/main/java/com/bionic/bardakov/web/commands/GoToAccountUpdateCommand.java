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
 * Date: 20.04.14
 * Time: 13:27
 */
public class GoToAccountUpdateCommand implements ActionCommand {
    String page;
    private static final String PARAM_NAME_ACCOUNT_NUMBER = "accountNumber";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long accountNumber = Long.parseLong((String) request.getSession().getAttribute(PARAM_NAME_ACCOUNT_NUMBER));
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("local");
        try {
            String status = MySQLDAOFactory.getMyDAOaccount().findWhereAccountNumberEquals(accountNumber).getStatus();
            //Check account lock or not to redirect on different pages
            if (status.equals("lock")) {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOCK_PATH);
            } else {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.UPDATE_ACCOUNT_PAGE_PATH);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.UPDATE_ACCOUNT_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ERROR_PAGE_PATH);
        }
        return page;
    }
}
