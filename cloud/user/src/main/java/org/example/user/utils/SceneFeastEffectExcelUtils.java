package org.example.user.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.extern.slf4j.Slf4j;
import org.example.user.annotation.ExcelSelected;
import org.example.user.annotation.ExcelSelectedResolve;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SceneFeastEffectExcelUtils {

    /**
     * 解析表头类中的下拉注解
     * @param head 表头类
     * @param <T> 泛型
     * @return Map<下拉框列索引, 下拉框内容> map
     */
    public static <T> Map<Integer, ExcelSelectedResolve> resolveSelectedAnnotation(Class<T> head) {
        Map<Integer, ExcelSelectedResolve> selectedMap = new HashMap<>();

        // getDeclaredFields(): 返回全部声明的属性；getFields(): 返回public类型的属性
        Field[] fields = head.getDeclaredFields();
        for (int i = 0; i < fields.length; i++){
            Field field = fields[i];
            // 解析注解信息
            ExcelSelected selected = field.getAnnotation(ExcelSelected.class);
            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
            if (selected != null) {
                ExcelSelectedResolve excelSelectedResolve = new ExcelSelectedResolve();
                // 处理下拉框内容
                String[] source = excelSelectedResolve.resolveSelectedSource(selected);
                if (source != null && source.length > 0){
                    excelSelectedResolve.setSource(source);
                    excelSelectedResolve.setFirstRow(selected.firstRow());
                    excelSelectedResolve.setLastRow(selected.lastRow());
                    if (property != null && property.index() >= 0){
                        selectedMap.put(property.index(), excelSelectedResolve);
                    } else {
                        selectedMap.put(i, excelSelectedResolve);
                    }
                }
            }
        }

        return selectedMap;
    }
}
