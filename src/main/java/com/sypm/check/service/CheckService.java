package com.sypm.check.service;

import com.sypm.check.bean.LoginInfoBean;
import com.sypm.check.bean.Teacher;
import com.sypm.check.bean.UploadFileBean;

import java.util.List;

public interface CheckService {

    List<LoginInfoBean> findLoginInfoByStoreCodeandStaffCode(LoginInfoBean loginInfoBean);

    Integer insertUploadFileInfo(UploadFileBean uploadFileBean);

    List<Teacher> findAllTeachers();
}
