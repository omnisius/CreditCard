package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.dao.mydao.MyDAOaccount;
import com.bionic.bardakov.web.messages.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 17:09
 */
public class AccountUpdateCommand implements ActionCommand {
    private static final String PARAM_NAME_ACCOUNT_NUMBER = "accountNumber";
    private static final String PARAM_NAME_MONEY = "money";
    private static final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        HttpSession session = request.getSession();
        String local= (String) session.getAttribute("local");
        try {
            long accountNumber = Long.parseLong((String) request.getSession().getAttribute(PARAM_NAME_ACCOUNT_NUMBER));
            float money = Float.parseFloat(request.getParameter(PARAM_NAME_MONEY));
            String smth = MessageManager.getInstance(local).getProperty(MessageManager.UPDATE_THIS_ACCOUNT);

            MyDAOaccount myDAOaccount = MySQLDAOFactory.getMyDAOaccount();
            //Check status of account
            if (!myDAOaccount.checkStatusIsUnlock(accountNumber)) {
                myDAOaccount.updateMoney(accountNumber, money);
                request.setAttribute("smth", smth + " " + money + " UAH");
                //Check user Admin or not to redirect on different pages
                boolean isAdmin = MySQLDAOFactory.getMyDAOuser().isAdmin(login);
                if (!isAdmin) {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_PAGE_PATH);
                } else {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_ACCOUNT_PAGE_PATH);
                }

            } else {
                request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.UPDATE_ACCOUNT_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ERROR_PAGE_PATH);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.UPDATE_ACCOUNT_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ERROR_PAGE_PATH);
        }
        return page;
    }
}
