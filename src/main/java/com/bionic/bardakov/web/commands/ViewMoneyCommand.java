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
 * Date: 20.04.14
 * Time: 0:41
 */
public class ViewMoneyCommand implements ActionCommand {
    private static final String PARAM_NAME_ACCOUNT_NUMBER = "accountNumber";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("local");
        try {
            long accountNumber = Long.parseLong((String) request.getSession().getAttribute(PARAM_NAME_ACCOUNT_NUMBER));

            MyDAOaccount account = MySQLDAOFactory.getMyDAOaccount();
            float money = account.findWhereAccountNumberEquals(accountNumber).getMoney();
            System.out.println(money);
            request.setAttribute("money", String.valueOf(money));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MONEY_PAGE_PATH);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.MONEY_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ERROR_PAGE_PATH);
        }
        return page;
    }
}
