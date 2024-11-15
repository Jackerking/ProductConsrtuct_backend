package com.rjgj.zjpg.entity;

import java.util.Date;

public class CostStandard {
    private int stdId;
    private String stdName;
    private float PDR;
    private Date createTime;
    private String intro ;
    private Type type;
    private Enable enable;

    public enum Enable{
        enabled,
        disabled
    }

    public enum Type {
        history,
        official
    }

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public float getPDR() {
        return PDR;
    }

    public void setPDR(float PDR) {
        this.PDR = PDR;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Enable getEnable() {
        return enable;
    }

    public void setEnable(Enable enable) {
        this.enable = enable;
    }
}

