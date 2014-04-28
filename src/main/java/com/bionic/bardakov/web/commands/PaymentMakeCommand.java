package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;
import com.bionic.bardakov.web.entities.Payment;
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
public class PaymentMakeCommand implements ActionCommand {
    private static final String PARAM_NAME_MONEY_SUM = "moneySum";
    private static final String PARAM_NAME_ACCOUNT_NUMBER = "accountNumber";
    private static final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        HttpSession session = request.getSession();
        String local= (String) session.getAttribute("local");
        String smth = MessageManager.getInstance(local).getProperty(MessageManager.PAY_MESSAGE);
        try {
            float moneySum = Float.parseFloat(request.getParameter(PARAM_NAME_MONEY_SUM));
            long accountNumber = Long.parseLong((String) request.getSession().getAttribute(PARAM_NAME_ACCOUNT_NUMBER));

            if (MySQLDAOFactory.getMyDAOaccount().compareMoney(accountNumber, moneySum)) {
                Payment payment = new Payment(moneySum, accountNumber);
                MySQLDAOFactory.getMyDAOpayment().update(payment);
                MySQLDAOFactory.getMyDAOaccount().updateMoney(accountNumber, -moneySum);

                request.setAttribute("smth", ", " + smth + " " + moneySum);

                boolean isAdmin = MySQLDAOFactory.getMyDAOuser().isAdmin(login);
                if (!isAdmin) {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_PAGE_PATH);
                } else {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_ACCOUNT_PAGE_PATH);
                }

            } else {
                request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.MAKE_PAYMENT_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ERROR_PAGE_PATH);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.MAKE_PAYMENT_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ERROR_PAGE_PATH);
        }
        return page;
    }
}
