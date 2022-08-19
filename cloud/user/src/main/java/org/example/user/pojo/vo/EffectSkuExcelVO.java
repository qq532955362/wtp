package org.example.user.pojo.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;
import org.example.user.annotation.ExcelSelected;
import org.example.user.pojo.selector.EffectCategoryOneSelector;
import org.example.user.pojo.selector.EffectCategoryTwoSelector;
import org.example.user.pojo.selector.EffectDeviceTypeSelector;
import org.example.user.pojo.selector.EffectTypeSelector;

@Data
@HeadRowHeight(value = 25) // 头部行高
@ContentRowHeight(value = 15) // 内容行高
@ColumnWidth(value = 20) // 列宽
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER) // 表头样式
@HeadFontStyle(fontName = "黑体")
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER) // 内容样式
public class EffectSkuExcelVO {

    @ExcelSelected(sourceClass = EffectCategoryOneSelector.class)
    @ExcelProperty("一级分类")
    private String cateOne;

    @ExcelSelected(sourceClass = EffectCategoryTwoSelector.class)
    @ExcelProperty("二级分类")
    private String cateTwo;

    @ExcelProperty("灯效名称")
    private String name;

    @ExcelSelected(sourceClass = EffectDeviceTypeSelector.class)
    @ExcelProperty("设备类型")
    private String attributeId;

    @ExcelSelected(sourceClass = EffectTypeSelector.class)
    @ExcelProperty("灯效类型")
    private String attribute;

    @ExcelProperty("参数")
    private String param;

    @Data
    public static class ExportData{
        private String key;
        private String value;
    }
}
