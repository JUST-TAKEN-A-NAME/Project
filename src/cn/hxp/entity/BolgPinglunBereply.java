package cn.hxp.entity;

import java.util.Date;

public class BolgPinglunBereply {
    private Integer beReplyId;

    private Integer bolgPinglunId;

    private Integer fromUserId;

    private String fromUserIp;

    private Date beReplyDate;

    private Integer toUserId;

    private String beReplyContent;
    
    private String fromUserHeadImg;//额外必须字段
    
    private String fromUserName;//翻译字段

    private String toUserName;//翻译字段
    
    private String beReplyDateString;//翻译字段

	public Integer getBeReplyId() {
		return beReplyId;
	}

	public void setBeReplyId(Integer beReplyId) {
		this.beReplyId = beReplyId;
	}

	public Integer getBolgPinglunId() {
		return bolgPinglunId;
	}

	public void setBolgPinglunId(Integer bolgPinglunId) {
		this.bolgPinglunId = bolgPinglunId;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserIp() {
		return fromUserIp;
	}

	public void setFromUserIp(String fromUserIp) {
		this.fromUserIp = fromUserIp;
	}

	public Date getBeReplyDate() {
		return beReplyDate;
	}

	public void setBeReplyDate(Date beReplyDate) {
		this.beReplyDate = beReplyDate;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public String getBeReplyContent() {
		return beReplyContent;
	}

	public void setBeReplyContent(String beReplyContent) {
		this.beReplyContent = beReplyContent;
	}

	public String getFromUserHeadImg() {
		return fromUserHeadImg;
	}

	public void setFromUserHeadImg(String fromUserHeadImg) {
		this.fromUserHeadImg = fromUserHeadImg;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getBeReplyDateString() {
		return beReplyDateString;
	}

	public void setBeReplyDateString(String beReplyDateString) {
		this.beReplyDateString = beReplyDateString;
	}
    
	

    
}