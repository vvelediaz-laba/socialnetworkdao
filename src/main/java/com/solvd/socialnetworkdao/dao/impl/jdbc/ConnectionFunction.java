package com.solvd.socialnetworkdao.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionFunction<T> {
    T apply(Connection connection) throws SQLException;
}
