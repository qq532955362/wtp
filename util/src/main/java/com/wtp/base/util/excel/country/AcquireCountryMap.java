package com.wtp.base.util.excel.country;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.wtp.base.json.ResolvingJson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AcquireCountryMap {

    public static void acquire(String filename) throws FileNotFoundException {
        CountryMapListener countryMapListener = new CountryMapListener();
        EasyExcel.read(new FileInputStream(filename), CountryMapListener.CountryInfoData.class, countryMapListener).sheet().headRowNumber(0).doRead();
        List<CountryMapListener.CountryInfoData> dataList = countryMapListener.getDataList();
        Map<String, CountryMapListener.CountryInfoData> baiduMap = dataList.stream().collect(Collectors.toMap(a -> a.getIso2(), Function.identity()));
        String jsonString = ResolvingJson.readFileToJson("/Users/wangtaiping/IdeaProjects/open/resources/country.json");
        List<ResolvingJson.CountryItem> countryItemList = JSONObject.parseArray(jsonString, ResolvingJson.CountryItem.class);
        List<ResolvingJson.CountryItem.Country> jsonCountryList = new ArrayList<>();
        countryItemList.forEach(a->jsonCountryList.addAll(a.getItems()));
        Map<String, String> isoNameMap = jsonCountryList.stream().collect(Collectors.toMap(a -> a.getIso(), b -> b.getName()));

        //校验的map
        HashMap<String, String> validateMap = new HashMap<>();
        baiduMap.forEach((a,b)->{
            validateMap.put(a, isoNameMap.getOrDefault(a, "___________"));
        });
        validateMap.forEach((a,b)->{
            System.out.println(a+"："+b);
        });
    }

    public static void main(String[] args) throws FileNotFoundException {
        acquire("/Users/wangtaiping/Documents/国家或地区代码ISO3166标准.xlsx");
    }
}
