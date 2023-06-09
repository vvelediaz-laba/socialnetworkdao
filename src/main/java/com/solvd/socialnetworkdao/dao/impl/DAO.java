package com.solvd.socialnetworkdao.dao.impl;

import com.solvd.socialnetworkdao.connection.ConnectionPool;
import com.solvd.socialnetworkdao.dao.ConnectionFunction;
import com.solvd.socialnetworkdao.dao.IDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO {
    private static final Logger logger = LogManager.getLogger(DAO.class.getName());
    private ConnectionPool connectionPool;

    public DAO() {
        connectionPool = ConnectionPool.getInstance(5);
    }

    protected <T> T executeWithConnection(ConnectionFunction<T> connectionFunction) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            return connectionFunction.apply(connection);
        } catch (SQLException | IOException | InterruptedException e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
