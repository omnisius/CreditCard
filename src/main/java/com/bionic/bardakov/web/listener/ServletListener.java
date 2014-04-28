package com.bionic.bardakov.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by Богдан on 23.04.2014.
 */
public class ServletListener implements HttpSessionAttributeListener {
    private static final Logger logger = Logger.getLogger(ServletListener.class);
    private String counterName = "counter";

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = event.getName();
        String url = "URL";

        if (name.equals(counterName)) {
            Integer currentName = (Integer) event.getValue();
            logger.info("add Counter=" + currentName);
        } else if (name.equals(url)) {
            String currentURL = (String) event.getValue();
            logger.info("add URL");
        } else {
            logger.info("add attribute");
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if(event.getName().equals(counterName)){
            logger.info("changed counter=" + event.getValue());
        }
    }
}
