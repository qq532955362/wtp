package com.wtp.excel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.analysis.ExcelReadExecutor;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author wangtaiping
 * @time 2021/9/16 16:24
 * @tips
 */
public class Reader {

    private final static String SQL = "sql|Sql|SQL";

    private final static Pattern compile = Pattern.compile(SQL);

    public static void getExcelSheet(String excelFilepath) throws Exception {
        if (excelFilepath == null || (!excelFilepath.endsWith(".xlsx") && !excelFilepath.endsWith(".xls"))) {
            throw new Exception("目标文件格式有问题");
        }
        ExcelReader excelReader = EasyExcel.read(excelFilepath).build();

        ExcelReadExecutor excelReadExecutor = excelReader.excelExecutor();
        final List<ReadSheet> readSheetList = excelReadExecutor.sheetList();

        final List<ReadSheet> collect = readSheetList.stream().filter(a -> "球员SQL".equals(a.getSheetName())).collect(Collectors.toList());
        final ReadSheet readSheet = collect.get(0);
        final String sheetName = readSheet.getSheetName();

        final Integer sheetNo = readSheet.getSheetNo();

        System.out.println(sheetNo);

        System.out.println(sheetName);

    }

    @Test
    public void testWrite() throws Exception {
        Reader.getExcelSheet("C:\\Users\\WIN10\\AppData\\Roaming\\Foxmail7\\Temp-10688-20210915140718\\Attach\\NBA球员数据v2.4.10(4)(1)(5)(2)(6).xlsx");
    }
}
