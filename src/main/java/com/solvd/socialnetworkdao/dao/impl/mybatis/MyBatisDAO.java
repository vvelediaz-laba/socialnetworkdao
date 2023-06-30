package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.dao.impl.jdbc.ConnectionFunction;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public abstract class MyBatisDAO {
    private static final Logger logger = LogManager.getLogger(MyBatisDAO.class.getName());

    protected <T> T executeWithSession(SessionFunction<T> sessionFunction) {
        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
             SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
             return sessionFunction.apply(session);
        } catch (SQLException | IOException e){
            logger.error(e);
            return null;
        }
    }
}
