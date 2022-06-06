package com.wtp.base.util.excel.country;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.wtp.base.json.ResolvingJson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AcquireCountryMap {

    public static void acquire(String filename) throws IOException {
        CountryMapListener countryMapListener = new CountryMapListener();
        EasyExcel.read(new FileInputStream(filename), CountryMapListener.CountryInfoData.class, countryMapListener).sheet().headRowNumber(0).doRead();
        List<CountryMapListener.CountryInfoData> dataList = countryMapListener.getDataList();
        Map<String, CountryMapListener.CountryInfoData> baiduMap = dataList.stream().collect(Collectors.toMap(a -> a.getIso2(), Function.identity()));
        String jsonString = ResolvingJson.readFileToJson("resources/country.json");
        List<ResolvingJson.CountryItem> countryItemList = JSONObject.parseArray(jsonString, ResolvingJson.CountryItem.class);
        List<ResolvingJson.CountryItem.Country> jsonCountryList = new ArrayList<>();
        countryItemList.forEach(a -> jsonCountryList.addAll(a.getItems()));
        Map<String, String> taoBaoIsoNameMap = jsonCountryList.stream().collect(Collectors.toMap(a -> a.getIso(), b -> b.getName()));
        Map<String, String> taoBaoIsoPinyinMap = jsonCountryList.stream().collect(Collectors.toMap(a -> a.getIso(), b -> b.getPinYin()));
        Map<String, String> taoBaoIsoPhonePrefixMap = jsonCountryList.stream().collect(Collectors.toMap(a -> a.getIso(), b -> b.getPhonePrefix()));

        //校验的map<iso,中文名>
        HashMap<String, String> validateCnMap = new HashMap<>();
        //校验的map<iso,英文名>
        HashMap<String, String> validateEnMap = new HashMap<>();
        //校验的map<iso,中文拼音>
        HashMap<String, String> validatePinyinMap = new HashMap<>();

        taoBaoIsoNameMap.forEach((a, b) -> {
            validateCnMap.put(a, b);
            CountryMapListener.CountryInfoData countryInfoData = baiduMap.get(a);
            if (countryInfoData == null) {
                System.out.println(a + "---------------");
            } else {
                validateEnMap.put(a, countryInfoData.getEnglishName());
            }
            validatePinyinMap.put(a, taoBaoIsoPinyinMap.get(a));
        });

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/新增国家sql.sql"));
        taoBaoIsoNameMap.forEach((a, b) -> {
            try {
                bufferedWriter.write("insert into `app_administration` (`cn_name`,`en_name`,`pinyin`,`iso`,phone_prefix) VALUES " + String.format("( '%s' , '%s' , '%s' ,'%s' ,'%s');",
                        b,
                        validateEnMap.get(a),
                        validatePinyinMap.get(a),
                        a,
                        taoBaoIsoPhonePrefixMap.get(a)));
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        acquire("resources/国家或地区代码ISO3166标准.xlsx");
    }
}
