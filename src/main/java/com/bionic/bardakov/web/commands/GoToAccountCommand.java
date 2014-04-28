package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.dao.mydao.MyDAOaccount;
import com.bionic.bardakov.web.messages.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * User: ${Bogdan}
 * Date: 15.04.14
 * Time: 13:25
 */
public class GoToAccountCommand implements ActionCommand {
    private static final String PARAM_NAME_CARD_NUMBER = "cardNumber";
    private static final String PARAM_NAME_LOGIN = "login";
    Long accountNumber;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        Object local = session.getAttribute("local");
        if ((local == null)) local = "ru_RU";
        session.setAttribute("local", local);

        try {
            long cardNumber = Long.parseLong(request.getParameter(PARAM_NAME_CARD_NUMBER));
            String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);

            MyDAOaccount account = MySQLDAOFactory.getMyDAOaccount();
            accountNumber = account.findWhereAccountCardEquals(cardNumber).getAccountNumber();

            session.setAttribute("accountNumber", accountNumber.toString());
            request.setAttribute("accountNumber", accountNumber);

            boolean isAdmin = MySQLDAOFactory.getMyDAOuser().isAdmin(login);
            if (!isAdmin) {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_PAGE_PATH);
            } else {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_ACCOUNT_PAGE_PATH);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", MessageManager.getInstance((String) local).getProperty(MessageManager.GO_TO_ACCOUNT_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
