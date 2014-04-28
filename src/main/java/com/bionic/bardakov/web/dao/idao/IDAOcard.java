package com.bionic.bardakov.web.dao.idao;

import com.bionic.bardakov.web.entities.Card;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:35
 */
public interface IDAOcard {
    public void add(Card card);
    public void update(Card card);
    boolean isCardNumberInDB(long cardNumber) throws Exception;
    public Card[] findAll() throws Exception;
    Card[] findWhereUsersIdEquals(long usersId) throws Exception;
    Card findWhereCardNumberEquals(long cardNumber) throws Exception;
}
