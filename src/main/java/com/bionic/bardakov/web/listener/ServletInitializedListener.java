package com.bionic.bardakov.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Богдан on 23.04.2014.
 */
public class ServletInitializedListener implements ServletRequestListener {
    private static final Logger logger = Logger.getLogger(ServletInitializedListener.class);
    private static int counter;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.info(sre+ "Destroy");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletContext context = sre.getServletContext();
        ServletRequest request = sre.getServletRequest();
        synchronized (context) {
            String name = ((HttpServletRequest) request).getRequestURI();
            logger.info("Request= " + name + "  Counter=" + ++counter);
            context.log("Request= " + name + "  Counter=" + counter);
        }
    }
}
