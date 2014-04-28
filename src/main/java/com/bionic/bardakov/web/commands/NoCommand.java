package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 09.04.14
 * Time: 13:55
 */
public class NoCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "LOGIN_PAGE_PATH");
        HttpSession session = request.getSession();
        String local= (String) request.getAttribute("local");
        request.setAttribute("local", local);

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);

    }
}
