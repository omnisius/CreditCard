package commands;

import config.ConfigurationManager;
import dao.daofactory.MySQLDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 20.04.14
 * Time: 19:05
 */
public class BackToAccountCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        boolean isAdmin = MySQLDAOFactory.getMyDAOuser().isAdmin(login);
        if(!isAdmin) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_PAGE_PATH);
        } else{
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_ACCOUNT_PAGE_PATH);
        }
        return page;
    }
}
