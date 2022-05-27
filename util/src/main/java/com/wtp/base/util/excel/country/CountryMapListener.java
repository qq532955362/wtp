package com.wtp.base.util.excel.country;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wtp.base.json.ResolvingJson;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class CountryMapListener extends AnalysisEventListener<CountryMapListener.CountryInfoData> {

    private List<CountryInfoData> dataList = new ArrayList<>();

    @Override
    public void invoke(CountryInfoData data, AnalysisContext context) {
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    public List<CountryInfoData> getDataList() {
        return dataList;
    }

    @Data
    public static class CountryInfoData {
        private String id;
        private String name;
        private String englishName;
        private String allName;
        private String iso2;
        private String iso3;
        private String code;
        private String description;

        @Override
        public String toString() {
            return "CountryInfoData{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", englishName='" + englishName + '\'' +
                    ", allName='" + allName + '\'' +
                    ", iso2='" + iso2 + '\'' +
                    ", iso3='" + iso3 + '\'' +
                    ", code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

}
