package cn.hxp.entity;

import java.util.Date;

public class BolgUser {
    private Integer userId;

    private String userName;

    private String userHeadImg;

    private String userEmail;

    private Integer userLoginErrCount;

    private Date userLastLogin;

    private Integer userState;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg == null ? null : userHeadImg.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getUserLoginErrCount() {
        return userLoginErrCount;
    }

    public void setUserLoginErrCount(Integer userLoginErrCount) {
        this.userLoginErrCount = userLoginErrCount;
    }

    public Date getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(Date userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }
}