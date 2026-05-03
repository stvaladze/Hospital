package com.solvd.hospital.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyBatisUtil {

    private static final Logger logger =
            Logger.getLogger(MyBatisUtil.class.getName());

    private static SqlSessionFactory factory;

    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
            logger.info("MyBatis SqlSessionFactory created");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to create SqlSessionFactory", e);
        }
    }

    private MyBatisUtil() {}

    public static SqlSessionFactory getFactory() {
        return factory;
    }
}