package commands;

import config.ConfigurationManager;
import dao.daofactory.MySQLDAOFactory;
import dao.mydao.MyDAOaccount;
import dao.mydao.MyDAOcard;
import dao.mydao.MyDAOuser;
import entities.Card;
import messages.MessageManager;
import session.SessionLogic;

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
