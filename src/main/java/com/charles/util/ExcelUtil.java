package com.charles.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.charles.exception.ExcelException;
import com.charles.listen.ExcelListener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcelUtil
 * 基于easyExcel的开源框架，poi版本3.17
 * BeanCopy ExcelException 属于自定义数据，属于可自定义依赖
 * 工具类尽可能还是需要减少对其他java的包的依赖
 * <p>
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 16:25
 */
public class ExcelUtil {
    /**
     * 私有化构造方法
     */
    private ExcelUtil() {
    }

    /**
     * 读取 Excel(多个 sheet)
     * 将多sheet合并成一个list数据集，通过自定义ExcelReader继承AnalysisEventListener
     * 重写invoke doAfterAllAnalysed方法
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(File excel, Class<T> rowModel) throws ExcelException {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        for (Sheet sheet : reader.getSheets()) {
            sheet.setClazz(rowModel);
            reader.read(sheet);
        }
        return getExtendsBeanList(excelListener.getDatas(), rowModel);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(File excel, Class<T> rowModel, int sheetNo) throws ExcelException {
        return readExcel(excel, rowModel, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel       文件
     * @param rowModel    实体类映射，继承 BaseRowModel 类
     * @param sheetNo     sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(File excel, Class<T> rowModel, int sheetNo,
                                                             int headLineNum) throws ExcelException {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel));
        return getExtendsBeanList(excelListener.getDatas(), rowModel);
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     * 自定义WriterHandler 可以定制行列数据进行灵活化操作
     *
     * @param outputStream HttpServletResponse
     * @param list         数据 list，每个元素为一个 BaseRowModel
     * @param sheetName    导入文件的 sheet 名
     */
    public static <T extends BaseRowModel> void writeExcel(FileOutputStream outputStream, List<T> list, String sheetName, ExcelTypeEnum excelTypeEnum,
                                                           Class<T> classType) throws ExcelException {
        if (sheetName == null || "".equals(sheetName)) {
            sheetName = "sheet1";
        }
        if (excelTypeEnum == ExcelTypeEnum.XLSX) {
            ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null,
                    outputStream, excelTypeEnum, true, new WriterHandler07<>(classType));
            Sheet sheet = new Sheet(1, 0, classType);
            sheet.setSheetName(sheetName);
            try {
                writer.write(list, sheet);
            } finally {
                writer.finish();
            }
            //其实也可以专门调03版的样式，或者直接套用
        } else if (excelTypeEnum == ExcelTypeEnum.XLS) {
            ExcelWriterFactory writer = new ExcelWriterFactory(outputStream, excelTypeEnum);
            Sheet sheet = new Sheet(1, 0, classType);
            sheet.setSheetName(sheetName);
            try {
                writer.write(list, sheet);
            } finally {
                writer.finish();
                writer.close();
            }
        }
    }

    /**
     * 导出 Excel ：多个 sheet，带表头
     *
     * @param outputStream HttpServletResponse
     * @param list         数据 list，每个元素为一个 BaseRowModel
     * @param sheetName    导入文件的 sheet 名
     * @param object       映射实体类，Excel 模型
     */
    public static ExcelWriterFactory writeExcelWithSheets(FileOutputStream outputStream, List<? extends BaseRowModel> list, String sheetName, BaseRowModel object, ExcelTypeEnum excelTypeEnum) throws ExcelException {
        ExcelWriterFactory writer = new ExcelWriterFactory(outputStream, excelTypeEnum);
        Sheet sheet = new Sheet(1, 0, object.getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        return writer;
    }


    /**
     * 返回 ExcelReader
     *
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(File excel,
                                         ExcelListener excelListener) throws ExcelException {
        String fileName = excel.getName();
        if (fileName == null) {
            throw new ExcelException("文件格式错误！");
        }
        if (!fileName.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) && !fileName.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())) {
            throw new ExcelException("文件格式错误！");
        }
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(excel);
            return new ExcelReader(inputStream, null, excelListener, false);
        } catch (IOException e) {
            //do something
        }
        return null;
    }

    /**
     * 利用BeanCopy转换list
     */
    public static <T extends BaseRowModel> List<T> getExtendsBeanList(List<?> list, Class<T> typeClazz) {
        return BeanCopy.convert(list, typeClazz);
    }

}
