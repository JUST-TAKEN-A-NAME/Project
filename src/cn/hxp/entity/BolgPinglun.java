package cn.hxp.entity;

import java.util.Date;

public class BolgPinglun {
    private Integer pinglunId;

    private Integer pinglunBolgId;

    private Integer pinglunrenId;

    private String pinglunrenIp;

    private String pinglunContent;

    private Date pinglunDate;

    private Integer pinglunIsGood;

    private Integer pinglunIsBereply;

    public Integer getPinglunId() {
        return pinglunId;
    }

    public void setPinglunId(Integer pinglunId) {
        this.pinglunId = pinglunId;
    }

    public Integer getPinglunBolgId() {
        return pinglunBolgId;
    }

    public void setPinglunBolgId(Integer pinglunBolgId) {
        this.pinglunBolgId = pinglunBolgId;
    }

    public Integer getPinglunrenId() {
        return pinglunrenId;
    }

    public void setPinglunrenId(Integer pinglunrenId) {
        this.pinglunrenId = pinglunrenId;
    }

    public String getPinglunrenIp() {
        return pinglunrenIp;
    }

    public void setPinglunrenIp(String pinglunrenIp) {
        this.pinglunrenIp = pinglunrenIp == null ? null : pinglunrenIp.trim();
    }

    public String getPinglunContent() {
        return pinglunContent;
    }

    public void setPinglunContent(String pinglunContent) {
        this.pinglunContent = pinglunContent == null ? null : pinglunContent.trim();
    }

    public Date getPinglunDate() {
        return pinglunDate;
    }

    public void setPinglunDate(Date pinglunDate) {
        this.pinglunDate = pinglunDate;
    }

    public Integer getPinglunIsGood() {
        return pinglunIsGood;
    }

    public void setPinglunIsGood(Integer pinglunIsGood) {
        this.pinglunIsGood = pinglunIsGood;
    }

    public Integer getPinglunIsBereply() {
        return pinglunIsBereply;
    }

    public void setPinglunIsBereply(Integer pinglunIsBereply) {
        this.pinglunIsBereply = pinglunIsBereply;
    }
}