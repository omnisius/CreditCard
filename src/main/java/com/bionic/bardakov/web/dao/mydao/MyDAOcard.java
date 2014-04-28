package com.bionic.bardakov.web.dao.mydao;

import com.bionic.bardakov.web.manager.ConnectionManager;
import com.bionic.bardakov.web.dao.idao.IDAOcard;
import com.bionic.bardakov.web.entities.Card;
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
 * Time: 20:37
 */
public class MyDAOcard implements IDAOcard {
    Connection connection = null;
    ConnectionManager connectionManager = ConnectionManager.getInstance();
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private static final Logger logger = Logger.getLogger(MyDAOcard.class);
    protected final String SQL_SELECT = "SELECT idcard, card_number, users_id FROM " + getTableName() + "";

    protected static final int COLUMN_CARD_NUMBER = 2;
    protected static final int COLUMN_USERS_ID = 3;

    public MyDAOcard() {
    }

    @Override
    public void add(Card card) {
        try {
            String queryString = "INSERT INTO card(card_number, users_id) VALUES(?,?)";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, card.getCardNumber());
            ptmt.setLong(2, card.getUserId());
            ptmt.executeUpdate();
            logger.info("Data Added Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            ConnectionManager.getInstance().disconnect(connection);
        }
    }

    @Override
    public void update(Card card) {
        try {
            String queryString = "UPDATE card SET card_number = ?  WHERE users_id =?";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, card.getCardNumber());
            ptmt.setLong(2, card.getUserId());
            ptmt.executeUpdate();
            logger.info("Table Updated Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public boolean isCardNumberInDB (long cardNumber) throws Exception {
        return (findWhereCardNumberEquals(cardNumber) == null);
    }

    public Card[] findBy(String sql, Object[] sqlParams) throws Exception {
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
                Card card = new Card();
                card.setCardNumber(resultSet.getLong(COLUMN_CARD_NUMBER));
                card.setUserId(resultSet.getLong(COLUMN_USERS_ID));
                resultList.add(card);
            }
             Card ret[] = new Card[resultList.size()];
            resultList.toArray(ret);
            return ret;
        } catch (Exception ex) {
            logger.error(ex);
            throw new Exception("Exception: " + ex.getMessage(), ex);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    public Card singleFindBy(String sql, Object[] sqlParams) throws Exception {
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
                Card card = new Card();
                card.setCardNumber(resultSet.getLong(COLUMN_CARD_NUMBER));
                card.setUserId(resultSet.getLong(COLUMN_USERS_ID));
                return card;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error(ex);
            return null;
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public Card[] findAll() throws Exception{
        return findBy(SQL_SELECT, null);
    }

    @Override
    public Card findWhereCardNumberEquals(long cardNumber) throws Exception {
        return singleFindBy(SQL_SELECT + " WHERE card_number = ? ORDER BY card_number", new Object[]{cardNumber});
    }

    @Override
    public Card[] findWhereUsersIdEquals(long usersId) throws Exception {
        return findBy(SQL_SELECT + " WHERE users_id = ? ORDER BY users_id", new Object[]{usersId});
    }

    public String getTableName() {
        return "card";
    }
}
