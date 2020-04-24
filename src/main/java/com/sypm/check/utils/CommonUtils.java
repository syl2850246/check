package com.sypm.check.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class CommonUtils {

    /**
     * 读取文件中的内容
     * @param file
     * @return
     * @throws IOException
     */
    public static String readTxt(File file) throws IOException {

        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader br = new BufferedReader(in);

        StringBuffer content = new StringBuffer();
        while ((s=br.readLine()) != null){

            content = content.append(s);
        }
        return content.toString();
    }

//    public static void main(String[] args) {
//
//        try {
//
//            String s1 = CommonUtils.readTxt(new File("e:\\User\\Desktop\\upload\\webservice地址备份.txt"));
//            System.out.println("文件内容: " + s1);
//
//            //spring boot中文件直接放在resources目录下
////            String s2 = CommonUtils.readTxt(ResourceUtils.getFile("classpath:du.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
