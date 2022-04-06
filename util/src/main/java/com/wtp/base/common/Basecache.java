package com.wtp.base.common;

/**
 * @author wangtaiping
 * 2022/4/6 18:01
 */
public class Basecache {
    /**
     * 总结基本类型 byte short int long对应的包装类型有[-128,127]的缓存
     * 其他类型没有
     * @param args
     */
    public static void main(String[] args) {
        Integer integerA = Integer.valueOf(13);
        Integer integerAa = Integer.valueOf(13);
        Integer integerB = Integer.valueOf(128);
        Integer integerBb = Integer.valueOf(128);

        Long longA = Long.valueOf(13);
        Long longAa = Long.valueOf(13);
        Long longB = Long.valueOf(128);
        Long longBb = Long.valueOf(128);
        //true
        System.out.println(integerA==integerAa);
        //false
        System.out.println(integerB==integerBb);

        //true
        System.out.println(longA==longAa);
        //false
        System.out.println(longB==longBb);

        Short shortA = Short.valueOf("13",10);
        Short shortAa = Short.valueOf("13",10);
        Short shortB = Short.valueOf("128", 10);
        Short shortBb = Short.valueOf("128", 10);

        System.out.println(shortA==shortAa);
        System.out.println(shortB==shortBb);

        Byte byteA = Byte.valueOf("12");
        Byte byteAa = Byte.valueOf("12");
        //compile exception for number format
        //Byte byteB = Byte.valueOf("128");
        //Byte byteBb = Byte.valueOf("128");

        System.out.println(byteA==byteAa);
        //System.out.println(byteB==byteBb);

        Double doubleA = Double.valueOf("12");
        Double doubleAa = Double.valueOf("12");
        Double doubleB = Double.valueOf("128");
        Double doubleBb = Double.valueOf("128");

        System.out.println(doubleA==doubleAa);
        System.out.println(doubleB==doubleBb);
    }
}
