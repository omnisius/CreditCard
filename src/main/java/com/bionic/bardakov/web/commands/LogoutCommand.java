package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 18.04.14
 * Time: 17:58
 */
public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("local");
        request.setAttribute("local", local);
        session.invalidate();
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGOUT_PAGE_PATH);
    }
}
