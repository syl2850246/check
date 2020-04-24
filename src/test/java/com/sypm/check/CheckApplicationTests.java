package com.sypm.check;

import com.sypm.check.bean.LoginInfoBean;
import com.sypm.check.bean.Teacher;
import com.sypm.check.mapper.CheckMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CheckApplication.class)
public class CheckApplicationTests {

    @Autowired
    private CheckMapper checkMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testFindAllTeachers() {

        List<Teacher> teacherList = checkMapper.findAllTeachers();
        System.out.println(teacherList.size());

        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean.setStaffCode("125261");
        loginInfoBean.setStoreCode("001");
        List<LoginInfoBean> loginInfoBeanList = checkMapper.findLoginInfoByStoreCodeandStaffCode(loginInfoBean);
        System.out.println(loginInfoBeanList.toString());
    }
}
