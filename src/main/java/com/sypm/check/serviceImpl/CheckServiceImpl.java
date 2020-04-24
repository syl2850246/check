package com.sypm.check.serviceImpl;

import com.sypm.check.bean.LoginInfoBean;
import com.sypm.check.bean.Teacher;
import com.sypm.check.bean.UploadFileBean;
import com.sypm.check.mapper.CheckMapper;
import com.sypm.check.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {

    @Resource
    private CheckMapper checkMapper;

    @Override
    public List<LoginInfoBean> findLoginInfoByStoreCodeandStaffCode(LoginInfoBean loginInfoBean) {

        return checkMapper.findLoginInfoByStoreCodeandStaffCode(loginInfoBean);
    }

    @Override
    public Integer insertUploadFileInfo(UploadFileBean uploadFileBean) {

        return checkMapper.insertUploadFileInfo(uploadFileBean);
    }

    @Override
    public List<Teacher> findAllTeachers() {

        return checkMapper.findAllTeachers();
    }
}
