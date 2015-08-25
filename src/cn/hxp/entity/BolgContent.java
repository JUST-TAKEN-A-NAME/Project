package cn.hxp.entity;

public class BolgContent {
    private Integer bolgContentId;

    private Integer bolgId;

    private String bolgContent;

    public Integer getBolgContentId() {
        return bolgContentId;
    }

    public void setBolgContentId(Integer bolgContentId) {
        this.bolgContentId = bolgContentId;
    }

    public Integer getBolgId() {
        return bolgId;
    }

    public void setBolgId(Integer bolgId) {
        this.bolgId = bolgId;
    }

    public String getBolgContent() {
        return bolgContent;
    }

    public void setBolgContent(String bolgContent) {
        this.bolgContent = bolgContent == null ? null : bolgContent.trim();
    }
}