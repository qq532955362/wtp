package com.wtp.base.json;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

@Slf4j
public class ResolvingJson {

    public static String readFileToJson(String filepath) {
        if (StringUtils.isNotBlank(filepath) || !filepath.endsWith(".json")) {
            log.error("文件有误！");
        }
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            char[] flag = new char[1024];
            int a = 0;
            while ((a = bufferedReader.read(flag)) != -1) {
                builder.append(flag, 0, a);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            log.error("文件路劲有误!");
        } catch (IOException e) {
            log.error("读取文件错误!");
        }
        return builder.toString();
    }

    public static void readStringToJsonObject(String jsonString) {

    }

    public static void main(String[] args) {
        String jsonString = readFileToJson("/Users/wangtaiping/IdeaProjects/open/resources/country.json");
        List<CountryItem> countryItems = JSONObject.parseArray(jsonString, CountryItem.class);
        countryItems.forEach(a -> {
            System.out.println(a.getLabel());
            a.getItems().forEach(country -> System.out.println("--" + country.getName()));
        });

    }

    @Data
    public static class CountryItem {
        private String label;
        private List<Country> items;

        @Data
        public static class Country {
            private String id;
            private String iso;
            private String maxLevel;
            private String name;
            private String phonePrefix;
            private String pinYin;
        }
    }

}
