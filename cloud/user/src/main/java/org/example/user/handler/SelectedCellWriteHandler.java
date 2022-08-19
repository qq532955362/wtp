package org.example.user.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.example.user.pojo.vo.EffectSkuExcelVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class SelectedCellWriteHandler implements CellWriteHandler {


    private final Map<String, List<EffectSkuExcelVO.ExportData>> dataMap;
    private String key = "";

    public SelectedCellWriteHandler(Map<String, List<EffectSkuExcelVO.ExportData>> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        //第一列和第二列的分类做联动
        Integer columnIndex = head.getColumnIndex();
        if (!isHead) {
            if (columnIndex == 0) {
                key = cell.getStringCellValue();
            }
            if (columnIndex == 1) {
                setSelectDataList(writeSheetHolder,key,cell.getRowIndex(),cell.getColumnIndex());
            }
        }

    }

    /**
     * 设置下拉框数据
     *
     * @param writeSheetHolder
     * @param key
     * @param rowIndex         行号
     * @param columnIndex      列号
     */
    private void setSelectDataList(WriteSheetHolder writeSheetHolder, String key, int rowIndex, int columnIndex) {
        Sheet sheet = writeSheetHolder.getSheet();
        DataValidationHelper helper = sheet.getDataValidationHelper();

        // 设置下拉列表的行： 首行，末行，首列，末列
        CellRangeAddressList rangeList = new CellRangeAddressList(rowIndex, rowIndex, columnIndex, columnIndex);
        // 设置下拉列表的值
        DataValidationConstraint constraint = helper.createExplicitListConstraint(getSourceByKey(key));
        // 设置约束
        DataValidation validation = helper.createValidation(constraint, rangeList);
        // 阻止输入非下拉选项的值
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        validation.createErrorBox("提示", "请输入下拉选项中的内容");
        sheet.addValidationData(validation);
    }

    /**
     * 根据key关联出下拉框数据
     *
     * @param key
     * @return
     */
    private String[] getSourceByKey(String key) {
        List<EffectSkuExcelVO.ExportData> values = dataMap.get(key);
        List<String> selectList = values.stream().map(EffectSkuExcelVO.ExportData::getValue).collect(Collectors.toList());
        String[] selectArray = selectList.toArray(new String[selectList.size()]);
        return selectArray;
    }
}
