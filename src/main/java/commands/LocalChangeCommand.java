package commands;

import config.PageManager;

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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String local =  request.getParameter(PARAM_NAME_LOCAL);
        String path = request.getParameter(PARAM_NAME_PAGE);
        System.out.println(path);

        HttpSession session = request.getSession();
        session.setAttribute("local", local);


        return page = PageManager.getInstance().getProperty(path);
    }
}