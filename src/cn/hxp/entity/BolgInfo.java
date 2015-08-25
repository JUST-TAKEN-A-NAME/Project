package cn.hxp.entity;

import java.util.Date;

public class BolgInfo {
    private Integer bolgId;

    private String bolgUserId;

    private String bolgTitle;

    private String bolgTags;

    private Integer bolgType;

    private String bolgSummary;

    private Date bolgCreatetime;

    private Integer bolgCommentCount;

    private Integer bolgLikesCount;

    private Integer bolgBrowse;

    private Date bolgLastEditTime;

    private Integer bolgIsReport;

    private Integer bolgGrade;

    public Integer getBolgId() {
        return bolgId;
    }

    public void setBolgId(Integer bolgId) {
        this.bolgId = bolgId;
    }

    public String getBolgUserId() {
        return bolgUserId;
    }

    public void setBolgUserId(String bolgUserId) {
        this.bolgUserId = bolgUserId;
    }

    public String getBolgTitle() {
        return bolgTitle;
    }

    public void setBolgTitle(String bolgTitle) {
        this.bolgTitle = bolgTitle == null ? null : bolgTitle.trim();
    }

    public String getBolgTags() {
        return bolgTags;
    }

    public void setBolgTags(String bolgTags) {
        this.bolgTags = bolgTags == null ? null : bolgTags.trim();
    }

    public Integer getBolgType() {
        return bolgType;
    }

    public void setBolgType(Integer bolgType) {
        this.bolgType = bolgType;
    }

    public String getBolgSummary() {
        return bolgSummary;
    }

    public void setBolgSummary(String bolgSummary) {
        this.bolgSummary = bolgSummary == null ? null : bolgSummary.trim();
    }

    public Date getBolgCreatetime() {
        return bolgCreatetime;
    }

    public void setBolgCreatetime(Date bolgCreatetime) {
        this.bolgCreatetime = bolgCreatetime;
    }

    public Integer getBolgCommentCount() {
        return bolgCommentCount;
    }

    public void setBolgCommentCount(Integer bolgCommentCount) {
        this.bolgCommentCount = bolgCommentCount;
    }

    public Integer getBolgLikesCount() {
        return bolgLikesCount;
    }

    public void setBolgLikesCount(Integer bolgLikesCount) {
        this.bolgLikesCount = bolgLikesCount;
    }

    public Integer getBolgBrowse() {
        return bolgBrowse;
    }

    public void setBolgBrowse(Integer bolgBrowse) {
        this.bolgBrowse = bolgBrowse;
    }
    
    public Date getBolgLastEditTime() {
        return bolgLastEditTime;
    }

    public void setBolgLastEditTime(Date bolgLastEditTime) {
        this.bolgLastEditTime = bolgLastEditTime;
    }

    public Integer getBolgIsReport() {
        return bolgIsReport;
    }

    public void setBolgIsReport(Integer bolgIsReport) {
        this.bolgIsReport = bolgIsReport;
    }

    public Integer getBolgGrade() {
        return bolgGrade;
    }

    public void setBolgGrade(Integer bolgGrade) {
        this.bolgGrade = bolgGrade;
    }
}