package com.bionic.bardakov.web.tag;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Богдан on 24.04.2014.
 */
public class ErrorTagHandler extends TagSupport {
    private static final String PARAM_NAME_MESSAGE = "errorMessage";

    public int doStartTag() throws JspException {
        try {
            ServletRequest request = pageContext.getRequest();
            String message = (String) request.getAttribute(PARAM_NAME_MESSAGE);
            if(message == null){
                message = "unknown error";
            }

            JspWriter out = pageContext.getOut();
            out.println(message);
        } catch (IOException ex) {
            throw new JspException(ex.getMessage());
        }
        return SKIP_BODY;
    }
}
