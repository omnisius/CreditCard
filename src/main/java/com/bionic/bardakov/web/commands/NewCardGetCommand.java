package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.dao.mydao.MyDAOaccount;
import com.bionic.bardakov.web.dao.mydao.MyDAOcard;
import com.bionic.bardakov.web.dao.mydao.MyDAOuser;
import com.bionic.bardakov.web.entities.Account;
import com.bionic.bardakov.web.entities.Card;

import java.util.Random;

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
public class NewCardGetCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        HttpSession session = request.getSession();
        String local= (String) session.getAttribute("local");
        String newCard = MessageManager.getInstance(local).getProperty(MessageManager.NEW_CARD_MESSAGE);
        String newAccount = MessageManager.getInstance(local).getProperty(MessageManager.NEW_ACCOUNT_MESSAGE);
        try {

            long cardNumber = doCardNumber();
            MyDAOuser user = MySQLDAOFactory.getMyDAOuser();
            long usersId = user.findWhereLoginEquals(login).getId();

            Card card = new Card(cardNumber, usersId);
            MySQLDAOFactory.getMyDAOcard().add(card);

            long accountNumber = doAccountNumber();
            Account account = new Account(0, "unlock", cardNumber, accountNumber);
            MySQLDAOFactory.getMyDAOaccount().add(account);

            request.setAttribute("user", login);
            request.setAttribute("smth", newCard + " " + cardNumber + " " + newAccount + " " + accountNumber);

            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.CARD_GET_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }

    private long doAccountNumber() throws Exception {
        final long MIN_VALUE = 1260000000L;
        final int INTERVAL = 9999999;
        Random rn = new Random();
        long accountNumber = rn.nextInt(INTERVAL) + MIN_VALUE;
        MyDAOaccount myDAOaccount = MySQLDAOFactory.getMyDAOaccount();
        while (!myDAOaccount.isAcountNumberInDB(accountNumber)) {
            accountNumber = rn.nextInt(INTERVAL) + MIN_VALUE;
        }
        return accountNumber;
    }

    private long doCardNumber() throws Exception {
        final long MIN_VALUE = 512323000000000L;
        final int INTERVAL = 999999999;
        Random rn = new Random();
        long cardNumber = rn.nextInt(INTERVAL) + MIN_VALUE;
        MyDAOcard myDAOcard = MySQLDAOFactory.getMyDAOcard();
        while (!myDAOcard.isCardNumberInDB(cardNumber)) {
            cardNumber = rn.nextInt(INTERVAL) + MIN_VALUE;
        }
        return cardNumber;
    }
}
