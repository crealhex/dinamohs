package com.dinamohs.webpage.services.couriers;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class Gatekeeper {

    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static int MAX_POOL_SIZE;
    private static int IDLE_TIME_POOL;

    private final static String JDBC_FILE_NAME = "connection";
    private static Driver driver = null;

    public static synchronized Connection open() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void loadProperties(String file) {
        Properties props = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration<String> enu = bundle.getKeys();
        String key;

        while (enu.hasMoreElements()) {
            key = enu.nextElement();
            props.put(key, bundle.getObject(key));
        }

        JDBC_DRIVER = props.getProperty("DRIVER");
        JDBC_URL = props.getProperty("URL");
        JDBC_USER = props.getProperty("USERNAME");
        JDBC_PASS = props.getProperty("PASSWORD");
        MAX_POOL_SIZE = Integer.parseInt(props.getProperty("MAX_POOL_SIZE"));
        IDLE_TIME_POOL = Integer.parseInt(props.getProperty("IDLE_TIME"));

//        TODO return-> properties;
    }

    public static DataSource getDataSource() {
        loadProperties(JDBC_FILE_NAME);
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(JDBC_DRIVER);
        source.setUrl(JDBC_URL);
        source.setUsername(JDBC_USER);
        source.setPassword(JDBC_PASS);
        source.setMaxActive(MAX_POOL_SIZE);
        source.setMaxIdle(IDLE_TIME_POOL);
        return source;
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet result) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
