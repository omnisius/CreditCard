package com.bionic.bardakov.web.manager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: ${Bogdan}
 * Date: 03.04.14
 * Time: 21:55
 */
public class ConnectionManager {
    private static ConnectionManager instance;
    private ConnectionPool connectionPool = new ConnectionPool();

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private static final Logger logger = Logger.getLogger(ConnectionManager.class);

    private ConnectionManager(){}
    
    public static ConnectionManager getInstance(){
        if (instance == null){
            instance = new ConnectionManager();
            return instance;
        } else {
            return instance;
        }
    }
    
    public Connection getConnection() throws SQLException{
        return connectionPool.getConnection();
    }

    public void freeConnection(Connection connection){
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void disconnect(Connection connection) {
          try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e, e);
            } catch (Exception e) {
                logger.error(e, e);
            }
           freeConnection(connection);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
