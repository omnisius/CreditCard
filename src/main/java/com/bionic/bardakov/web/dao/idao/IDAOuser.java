package com.bionic.bardakov.web.dao.idao;

import com.bionic.bardakov.web.entities.User;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:35
 */
public interface IDAOuser {
    boolean checkLogin(String login, String password);
    public void add(User user) throws Exception;
    boolean isAdmin(String login);
    public Object findWhereLoginEquals(String login) throws Exception;
    void updateType(String login);
    void updatePassword(String password, String login);

}