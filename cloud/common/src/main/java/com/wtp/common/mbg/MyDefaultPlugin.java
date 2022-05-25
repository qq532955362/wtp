package com.wtp.common.mbg;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

public class MyDefaultPlugin extends PluginAdapter {


    @Override
    public boolean validate(List<String> warnings) {
        return false;
    }


}
