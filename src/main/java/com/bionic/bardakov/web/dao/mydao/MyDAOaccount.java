package com.bionic.bardakov.web.dao.mydao;

import com.bionic.bardakov.web.manager.ConnectionManager;

import com.bionic.bardakov.web.dao.idao.IDAOaccount;
import com.bionic.bardakov.web.entities.Account;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:38
 */
public class MyDAOaccount implements IDAOaccount {
    Connection connection = null;
    ConnectionManager connectionManager = ConnectionManager.getInstance();
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private static final Logger logger = Logger.getLogger(MyDAOaccount.class);
    protected final String SQL_SELECT = "SELECT idaccount, money, status, account_number, account_card FROM " + getTableName() + "";

    protected static final int COLUMN_ACCOUNT_ID = 1;
    protected static final int COLUMN_MONEY = 2;
    protected static final int COLUMN_STATUS = 3;
    protected static final int COLUMN_ACCOUNT_NUMBER = 4;
    protected static final int COLUMN_ACCOUNT_CARD = 5;

    public MyDAOaccount() {
    }

    @Override
    public void add(Account account) {
        try {
            String queryString = "INSERT INTO account(money, status, account_number, account_card) VALUES(?,?,?,?)";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setFloat(1, account.getMoney());
            ptmt.setString(2, account.getStatus());        //Only Admin can do it
            ptmt.setLong(3, account.getAccountNumber());
            ptmt.setLong(4, account.getCardNumber());

            ptmt.executeUpdate();
            logger.info("Data Added Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public void update(Account account) {
        try {
            String queryString = "UPDATE account SET money = ?, SET status = ?, SET account_number = ? WHERE account_card =?";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setFloat(1, account.getMoney());
            ptmt.setString(2, account.getStatus());        //Only Admin can do it
            ptmt.setLong(3, account.getAccountNumber());
            ptmt.setLong(4, account.getCardNumber());
            ptmt.executeUpdate();
            logger.info("Table Updated Successfully "+getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public void updateStatus(long accountNumber, String status) {
        try {
            String queryString = "UPDATE account SET status =? WHERE account_number = ?";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, status);
            ptmt.setLong(2, accountNumber);
            ptmt.executeUpdate();
            logger.info("Table Updated Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public void updateMoney(long accountNumber, float money) {
        try {
            String queryString = "UPDATE account SET money = (money + ?) WHERE account_number = ?";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setFloat(1, money);
            ptmt.setLong(2, accountNumber);
            ptmt.executeUpdate();
            logger.info("Table Updated Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public boolean checkStatusIsUnlock(long accountNumber) throws Exception {
        boolean ans = false;
        try {
            Account[] findWhereStatusEquals = findWhereStatusEquals("unlock");
            for (Account account : findWhereStatusEquals) {
                return ans = (account != findWhereAccountNumberEquals(accountNumber));
            }

        } catch (Exception ex) {
            logger.error(ex);
            throw new Exception("Exception: " + ex.getMessage(), ex);
        } finally {
            connectionManager.disconnect(connection);
        }
        return ans;
    }

    @Override
    public boolean compareMoney(long accountNumber, float moneySum) throws Exception {
        Account account = findWhereAccountNumberEquals(accountNumber);
        long money = (long) account.getMoney();
        return ( money >  moneySum);
    }

    @Override
    public boolean isAcountNumberInDB (long accountNumber) throws Exception {
        return (findWhereAccountNumberEquals(accountNumber) == null);
        }

    public Account[] findBy(String sql, Object[] sqlParams) throws Exception {
        try {
            connection = connectionManager.getConnection();
            // construct the SQL statement
            final String SQL = sql;

            logger.info("Executing " + SQL);
            // prepare statement
            ptmt = connection.prepareStatement(SQL);
            // bind parameters
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                ptmt.setObject(i + 1, sqlParams[i]);
            }
            resultSet = ptmt.executeQuery();

            // fetch the results
            List resultList = new ArrayList();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt(COLUMN_ACCOUNT_ID));
                account.setMoney(resultSet.getFloat(COLUMN_MONEY));
                account.setStatus(resultSet.getString(COLUMN_STATUS));
                account.setAccountNumber(resultSet.getLong(COLUMN_ACCOUNT_NUMBER));
                account.setCardNumber(resultSet.getLong(COLUMN_ACCOUNT_CARD));
                resultList.add(account);
            }
            Account ret[] = new Account[resultList.size()];
            resultList.toArray(ret);
            return ret;
        } catch (Exception ex) {
            logger.error(ex);
            throw new Exception("Exception: " + ex.getMessage(), ex);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    public Account singleFindBy(String sql, Object[] sqlParams) throws Exception {
        try {

            connection = connectionManager.getConnection();
            // construct the SQL statement
            final String SQL = sql;

            logger.info("Executing " + SQL);
            // prepare statement
            ptmt = connection.prepareStatement(SQL);
            // bind parameters
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                ptmt.setObject(i + 1, sqlParams[i]);
            }
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt(COLUMN_ACCOUNT_ID));
                account.setMoney(resultSet.getFloat(COLUMN_MONEY));
                account.setStatus(resultSet.getString(COLUMN_STATUS));
                account.setAccountNumber(resultSet.getLong(COLUMN_ACCOUNT_NUMBER));
                account.setCardNumber(resultSet.getLong(COLUMN_ACCOUNT_CARD));
                return account;
            } else {

                return null;
            }

        } catch (Exception ex) {
            logger.error(ex);
            throw new Exception("Exception: " + ex.getMessage(), ex);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public Account[] findWhereStatusEquals(String status) throws Exception {
        return findBy(SQL_SELECT + " WHERE status = ? ORDER BY status", new Object[]{status});
    }

    @Override
    public Account findWhereAccountNumberEquals(long accountNumber) throws Exception {
        return singleFindBy(SQL_SELECT + " WHERE account_number = ? ORDER BY account_number", new Object[]{accountNumber});
    }

    @Override
    public Account findWhereAccountCardEquals(long accountCard) throws Exception {
        return singleFindBy(SQL_SELECT + " WHERE account_card = ? ORDER BY account_card", new Object[]{accountCard});
    }

    public String getTableName() {
        return "account";
    }
}
