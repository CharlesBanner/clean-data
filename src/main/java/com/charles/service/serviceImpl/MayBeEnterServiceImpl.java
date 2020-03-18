//package com.charles.service.serviceImpl;
//
//import com.alibaba.excel.annotation.ExcelProperty;
//import com.charles.mapper.MayBeEnterMapper;
//import com.charles.model.MaybeEnter;
//import com.charles.model.MaybeEnterPeopleExcelVO;
//import com.charles.service.MayBeEnterService;
//import com.charles.service.base.BaseServiceImpl;
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MayBeEnterServiceImpl extends BaseServiceImpl<MayBeEnterMapper, MaybeEnter> implements MayBeEnterService {
//
//    @Override
//    public void saveMaybeEnterDate(List<MaybeEnterPeopleExcelVO> list) {
//        List<MaybeEnter> maybeEnters = new ArrayList<>(list.size());
//        list.forEach(vo -> {
//            MaybeEnter maybeEnter = new MaybeEnter();
//            Field[] sourceFields = vo.getClass().getFields();
//            Field[] targetFields = maybeEnter.getClass().getFields();
//            for (int i = 0; i < sourceFields.length; i++) {
//
//                for (int i1 = 0; i1 < targetFields.length; i1++) {
//                    ExcelProperty sourceAnnotation = sourceFields[i].getAnnotation(ExcelProperty.class);
//                    ExcelProperty targetAnnotation = targetFields[i1].getAnnotation(ExcelProperty.class);
//                    if (targetAnnotation.index()==sourceAnnotation.index()){
////                        targetFields[i1].set
//                    }
//                }
//            }
//
//
//        });
//
//    }
//}
