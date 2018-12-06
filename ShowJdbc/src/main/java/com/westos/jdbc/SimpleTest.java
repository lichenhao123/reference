package com.westos.jdbc;

import java.sql.*;

public class SimpleTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/test?useSSL=false";
        String username = "root";
        String password = "root";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement psmt = conn.prepareStatement("insert into b values(?)")) {
                psmt.setString(1, "😂");
                psmt.executeUpdate();
            }
            try (PreparedStatement psmt = conn.prepareStatement("select * from b")) {
                try (ResultSet rs = psmt.executeQuery()) {
                    while(rs.next()) {
                        System.out.println(rs.getString(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
