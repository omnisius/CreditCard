package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.entities.Account;
import com.bionic.bardakov.web.messages.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 17:10
 */
public class AccountUnlockCommand implements ActionCommand {
    private static final String PARAM_NAME_ACCOUNT_NUMBER = "userAccountNumber";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        long userAccountNumber = Long.parseLong(request.getParameter(PARAM_NAME_ACCOUNT_NUMBER));
        HttpSession session = request.getSession();
        String local= (String) session.getAttribute("local");
        String smth = MessageManager.getInstance(local).getProperty(MessageManager.UNLOCK_THIS_ACCOUNT);

        MySQLDAOFactory.getMyDAOaccount().updateStatus(userAccountNumber, "unlock");
        request.setAttribute("account", userAccountNumber);
        request.setAttribute("smth", smth);
        request.setAttribute("page", "ADMIN_ACCOUNT_PAGE_PATH");
        return page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_ACCOUNT_PAGE_PATH);
    }
}
