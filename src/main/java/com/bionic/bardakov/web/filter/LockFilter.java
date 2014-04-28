package com.bionic.bardakov.web.filter;

import com.bionic.bardakov.web.config.ConfigurationManager;
import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Богдан on 21.04.2014.
 */
public class LockFilter implements Filter {
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

        String accountNumberStr = (String) httpRequest.getSession().getAttribute("accountNumber");

        try {
        if(accountNumberStr!=null) {
            Long accountNumber = Long.parseLong(accountNumberStr);
            String status = MySQLDAOFactory.getMyDAOaccount().findWhereAccountNumberEquals(accountNumber).getStatus();
            if ("lock".equals(status)) {
                String lockPath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOCK_PATH);
                wrapResponse.sendRedirect(lockPath);
//                SessionLogic.saveAccountNumber(httpRequest, wrapResponse, null);
                String login = (String) httpRequest.getSession().getAttribute("login");
                System.out.println(login);
//                chain.doFilter(request, wrapResponse);
//                ((HttpServletRequest) request).getSession().invalidate();
//                ((HttpServletRequest) request).getSession(true);
//                SessionLogic.saveLogin(httpRequest, (HttpServletResponse)response, login);
            } else {
//                System.out.println("com.bionic.bardakov.web.filter is working badly");
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
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
