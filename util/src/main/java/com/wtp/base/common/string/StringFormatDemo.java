package com.wtp.base.common.string;

public class StringFormatDemo {

    public static void main(String[] args) {
        //输出 10000000
        String format = String.format("%06d", 10000000);
        //输出 0010000 六位数字，以0填充前面不足的位
        String format2 = String.format("%06d", 1000);
        System.out.println(format);
    }
}
