package com.sypm.check.controller;

import com.sypm.check.bean.DownFileBean;
import com.sypm.check.bean.LoginInfoBean;
import com.sypm.check.bean.Teacher;
import com.sypm.check.bean.UploadFileBean;
import com.sypm.check.service.CheckService;
import com.sypm.check.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CheckController {

    @Resource
    private CheckService checkService;

    @RequestMapping("/login")
    public String toLogin() {

        return "login";
    }

    @RequestMapping(value = "/toCheck", method = RequestMethod.POST)
    public String checkLoginInfo(LoginInfoBean loginInfoBean, ModelMap modelMap, HttpServletRequest request) {

        List<LoginInfoBean> loginInfoBeanList = checkService.findLoginInfoByStoreCodeandStaffCode(loginInfoBean);
        if (loginInfoBeanList.size() > 0) {

            modelMap.addAttribute("StoreCode", loginInfoBean.getStoreCode());
            modelMap.addAttribute("StaffCode", loginInfoBean.getStaffCode());
            request.getSession().setAttribute("users", loginInfoBean.getStaffCode());
            return "index";
        } else {

            modelMap.addAttribute("loginInfo", "*请检查门店编码及其员工编码");
            return "login";
        }
    }

    @RequestMapping(value="/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addImg(HttpServletRequest request, HttpServletResponse response){

        Map<String,Object> result= new HashMap<String, Object>();

        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 上传文件相关信息
        UploadFileBean uploadFileBean = new UploadFileBean();
        // 获取文件上传的附加参数
        Map<String, String[]> extraFileUploadParameters = multipartRequest.getParameterMap();
        // 门店编码
        String[] storeCodes = extraFileUploadParameters.get("storeCode");
        // 员工编码
        String[] staffCodes = extraFileUploadParameters.get("staffCode");


        // 获得文件：
        MultipartFile imgFile= multipartRequest.getFile("uploadfile");

        // 进行Excel文件的解析
        try {
            if (imgFile != null) {

                List<List<String>> datas = ExcelUtil.readXlsx(imgFile.getInputStream());
                if (datas != null && datas.size() > 0) {

                    // 遍历列数
                    for(int i = 0; i < datas.size(); i++) {

                        String rowsValue = "";
                        List<String> cols = datas.get(i);
                        // 遍历行数
                        for(int j = 0; j < cols.size(); j++) {

                            String row = cols.get(j);
                            rowsValue = rowsValue + row + ", ";
                        }
                        System.out.println("第" + i + "列数据: " + rowsValue);
                    }
                    System.out.println("解析成功....");
                }
            } else {

                System.out.println("解析失败....");
            }
        } catch (Exception e) {

            System.out.println("异常信息...");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            if(!(imgFile.getOriginalFilename() == null || "".equals(imgFile.getOriginalFilename()))){

                String imgDir = "e:\\User\\Desktop\\upload";        // 文件上传地址

                // 对文件进行存储处理
                byte[] bytes = imgFile.getBytes();
                Path path = Paths.get(imgDir,"\\"+imgFile.getOriginalFilename());
                Files.write(path,bytes);

                uploadFileBean.setUploadFileName(imgFile.getOriginalFilename());
                uploadFileBean.setUploadStoreCode(storeCodes[0]);
                uploadFileBean.setUploadStaffCode(staffCodes[0]);
                uploadFileBean.setUploadFileTime(sdf.format(new Date()));
                uploadFileBean.setUplaodFilePath(path.toString());

                Integer rows = checkService.insertUploadFileInfo(uploadFileBean);

                if(rows > 0) {

                    result.put("msg", "上传成功！");
                    result.put("result", true);
                } else {

                    result.put("msg", "上传失败！");
                    result.put("result", false);
                }
            }
        }catch(IOException e){

            result.put("msg","出错了");
            result.put("result",false);
            e.printStackTrace();
        }catch (Exception e1){

            e1.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response, DownFileBean downFileBean) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("综合满意度评分结果");

        String fileName = "综合满意度评分结果" + ".xlsx";// 设置要导出的文件的名字
        String[] headers = { "部门", "被评人", "考核人姓名", "题1得分", "题2得分", "题3得分", "题4得分", "题5得分", "合计总分", "占比" };

        List<Teacher> classmateList = checkService.findAllTeachers();

        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 9);
        sheet.addMergedRegion(region);
        XSSFRow rowTitle = sheet.createRow(0);
        Cell oneCell = rowTitle.createCell(0);
        oneCell.setCellValue("综合满意度评分结果表");// 设置标题内容

        // 合并的单元格样式
        XSSFCellStyle boderStyle = workbook.createCellStyle();
        boderStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        boderStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        oneCell.setCellStyle(boderStyle);

        XSSFRow row = sheet.createRow(1);
        // 在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(boderStyle);
        }

        int rowNum = 2;
        // 在表中存放查询到的数据放入对应的列
        for (Teacher teacher : classmateList) {

            XSSFRow XSSFRow = sheet.createRow(rowNum);

            XSSFCell cell0 = XSSFRow.createCell(0);
            cell0.setCellValue(teacher.getDEPARTMENTS());;
            cell0.setCellStyle(boderStyle);

            XSSFCell cell1 = XSSFRow.createCell(1);
            cell1.setCellValue(teacher.getTESTED_NAME());;
            cell1.setCellStyle(boderStyle);

            XSSFCell cell2 = XSSFRow.createCell(2);
            cell2.setCellValue(teacher.getTEST_NAME());;
            cell2.setCellStyle(boderStyle);

            XSSFCell cell3 = XSSFRow.createCell(3);
            cell3.setCellValue(teacher.getQUESTION_ID());;
            cell3.setCellStyle(boderStyle);

            XSSFCell cell4 = XSSFRow.createCell(4);
            cell4.setCellValue(teacher.getOPTION_ID1());;
            cell4.setCellStyle(boderStyle);

            XSSFCell cell5 = XSSFRow.createCell(5);
            cell5.setCellValue(teacher.getOPTION_ID2());;
            cell5.setCellStyle(boderStyle);

            XSSFCell cell6 = XSSFRow.createCell(6);
            cell6.setCellValue(teacher.getOPTION_ID3());;
            cell6.setCellStyle(boderStyle);

            XSSFCell cell7 = XSSFRow.createCell(7);
            cell7.setCellValue(teacher.getOPTION_ID4());;
            cell7.setCellStyle(boderStyle);

            XSSFCell cell8 = XSSFRow.createCell(8);
            cell8.setCellValue(teacher.getOPTION_ID5());;
            cell8.setCellStyle(boderStyle);

            XSSFCell cell9 = XSSFRow.createCell(9);
            cell9.setCellValue(teacher.getSCORE());;
            cell9.setCellStyle(boderStyle);
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
