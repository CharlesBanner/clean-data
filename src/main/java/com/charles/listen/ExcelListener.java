package com.charles.listen;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 16:25
 */
public class ExcelListener extends AnalysisEventListener {

    private List<Object> dataList = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        if (!checkObjAllFieldsIsNull(object)) {
            dataList.add(object);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //do something
    }

    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 判断对象中属性值是否全为空
     */
    private boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                //只校验带ExcelProperty注解的属性
                ExcelProperty property = f.getAnnotation(ExcelProperty.class);
                if (property == null || SERIAL_VERSION_UID.equals(f.getName())) {
                    continue;
                }
                if (f.get(object) != null && !StringUtils.isBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            //do something
        }
        return true;
    }

    public List<Object> getDatas() {
        return dataList;
    }
}