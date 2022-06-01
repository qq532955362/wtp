package com.wtp.common;


import com.wtp.common.mbg.MyGenerator;

public class MyMBG {

    public static void main(String[] args) {
        MyGenerator myGenerator = new MyGenerator();
//        myGenerator.generate(args[0], args[1], args[2], args[3]);
        myGenerator.generate("cloud/user", "flat", "govee.app.mall.mapper.mall","");
    }
}
