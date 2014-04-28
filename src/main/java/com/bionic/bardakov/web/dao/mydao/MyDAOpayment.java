package com.bionic.bardakov.web.dao.mydao;

import com.bionic.bardakov.web.manager.ConnectionManager;
import com.bionic.bardakov.web.dao.idao.IDAOpayment;
import com.bionic.bardakov.web.entities.Payment;
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
public class MyDAOpayment implements IDAOpayment {

    Connection connection = null;
    ConnectionManager connectionManager = ConnectionManager.getInstance();
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private static final Logger logger = Logger.getLogger(MyDAOpayment.class);
    protected final String SQL_SELECT = "SELECT idpayment, payment_sum, payment_from_account FROM " + getTableName() + "";

    protected static final int COLUMN_IDPAYMENT = 1;
    protected static final int COLUMN_PAYMENT_SUM = 2;
    protected static final int COLUMN_USERS_ID = 3;

    public MyDAOpayment() {
    }

    @Override
    public void add(Payment payment) {
        try {
            String queryString = "INSERT INTO payment(payment_sum, payment_from_account) VALUES(?,?)";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setFloat(1, payment.getSum());
            ptmt.setLong(2, payment.getPaymentFromAccount());
            ptmt.executeUpdate();
            logger.info("Data Added Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public void update(Payment payment) {
        try {
            String queryString = "UPDATE payment SET payment_sum = payment_sum + ? WHERE payment_from_account = ?";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setFloat(1, payment.getSum());
            ptmt.setLong(1, payment.getPaymentFromAccount());
            ptmt.executeUpdate();
            logger.info("Table Updated Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    public Payment[] findBy(String sql, Object[] sqlParams) throws Exception {
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
            resultSet= ptmt.executeQuery();

            // fetch the results
            List resultList = new ArrayList();
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setId(resultSet.getInt(COLUMN_IDPAYMENT));
                payment.setSum(resultSet.getFloat(COLUMN_PAYMENT_SUM));
                payment.setPaymentFromAccount(resultSet.getLong(COLUMN_USERS_ID));
                resultList.add(payment);
            }
            Payment ret[] = new Payment[resultList.size()];
            resultList.toArray(ret);
            return ret;
        } catch (Exception ex) {
            logger.error(ex);
            throw new Exception("Exception: " + ex.getMessage(), ex);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public Payment[] findWherePaymentFromAccountEquals(long paymentFromAccount) throws Exception {
        return findBy(SQL_SELECT + " WHERE payment_from_account = ? ORDER BY payment_from_account", new Object[]{paymentFromAccount});
    }

    public String getTableName() {
        return "payment";
    }

}
