package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.dao.mydao.MyDAOaccount;
import com.bionic.bardakov.web.entities.Account;
import com.bionic.bardakov.web.entities.Card;
import com.bionic.bardakov.web.messages.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: ${Bogdan}
 * Date: 20.04.14
 * Time: 13:34
 */
public class GoToAccountUnlockCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession();
        String local= (String) session.getAttribute("local");

        MyDAOaccount myDAOaccount = MySQLDAOFactory.getMyDAOaccount();
        try {
            ArrayList accountNum = new ArrayList();
            Account[] account = myDAOaccount.findWhereStatusEquals("lock");
            if (account.length != 0) {
                for (Account aAccount : account) {
                    long userAccountNumber  = aAccount.getAccountNumber();
                    accountNum.add(userAccountNumber);

                    request.setAttribute("accountNum", accountNum);
                    request.setAttribute("size", accountNum.size());
                }
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.UNLOCK_ACCOUNT_PAGE_PATH);
            } else {
                request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.ACCOUNT_UNLOCK_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ERROR_PAGE_PATH);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.CARD_VIEW_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ERROR_PAGE_PATH);
        }
        return page;
    }
}
