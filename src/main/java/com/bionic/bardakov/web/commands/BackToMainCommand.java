package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.messages.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Богдан on 27.04.2014.
 */
public class BackToMainCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("local");
        String login = (String) session.getAttribute("login");
        String smth = MessageManager.getInstance(local).getProperty(MessageManager.WELCOME_MESSAGE);
        request.setAttribute("user", login);
        request.setAttribute("smth", " " + smth);
        request.setAttribute("local", local);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}
