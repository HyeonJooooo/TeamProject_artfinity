package com.example.project_1.vo;

import java.sql.Timestamp;

public class UserVo {

    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userPhone;
    private java.sql.Timestamp JoinDate;

    public UserVo() {
    }

    public UserVo(int userId, String userName, String userEmail, String userPassword, String userPhone, Timestamp JoinDate) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.JoinDate = JoinDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public java.sql.Timestamp getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(java.sql.Timestamp joinDate) {
        this.JoinDate = joinDate;
    }
}
