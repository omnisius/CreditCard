package com.bionic.bardakov.web.testers;

import com.bionic.bardakov.web.dao.daofactory.MySQLDAOFactory;

/**
 * User: ${Bogdan}
 * Date: 14.04.14
 * Time: 0:06
 */
public class TesterDB {
    public static void main(String[] args) throws Exception {
        System.out.println(MySQLDAOFactory.getMyDAOaccount().findWhereAccountNumberEquals(1265982601).getStatus());
        MySQLDAOFactory.getMyDAOuser().updateType("root");
        System.out.println(MySQLDAOFactory.getMyDAOuser().findWhereLoginEquals("лох"));
        System.out.println(MySQLDAOFactory.getMyDAOuser().isAdmin("лох"));
    }
}
