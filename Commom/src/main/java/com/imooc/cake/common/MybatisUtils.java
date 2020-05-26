package com.imooc.cake.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Mybatis的工具类
 * @author passionlife
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            String source = "config.xml";
            reader= Resources.getResourceAsReader(source);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开会话的方法
     * @return
     */
    public static SqlSession openSession(){

        return sqlSessionFactory.openSession();
    }

}
