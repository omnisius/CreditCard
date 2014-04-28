package com.bionic.bardakov.web.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * User: ${Bogdan}
 * Date: 17.04.14
 * Time: 22:28
 */
public class CardTagHandler extends BodyTagSupport {
    private int size;

    public void setSize(String number) {
        this.size = new Integer(number);
    }

    public int doStartTag() throws JspTagException {
        try {
            pageContext.getOut().write("<TABLE BORDER=\"2\" WIDTH=\"50%\" HEIGHT=\"100%\" >");
            pageContext.getOut().write("<TR><TD>");
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    public int doAfterBody() throws JspTagException{
        if(size-- > 1){
            try {
                pageContext.getOut().write("</TD></TR><TR><TD>");
                pageContext.getOut().write("</TD></TR><TR><TD>");
            } catch (IOException ex) {
                throw new JspTagException(ex.getMessage());
            }
            return EVAL_BODY_INCLUDE;
        }else{
            return SKIP_BODY;
        }
    }

    public int doEndTag() throws JspTagException {
        try{
            pageContext.getOut().write("</TD></TR>");
            pageContext.getOut().write("</TABLE>");
        }catch(IOException e){
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
