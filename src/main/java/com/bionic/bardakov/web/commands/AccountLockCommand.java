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
 * Time: 17:08
 */
public class AccountLockCommand implements ActionCommand {
    private static final String PARAM_NAME_ACCOUNT_NUMBER = "accountNumber";
    private static final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession();
        String local= (String) session.getAttribute("local");
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        long accountNumber = Long.parseLong((String) request.getSession().getAttribute(PARAM_NAME_ACCOUNT_NUMBER));
        //Update status in table "account" to "lock"
        MySQLDAOFactory.getMyDAOaccount().updateStatus(accountNumber, "lock");
        request.setAttribute("accountNumber", accountNumber);
        String smth = MessageManager.getInstance(local).getProperty(MessageManager.LOCK_THIS_ACCOUNT);
        request.setAttribute("smth", smth );
        //Check user Admin or not to redirect on different pages
        boolean isAdmin = MySQLDAOFactory.getMyDAOuser().isAdmin(login);
        if(!isAdmin) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_PAGE_PATH);
        } else{
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_ACCOUNT_PAGE_PATH);
        }
        return page;
    }
}
