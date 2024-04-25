package com.example.project_1.service;

import com.example.project_1.vo.UserVo;
import org.springframework.stereotype.Service;
import java.sql.*;

@Service
public class UserService {

    private final String url = "jdbc:mysql://localhost:3306/javadb1";
    private final String user_sql = "root";
    private final String password = "root";

    public UserVo login(String userEmail, String inputPassword) {

        UserVo user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(url, user_sql, password);

            // SQL 쿼리 준비
            String sql = "SELECT userId, userName, userPassword FROM user WHERE userEmail = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);

            // 쿼리 실행
            rs = pstmt.executeQuery();

            // 결과 처리
            if (rs.next()) {
                String storedPassword = rs.getString("userPassword");
                if (inputPassword.equals(storedPassword)) {

                    user = new UserVo();
                    user.setUserId(rs.getInt("userId"));
                    user.setUserName(rs.getString("userName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리는 로깅 라이브러리로 로그를 남겨야 합니다.
        } finally {
            // 자원 정리
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // 예외 처리는 로깅 라이브러리로 로그를 남겨야 합니다.
            }
        }

        return user; // UserVo 객체 반환 또는 null
    }


    public void signup(UserVo userVo) {
        String sql = "INSERT INTO user (userName, userEmail, userPassword, userPhone, JoinDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user_sql, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userVo.getUserName());
            pstmt.setString(2, userVo.getUserEmail());
            pstmt.setString(3, userVo.getUserPassword());
            pstmt.setString(4, userVo.getUserPhone());
            pstmt.setTimestamp(5, userVo.getJoinDate());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // 실제 프로젝트에서는 적절한 예외 처리가 필요합니다.
        }
    }

    public UserVo getUserById(Long userId) {
        UserVo userVo = null;
        try (Connection conn = DriverManager.getConnection(url, user_sql, password);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE userId = ?")) {

            pstmt.setLong(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userVo = new UserVo();
                    userVo.setUserId(rs.getInt("userId"));
                    userVo.setUserName(rs.getString("userName"));
                    userVo.setUserEmail(rs.getString("userEmail"));
                    userVo.setUserPassword(rs.getString("userPassword"));
                    userVo.setUserPhone(rs.getString("userPhone"));
                    userVo.setJoinDate(rs.getTimestamp("JoinDate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 적절한 예외 처리를 해야 합니다.
        }
        return userVo;
    }

}
