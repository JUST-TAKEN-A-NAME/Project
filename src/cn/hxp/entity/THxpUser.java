package cn.hxp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class THxpUser {
    private Integer userId;

    private String userName;

    private String userPassword;

    private String userHeadimg;

    private Integer userExp;

    private Integer userGrade;

    private String userSig;

    private String userSummary;

    private Integer userSex;

    private Integer userTucao;

    private Integer userMubiao;

    private Integer userMbSuc;

    private double userZhangdan;

    private Date lastLogin;

    private Integer userStatus;

    private Integer userLoginCount;

    private Date userCreatetime;
    
    private String userEmail;

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
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserHeadimg() {
		return userHeadimg;
	}

	public void setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
	}

	public Integer getUserExp() {
		return userExp;
	}

	public void setUserExp(Integer userExp) {
		this.userExp = userExp;
	}

	public Integer getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(Integer userGrade) {
		this.userGrade = userGrade;
	}

	public String getUserSig() {
		return userSig;
	}

	public void setUserSig(String userSig) {
		this.userSig = userSig;
	}

	public String getUserSummary() {
		return userSummary;
	}

	public void setUserSummary(String userSummary) {
		this.userSummary = userSummary;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getUserTucao() {
		return userTucao;
	}

	public void setUserTucao(Integer userTucao) {
		this.userTucao = userTucao;
	}

	public Integer getUserMubiao() {
		return userMubiao;
	}

	public void setUserMubiao(Integer userMubiao) {
		this.userMubiao = userMubiao;
	}

	public Integer getUserMbSuc() {
		return userMbSuc;
	}

	public void setUserMbSuc(Integer userMbSuc) {
		this.userMbSuc = userMbSuc;
	}

	public double getUserZhangdan() {
		return userZhangdan;
	}

	public void setUserZhangdan(double userZhangdan) {
		this.userZhangdan = userZhangdan;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getUserLoginCount() {
		return userLoginCount;
	}

	public void setUserLoginCount(Integer userLoginCount) {
		this.userLoginCount = userLoginCount;
	}

	public Date getUserCreatetime() {
		return userCreatetime;
	}

	public void setUserCreatetime(Date userCreatetime) {
		this.userCreatetime = userCreatetime;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public THxpUser(Integer userId, String userName, String userPassword,
			String userHeadimg, Integer userExp, Integer userGrade,
			String userSig, String userSummary, Integer userSex,
			Integer userTucao, Integer userMubiao, Integer userMbSuc,
			double userZhangdan, Date lastLogin, Integer userStatus,
			Integer userLoginCount, Date userCreatetime, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userHeadimg = userHeadimg;
		this.userExp = userExp;
		this.userGrade = userGrade;
		this.userSig = userSig;
		this.userSummary = userSummary;
		this.userSex = userSex;
		this.userTucao = userTucao;
		this.userMubiao = userMubiao;
		this.userMbSuc = userMbSuc;
		this.userZhangdan = userZhangdan;
		this.lastLogin = lastLogin;
		this.userStatus = userStatus;
		this.userLoginCount = userLoginCount;
		this.userCreatetime = userCreatetime;
		this.userEmail = userEmail;
	}

	public THxpUser() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
