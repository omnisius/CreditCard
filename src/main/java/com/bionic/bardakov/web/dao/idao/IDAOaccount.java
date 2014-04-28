package com.bionic.bardakov.web.dao.idao;

import com.bionic.bardakov.web.entities.Account;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:36
 */
public interface IDAOaccount {
    public void add(Account account);
    public void update(Account account);
    boolean checkStatusIsUnlock(long accountNumber) throws Exception;
    boolean compareMoney(long accountNumber, float moneySum) throws Exception;
    boolean isAcountNumberInDB(long accountNumber) throws Exception;
    public Account[] findWhereStatusEquals(String status) throws Exception;
    public Account findWhereAccountNumberEquals(long accountNumber) throws Exception;
    public Account findWhereAccountCardEquals(long accountCard) throws Exception;
    void updateStatus(long accountNumber, String status);
    void updateMoney(long accountNumber, float money);
}
