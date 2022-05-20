package com.wtp.base;


import com.wtp.base.util.mybatisgenerator.MyGenerator;

public class MyMBG {

    public static void main(String[] args) {
        MyGenerator myGenerator = new MyGenerator();
//        myGenerator.generate(args[0], args[1], args[2], args[3]);
        myGenerator.generate("cloud/user", "flat", "org.example.user.pojo", "com.wtp.base.util.mybatisgenerator.MyDefaultPlugin");
    }
}
