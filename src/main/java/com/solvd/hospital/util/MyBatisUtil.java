package com.solvd.hospital.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyBatisUtil {

    private static final Logger logger =
            Logger.getLogger(MyBatisUtil.class.getName());

    private static SqlSessionFactory factory;

    static {

        try {

            Reader reader =
                    Resources.getResourceAsReader(
                            "mybatis-config.xml"
                    );

            factory =
                    new SqlSessionFactoryBuilder()
                            .build(reader);

            logger.info("SqlSessionFactory initialized successfully.");

        } catch (Exception e) {

            logger.log(Level.SEVERE,
                    "Error while initializing SqlSessionFactory.", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {

        return factory;
    }
}