package com.wtp.base.common.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList可以添加null输出之后用的是直接是null字符串
 *
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        List integerList = new ArrayList<>();
        integerList.add(null);
        integerList.add(null);
        integerList.add(1);
        integerList.add(666);
        integerList.forEach(a-> System.out.println((String) a));
    }
}
