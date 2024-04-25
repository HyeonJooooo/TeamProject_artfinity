package com.example.project_1.service;

import com.example.project_1.vo.CommentVo;
import com.example.project_1.vo.UserLikeVo;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class UserLikeService {

    private final String url = "jdbc:mysql://localhost:3306/javadb1";
    private final String user = "root";
    private final String password = "root";

    public UserLikeVo getUserLikeById(long userId) {
        UserLikeVo userlikeVo = null;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM userlike WHERE userId = ?")) {  // 수정

            pstmt.setLong(1, userId);  // 수정

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userlikeVo = new UserLikeVo();
                    userlikeVo.setUserId(rs.getLong("userId"));
                    userlikeVo.setUserId(rs.getLong("userId"));
                    userlikeVo.setProjectId(rs.getLong("projectId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 적절한 예외 처리를 해야 합니다.
        }
        return userlikeVo;
    }

    public void addUserLike(UserLikeVo userLikeVo) {
        String sql = "INSERT INTO userlike (userId, projectId) VALUES (?,?);";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setLong(1, userLikeVo.getUserId());
            pstmt.setLong(2, userLikeVo.getProjectId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserLikeByUserId(long userId) {
        String sql = "DELETE FROM userlike WHERE userId = ?;";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, userId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // 적절한 예외 처리와 로깅을 추가해야 합니다.
        }
    }
}
