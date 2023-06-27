package com.solvd.socialnetworkdao.dao.impl.mybatis;

import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public interface SessionFunction<T> {
    T apply(SqlSession session) throws SQLException;
}
