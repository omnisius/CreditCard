package com.bionic.bardakov.web.dao.mydao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bionic.bardakov.web.manager.ConnectionManager;

import com.bionic.bardakov.web.dao.idao.IDAOuser;
import com.bionic.bardakov.web.entities.User;
import org.apache.log4j.Logger;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:37
 */
public class MyDAOuser implements IDAOuser {

    Connection connection = null;
    ConnectionManager connectionManager = ConnectionManager.getInstance();
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private static final Logger logger = Logger.getLogger(MyDAOuser.class);
    protected final String SQL_SELECT = "SELECT id, fio, login, password, type FROM " + getTableName() + "";

    protected static final int COLUMN_ID = 1;
    protected static final int COLUMN_FIO = 2;
    protected static final int COLUMN_LOGIN = 3;
    protected static final int COLUMN_PASSWORD = 4;
    protected static final int COLUMN_TYPE = 5;

    public MyDAOuser() {
    }

    @Override
    public boolean checkLogin(String login, String password) {
        try {
            connection = connectionManager.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE login=? AND password=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet res = st.executeQuery();
            return res.next();
        } catch (SQLException e) {
            logger.error(e);
            return false;
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public void add(User user) {
        try {
            String queryString = "INSERT INTO user(id, fio, login, password, type) VALUES(?,?,?,?,?)";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, user.getId());
            ptmt.setString(2, user.getFio());
            ptmt.setString(3, user.getLogin());
            ptmt.setString(4, user.getPassword());
            ptmt.setString(5, user.getType());            //Admin or null
            ptmt.executeUpdate();
            logger.info("Data Added Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public void updateType(String login) {
        try {
            String queryString = "UPDATE user SET type = 'admin'  WHERE login = ?";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setString(1, login);
            ptmt.executeUpdate();
            logger.info("Table Updated Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public void updatePassword(String password, String login) {
        try {
            String queryString = "UPDATE user SET password = ?  WHERE login = ?";
            connection = connectionManager.getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, password);
            ptmt.setString(2, login);
            ptmt.executeUpdate();
            logger.info("Table Updated Successfully " + getTableName());
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            connectionManager.disconnect(connection);
        }
    }

    @Override
    public boolean isAdmin(String login) {
        try {
            String type = findWhereLoginEquals(login).getType();
            return ("admin".equals(type));
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public User singleFindBy(String sql, Object[] sqlParams) throws Exception {
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
                User user = new User();
                user.setId(resultSet.getLong(COLUMN_ID));
                user.setFio(resultSet.getString(COLUMN_FIO));
                user.setLogin(resultSet.getString(COLUMN_LOGIN));
                user.setPassword(resultSet.getString(COLUMN_PASSWORD));
                user.setType(resultSet.getString(COLUMN_TYPE));
                return user;
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
    public User findWhereLoginEquals(String login) throws Exception {
        return singleFindBy(SQL_SELECT + " WHERE login = ? ", new Object[]{login});
    }

    public String getTableName() {
        return "user";
    }
}

