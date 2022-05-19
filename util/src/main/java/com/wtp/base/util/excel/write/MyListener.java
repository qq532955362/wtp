package com.wtp.base.util.excel.write;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyListener extends AnalysisEventListener<ReadCustomerInfo.CustomerInfoHead> {

    public List<ReadCustomerInfo.CustomerInfoHead> dataList = new ArrayList<>();

    @Override
    public void invoke(ReadCustomerInfo.CustomerInfoHead customerInfoHead, AnalysisContext analysisContext) {
        dataList.add(customerInfoHead);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}