
package com.bionic.bardakov.web.manager;

import com.bionic.bardakov.web.config.ConfigurationManager;
import org.apache.log4j.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * User: ${Bogdan}
 * Date: 03.04.14
 * Time: 21:55
 */
 public class ConnectionPool {

    Queue<Connection> connectionFreeQueue;
    Queue<Connection> connectionBusyQueue;
    String connectionUrl;

    {
        try {
            ConfigurationManager manager = ConfigurationManager.getInstance();
            String drv = manager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);
            Class.forName(drv);
            connectionUrl = manager.getProperty(ConfigurationManager.DATABASE_URL);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ConnectionPool.class.getName());
        }
    }

    public ConnectionPool() {
        connectionFreeQueue = new LinkedList<Connection>();
        connectionBusyQueue = new LinkedList<Connection>();
    }

    public Connection getConnection() throws SQLException {
        int MAX_BUSY = 100;
        if (connectionBusyQueue.size() < MAX_BUSY) {
            if (connectionFreeQueue.isEmpty()) {
                Connection connection = DriverManager.getConnection(connectionUrl);
                connectionFreeQueue.add(connection);
            }
            Connection connection = connectionFreeQueue.poll();
            connectionBusyQueue.add(connection);
            return connection;
        } else {
            throw new SQLException("Number of connections exceeded");
        }
    }
}
