package cn.hxp.common.entity;



public class PinglunEntity {
	
	private String userHeadImg;
	private String commentId;
	private String userName;
	private String commentDate;
	private String comment;
	public String getUserHeadImg() {
		return userHeadImg;
	}
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
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
	public PinglunEntity(String userHeadImg, String commentId, String userName,
			String commentDate, String comment) {
		super();
		this.userHeadImg = userHeadImg;
		this.commentId = commentId;
		this.userName = userName;
		this.commentDate = commentDate;
		this.comment = comment;
	}
	
	
	
}
