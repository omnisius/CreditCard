package com.bionic.bardakov.web.filter;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: ${Bogdan}
 * Date: 18.04.14
 * Time: 0:25
 */
public class AdminFilter implements Filter  {
    private FilterConfig filterConfig;
    private ServletContext ctx;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        ctx = filterConfig.getServletContext();
        ctx.log(filterConfig.getFilterName() + " initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        RedirectionWrapper wrapResponse = new RedirectionWrapper((HttpServletResponse) response);

        String uriPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        String uriAdminAccountPath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_ACCOUNT_PAGE_PATH);


        String toaccount = (String) request.getAttribute("command");
        String login = (String) httpRequest.getSession().getAttribute("login");
        System.out.println(toaccount);
        try {

            boolean isAdmin = MySQLDAOFactory.getMyDAOuser().isAdmin(login);
            if(isAdmin&&toaccount.equals("to account")){

                wrapResponse.sendRedirect(uriAdminAccountPath);
                chain.doFilter(request,wrapResponse);
            }else {
                System.out.println("com.bionic.bardakov.web.filter is working badly");
                chain.doFilter(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
