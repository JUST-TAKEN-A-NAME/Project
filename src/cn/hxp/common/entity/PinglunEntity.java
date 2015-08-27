package cn.hxp.common.entity;

import java.util.List;

import cn.hxp.entity.BolgPinglunBereply;



public class PinglunEntity {
	
	private String userHeadImg;
	private int commentId;
	private int userId;
	private String userName;
	private String commentDate;
	private String comment;
	private List<BolgPinglunBereply> bereplyList;
	public String getUserHeadImg() {
		return userHeadImg;
	}
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<BolgPinglunBereply> getBereplyList() {
		return bereplyList;
	}
	public void setBereplyList(List<BolgPinglunBereply> bereplyList) {
		this.bereplyList = bereplyList;
	}
	public PinglunEntity(String userHeadImg, int commentId, int userId,
			String userName, String commentDate, String comment,
			List<BolgPinglunBereply> bereplyList) {
		super();
		this.userHeadImg = userHeadImg;
		this.commentId = commentId;
		this.userId = userId;
		this.userName = userName;
		this.commentDate = commentDate;
		this.comment = comment;
		this.bereplyList = bereplyList;
	}
	public PinglunEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
