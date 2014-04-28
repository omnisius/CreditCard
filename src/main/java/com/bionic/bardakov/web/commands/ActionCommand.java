package com.bionic.bardakov.web.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 09.04.14
 * Time: 13:51
 */
public interface ActionCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}


