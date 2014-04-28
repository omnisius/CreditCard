package com.bionic.bardakov.web.dao.daofactory;

import com.bionic.bardakov.web.dao.mydao.MyDAOaccount;
import com.bionic.bardakov.web.dao.mydao.MyDAOcard;
import com.bionic.bardakov.web.dao.mydao.MyDAOpayment;
import com.bionic.bardakov.web.dao.mydao.MyDAOuser;

/**
 * User: ${Bogdan}
 * Date: 30.03.14
 * Time: 1:49
 */
public class MySQLDAOFactory {

    public static MyDAOuser getMyDAOuser(){
        return new MyDAOuser();
    }

    public static MyDAOcard getMyDAOcard(){
        return new MyDAOcard();
    }

    public static MyDAOpayment getMyDAOpayment(){
        return new MyDAOpayment();
    }

    public static MyDAOaccount getMyDAOaccount(){
        return new MyDAOaccount();
    }
}
