package com.haohuo.beans;

public class Account {
    private Integer acId;

    private String acName;

    private Integer acFee;

    private String acTime;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName == null ? null : acName.trim();
    }

    public Integer getAcFee() {
        return acFee;
    }

    public void setAcFee(Integer acFee) {
        this.acFee = acFee;
    }

    public String getAcTime() {
        return acTime;
    }

    public void setAcTime(String acTime) {
        this.acTime = acTime == null ? null : acTime.trim();
    }
}