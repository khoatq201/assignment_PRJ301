/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class DateUtil {
    // Phương thức để lấy ngày từ cơ sở dữ liệu và trả về kiểu java.util.Date
    /*public static Date getCurrentTimeFromDatabase(String jdbcUrl, String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Kết nối tới cơ sở dữ liệu
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Tạo câu truy vấn để lấy thời gian hiện tại từ SQL (tùy theo loại cơ sở dữ liệu)
            String query = "";
            if (jdbcUrl.contains("mysql")) {
                query = "SELECT CURTIME() AS current_time";
            } else if (jdbcUrl.contains("postgresql")) {
                query = "SELECT CURRENT_TIME AS current_time";
            }
            
            // Tạo đối tượng Statement
            stmt = conn.createStatement();
            
            // Thực thi câu truy vấn và lấy kết quả
            rs = stmt.executeQuery(query);
            
            // Xử lý kết quả trả về
            if (rs.next()) {
                Time currentTime = rs.getTime("current_time");
                return new Date(currentTime.getTime());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và giải phóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return null; // Trả về null nếu không thành công
    }*/
    }
