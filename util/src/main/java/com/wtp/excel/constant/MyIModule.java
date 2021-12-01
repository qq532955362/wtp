package com.wtp.excel.constant;

/**
 * @author wangtaiping
 * 2021/11/29 17:32
 */
public enum MyIModule implements IModule {

    //测试模块
    TEST_MODULE("测试模块"),
    ;

    private String name;

    MyIModule(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
