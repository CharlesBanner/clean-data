package com.charles.task;

import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.charles.model.MaybeEnterPeopleExcelVO;
import com.charles.util.CommonUtils;
import com.charles.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.charles.constant.Constant.*;

@Service
@Slf4j
public class MaybeEnterPeopleService {


    public void export() {
        LocalDateTime now = LocalDateTime.now();
        String nowStr = CommonUtils.getDateTimeAsString(now, "yyyy-MM-dd");
        File file = new File(basePath);
        File fileDir = new File(resultPath + nowStr + File.separator);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File[] tempList = file.listFiles();
        if (null == tempList) {
            log.error("文件夹为空或文件夹不存在！请检查");
            return;
        }
        try {
            for (int i = 0; i < tempList.length; i++) {
                File excelFile = tempList[i];
                String name = excelFile.getName();
                if (StringUtils.isBlank(name)) {
                    log.error("文件名称不合规范！请检查");
                    return;
                }
                log.info("开始解析文件：" + name);
                String[] fileSplit = name.split("\\.");
                String resultFileName = resultPath + nowStr + File.separator + fileSplit[0] + "_已拆分" + ".xlsx";
                Sheet sheet = new Sheet(1, 1);
                List<MaybeEnterPeopleExcelVO> maybeEnterPeopleExcelVOS = ExcelUtil.readExcel(excelFile, MaybeEnterPeopleExcelVO.class);
                File resultExcel = new File(resultFileName);
                if (!resultExcel.exists()) {
                    resultExcel.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(resultExcel);
                log.info("正在拆分文件：" + name);
                /*生成新表*/
                maybeEnterPeopleExcelVOS.forEach(vo -> {
                    vo.setPhoneNum(vo.getPhoneNum());
                });
                List<MaybeEnterPeopleExcelVO> resultData = new ArrayList<>();
                maybeEnterPeopleExcelVOS.forEach(vo -> {
                    if (StringUtils.isBlank(vo.getPhoneNum()) || vo.getPhoneNum().length() == 11) {
                        resultData.add(vo);
                    } else {
                        String[] split = vo.getPhoneNum().trim().split(",");
                        for (int i1 = 0; i1 < split.length; i1++) {
                            MaybeEnterPeopleExcelVO excelDemo = new MaybeEnterPeopleExcelVO();
                            BeanUtils.copyProperties(vo, excelDemo);
                            if (!StringUtils.isBlank(split[i1])) {
                                excelDemo.setPhoneNum(dealPhone(split[i1]));
                                resultData.add(excelDemo);
                            }
                        }
                    }
                });
                ExcelUtil.writeExcel(fileOutputStream, resultData, "拆分数据", ExcelTypeEnum.XLSX, MaybeEnterPeopleExcelVO.class);
                log.info("生成拆分excel");
                CommonUtils.copy(excelFile.getAbsolutePath(), resultPath + nowStr + File.separator + "原文件" + File.separator + excelFile.getName(), nowStr);
                excelFile.deleteOnExit();
            }
            log.info("任务完成！");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.warn("任务失败!");
    }

    public static String dealPhone(String phoneStr) {
        if (StringUtils.isBlank(phoneStr)) {
            return null;
        } else {
            phoneStr = "a" + phoneStr.trim();
        }

        phoneStr = phoneStr.replace(",,", ",").replace("a,", "").replace("a", "");
        List<String> split = Arrays.asList(phoneStr.split(","));
        split = split.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(split)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.size(); i++) {
            if (split.get(i).length() == 11 && split.get(i).trim().startsWith("1")) {
                sb.append(split.get(i)).append(",");
            }
        }
        StringBuilder stringBuffer = new StringBuilder();

        if (!StringUtils.isBlank(sb.toString())) {
            String charAt = sb.deleteCharAt(sb.length() - 1).toString();
            List<String> strings = Arrays.asList(charAt.split(","));
            strings = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(strings)) {
                return null;
            }
            for (int i = 0; i < strings.size(); i++) {
                stringBuffer.append(strings.get(i)).append(",");
            }
            return stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString();
        } else {
            return null;
        }


    }

}
