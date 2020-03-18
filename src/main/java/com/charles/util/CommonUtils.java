package com.charles.util;

import com.charles.exception.ExcelException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.charles.constant.Constant.resultPath;

/**
 * @author BBD
 */
public class CommonUtils {


    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    public static void copy(String srcPathStr, String desPathStr, String nowStr) throws ExcelException {

        File fileDir = new File(resultPath + nowStr + File.separator + "原文件" + File.separator);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //获取源文件的名称
        //目标文件地址
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\") + 1);
        System.out.println("源文件:" + newFileName);
        //源文件地址

        System.out.println("目标文件地址:" + desPathStr);
        try {
            //创建输入流对象
            FileInputStream fis = new FileInputStream(srcPathStr);
            //创建输出流对象
            FileOutputStream fos = new FileOutputStream(desPathStr);
            //创建搬运工具
            byte datas[] = new byte[1024 * 8];
            //创建长度
            int len = 0;
            //循环读取数据
            while ((len = fis.read(datas)) != -1) {
                fos.write(datas, 0, len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        } catch (Exception e) {
            throw new ExcelException(e.getMessage());
        }
    }

}
