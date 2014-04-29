package com.bionic.bardakov.web.commands;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.messages.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Богдан on 24.04.2014.
 */
public class LocalChangeCommand implements ActionCommand {
    private static final String PARAM_NAME_LOCAL = "local";
    private static final String PARAM_NAME_PAGE = "page";
    private static final String PARAM_NAME_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String local =  request.getParameter(PARAM_NAME_LOCAL);
        String path = request.getParameter(PARAM_NAME_PAGE);
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
//        String smth = MessageManager.getInstance(local).getProperty(MessageManager.LOCAL_MESSAGE);

        request.setAttribute("user", login);
        request.setAttribute("page", path);
        request.setAttribute("smth", "");

        HttpSession session = request.getSession(true);
        session.setAttribute("local", local);
        request.setAttribute("local", local);

        return page = ConfigurationManager.getInstance().getProperty(path);
    }
}
