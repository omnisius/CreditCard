package com.bionic.bardakov.web.filter;

import com.bionic.bardakov.web.config.ConfigurationManager;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 19.04.14
 * Time: 0:53
 */
class RedirectionWrapper extends HttpServletResponseWrapper{

    public RedirectionWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        super.sendRedirect(location);
    }
}
