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
        this.fromUserIp = fromUserIp == null ? null : fromUserIp.trim();
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
        this.beReplyContent = beReplyContent == null ? null : beReplyContent.trim();
    }
}