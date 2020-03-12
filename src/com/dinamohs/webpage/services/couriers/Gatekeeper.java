package com.dinamohs.webpage.services.couriers;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * This helper is prepared to handle, open and close database connections.
 * @author Luis Enco (crealhex)
 */
public class Gatekeeper {

    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static int MAX_POOL_SIZE;
    private static int IDLE_TIME_POOL;

    /**
     * Name of the file that contains all the connection information.
     */
    private final static String JDBC_FILE_NAME = "connection";

    /**
     * Allows to open a new connection to be used in most cases to obtain
     * data from the database.
     * @return Established connection.
     * @throws SQLException In case of errors to open the connection.
     */
    public static synchronized Connection open() throws SQLException {
        return getDataSource().getConnection();
    }

    /**
     * Allows handle the connection values extracted from a file.
     * @param file name of the file with <code>.properties</code> extension.
     */
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
    }

    /**
     * Generates a DataSource using the properties handler considering
     * the values of a connection pool.
     * @see Gatekeeper#loadProperties(String)
     * @return DataSource ready for connection.
     */
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

    /**
     * Closes all instances to prevent memory leakage.
     * @param connection current connection oppened.
     * @param statement current statement in use.
     * @param result current result with established data.
     */
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
