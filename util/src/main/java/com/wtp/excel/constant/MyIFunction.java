package com.wtp.excel.constant;

/**
 * @author wangtaiping
 * 2021/11/29 17:36
 */
public enum MyIFunction implements IFunction {

    //测试功能
    TEST_FUNCTION("测试功能")
    ;

    private String name;
    MyIFunction(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
