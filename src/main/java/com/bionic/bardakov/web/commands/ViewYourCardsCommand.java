package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.dao.mydao.MyDAOcard;
import com.bionic.bardakov.web.dao.mydao.MyDAOuser;
import com.bionic.bardakov.web.entities.Card;
import com.bionic.bardakov.web.messages.MessageManager;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 19:21
 */
public class ViewYourCardsCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        HttpSession session = request.getSession();
        String local= (String) session.getAttribute("local");

        MyDAOuser myDAOuser = MySQLDAOFactory.getMyDAOuser();
        long userId = 0;
        try {
            userId = myDAOuser.findWhereLoginEquals(login).getId();
            MyDAOcard myDAOcard = MySQLDAOFactory.getMyDAOcard();
            Card[] card = myDAOcard.findWhereUsersIdEquals(userId);
            if (card.length != 0) {
                ArrayList cardNum = new ArrayList();
                for (Card aCard : card) {
                    long cardNumber = aCard.getCardNumber();
                    cardNum.add(cardNumber);
                    request.setAttribute("cardNum", cardNum);
                    request.setAttribute("size", cardNum.size());
                    request.setAttribute("page", "CARD_VIEW_PAGE_PATH");
                }
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CARD_VIEW_PAGE_PATH);
            } else {
                request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.CARD_VIEW_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CARD_ERROR_PAGE_PATH);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.CARD_VIEW_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CARD_ERROR_PAGE_PATH);
        }
        return page;
    }
}
