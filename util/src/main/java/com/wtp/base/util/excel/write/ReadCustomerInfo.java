package com.wtp.base.util.excel.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.wtp.base.constant.AimCountryEnum;
import lombok.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadCustomerInfo {

    public static void main(String[] args) throws IOException {
        readCustomerInfoToSql("/Users/wangtaiping/Documents/生产-客户信息.xlsx", "/Users/wangtaiping/Documents/update-new.sql");
    }

    public static void readCustomerInfoToSql(String filepath, String outPath) throws IOException {

        MyListener myListener = new MyListener();
        EasyExcel.read(new File(filepath), CustomerInfoHead.class, myListener)
                .sheet()
                //从第1行开始读取（无表头行excel）
                .headRowNumber(0).doRead();
        List<CustomerInfoHead> dataList = myListener.dataList;
        Map<String, AimCountryEnum> fullNameMapEnum = AimCountryEnum.fullNameMapEnum();
        Map<String, AimCountryEnum> markMapEnum = AimCountryEnum.markMapEnum();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outPath));
        dataList.forEach(data -> {
            StringBuilder builder = new StringBuilder();
            builder.append("UPDATE `ih_customer_info` SET ");
            builder.append(" `channel`= ");
            if (data.getChannel().startsWith("FX")) {
                builder.append(" 1 ");
            } else {
                builder.append(" 2 ");
            }
            builder.append(" , ");
            builder.append(" `country`= ");
            if ("OTHER".equalsIgnoreCase(data.getCountryMark().trim())) {
                builder.append(" ").append(fullNameMapEnum.get(data.getCountryDetail().trim()).getId()).append(" ");
            } else {
                builder.append(" ").append(markMapEnum.get(data.getCountryMark()).getId()).append(" ");
            }

            builder.append("WHERE `id` = ").append(data.getId()).append(";");
            try {
                bufferedWriter.write(builder.toString());
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    @Data
    public static class CustomerInfoHead {
        @ExcelProperty(index = 0)
        private Integer id;

        @ExcelProperty(index = 1)
        private Integer country;

        @ExcelProperty(index = 2)
        private String c;

        @ExcelProperty(index = 3)
        private String identity;

        @ExcelProperty(index = 4)
        private String e;

        @ExcelProperty(index = 5)
        private String f;

        @ExcelProperty(index = 6)
        private String g;

        @ExcelProperty(index = 7)
        private String h;

        @ExcelProperty(index = 8)
        private String i;

        @ExcelProperty(index = 9)
        private String j;

        @ExcelProperty(index = 10)
        private String k;

        @ExcelProperty(index = 11)
        private String l;

        @ExcelProperty(index = 12)
        private String m;

        @ExcelProperty(index = 13)
        private String channel;

        @ExcelProperty(index = 14)
        private String countryMark;

        @ExcelProperty(index = 15)
        private String countryDetail;
    }

}
