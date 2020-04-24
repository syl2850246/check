package com.sypm.check.mapper;

import com.sypm.check.bean.LoginInfoBean;
import com.sypm.check.bean.Teacher;
import com.sypm.check.bean.UploadFileBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckMapper {

    // 根据门店编码和员工编码进行查询相关信息
    List<LoginInfoBean> findLoginInfoByStoreCodeandStaffCode(LoginInfoBean loginInfoBean);

    // 上传文件
    Integer insertUploadFileInfo(UploadFileBean uploadFileBean);

    List<Teacher> findAllTeachers();
}
