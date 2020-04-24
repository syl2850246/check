package com.sypm.check.bean;

public class UploadFileBean {

    // 上传门店编码
    private String uploadStoreCode;
    // 上传文件员工编码
    private String uploadStaffCode;
    // 上传文件的路径
    private String uplaodFilePath;
    // 上传文件名
    private String uploadFileName;
    // 上传文件时间
    private String uploadFileTime;

    public String getUploadStoreCode() {
        return uploadStoreCode;
    }

    public void setUploadStoreCode(String uploadStoreCode) {
        this.uploadStoreCode = uploadStoreCode;
    }

    public String getUploadStaffCode() {
        return uploadStaffCode;
    }

    public void setUploadStaffCode(String uploadStaffCode) {
        this.uploadStaffCode = uploadStaffCode;
    }

    public String getUplaodFilePath() {
        return uplaodFilePath;
    }

    public void setUplaodFilePath(String uplaodFilePath) {
        this.uplaodFilePath = uplaodFilePath;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadFileTime() {
        return uploadFileTime;
    }

    public void setUploadFileTime(String uploadFileTime) {
        this.uploadFileTime = uploadFileTime;
    }
}
