package com.zuk.connection;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    DataSource ds;


    public ConnectionManager() {

        try {

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/My_DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {

        Connection connection = null;

        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }
}