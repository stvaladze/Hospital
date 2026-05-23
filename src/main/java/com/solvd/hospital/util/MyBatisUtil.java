package com.solvd.hospital.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MyBatisUtil {

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

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {

        return factory;
    }
}