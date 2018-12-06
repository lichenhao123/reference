package com.westos.jdbc;

import java.sql.*;

public class CreateTable {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/test?useSSL=false";
        String username = "root";
        String password = "root";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            System.out.println(databaseMetaData.getJDBCMajorVersion()+"."+databaseMetaData.getJDBCMinorVersion());
            ResultSet tables = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while(tables.next()) {
                System.out.println(tables.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
