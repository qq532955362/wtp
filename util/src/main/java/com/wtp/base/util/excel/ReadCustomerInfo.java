package com.wtp.base.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.wtp.base.constant.AimCountryEnum;
import lombok.Data;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadCustomerInfo {

    public static void readCustomerInfoToSql(String filepath) {

        MyListener myListener = new MyListener();
        EasyExcel.read(new File(filepath), CustomerInfoHead.class, myListener).doReadAll();
        List<CustomerInfoHead> dataList = myListener.dataList;
        Map<String, Integer> markMapId = Arrays.stream(AimCountryEnum.values()).collect(Collectors.toMap(a -> a.getMarketIdentifier(), b -> b.getId()));
        dataList.forEach(data -> {
            Integer id = data.getId();
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

            } else {
                builder.append(" " + markMapId.get(data) +
                        " ");

            }


        });

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
