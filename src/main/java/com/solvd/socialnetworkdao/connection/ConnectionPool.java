package com.solvd.socialnetworkdao.connection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool{
    private static ConnectionPool connectionPool;

    private final int poolSize;
    private final BlockingQueue<Connection> connections;
    private final Set<Connection> initializedConnections;

    private ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.connections = new LinkedBlockingQueue<>(poolSize);
        this.initializedConnections = new HashSet<>();
    }

    public synchronized static ConnectionPool getInstance(int poolSize) {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool(poolSize);
        }
        return connectionPool;
    }

    public synchronized Connection getConnection() throws InterruptedException, IOException, SQLException {
        // retrieve and remove available connection
        Connection connection = connections.poll();
        // if no connection was available, initialize new connection if less than poolsize
        // else, wait for new connection to open
        if (connection == null) {
            if (initializedConnections.size() < poolSize) {
                Properties props = new Properties();
                try(InputStream input = Files.newInputStream(Paths.get("src/main/resources/db.properties"))){
                    props.load(input);
                }
                connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
                initializedConnections.add(connection);
            } else {
                connection = connections.take();
            }
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null && initializedConnections.contains(connection)) {
            connections.offer(connection);
        }
    }
}


