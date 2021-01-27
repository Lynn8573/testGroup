package com.qfedu.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 工具类能干嘛？
 * 1.自动完成驱动的加载
 * 2.自动完成必要的数据处理
 * 3.简化getConnection方法，提供给开发者
 * 4.完成统一的close方法
 */
public class JdbcUtil {

    //c3p0的连接池对象，就会加载默认的xml配置文件，配置文件必须写成固定能够c3p0-config.xml
    //存放的目录必须是srcx下面
    private static ComboPooledDataSource pool = new ComboPooledDataSource();

    public static Connection getConnection () {
        Connection connection = null;
        try {
            //connection = DriverManager.getConnection(url, user, password);
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * 关闭数据库连接对象
     */
    //假如statement 是null   resultSet是null
    public static void close(Connection connection) {
        try {
            close(connection, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //resultSet是null
    public static void close(Connection connection, Statement statement) {
        try {
            close(connection, statement, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

}
