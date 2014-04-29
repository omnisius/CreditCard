/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.bardakov.web.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bionic.bardakov.web.commands.ActionCommand;
import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.messages.MessageManager;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 17:09
 */
public class Controller extends HttpServlet {
    ActionFactory requestHelper = ActionFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                                    throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("local");

        try {
            ActionCommand command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e){
            e.printStackTrace();
            request.setAttribute("errorMessage", MessageManager.getInstance(local).getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);

        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
