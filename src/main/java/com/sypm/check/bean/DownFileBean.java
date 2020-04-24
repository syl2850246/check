package com.sypm.check.bean;

public class DownFileBean {

    // 门店编码
    private String storeCode;
    // 员工编码
    private String staffCode;

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Override
    public String toString() {
        return "DownFileBean{" +
                "storeCode='" + storeCode + '\'' +
                ", staffCode='" + staffCode + '\'' +
                '}';
    }
}
