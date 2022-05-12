package com.wtp.base.util.file;

import lombok.Data;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReadLineUtil {

    public static void main(String[] args) throws IOException {
        //parseLineOne("/Users/wangtaiping/IdeaProjects/ERP_backend/sales/src/main/resources/com/ihoment/mapper/self/sales/country-new");
        parseLineTwo("/Users/wangtaiping/IdeaProjects/ERP_backend/sales/src/main/resources/com/ihoment/mapper/self/sales/country-new");
    }

    public static void parseLineOne(String filepath) throws IOException {
        File file = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        Map<Integer, CountryModel> countryModelMap = new HashMap<>();
        ArrayList<CountryModel> countryModelList = new ArrayList<>();
        String line = "";
        int flag = 17;
        for (int i = 0; i < 82; i++) {
            line = bufferedReader.readLine();
            String[] split = line.split("-");
            CountryModel countryModel = new CountryModel();
            int id = flag + 1 + i;
            countryModel.setMark(split[0]);
            countryModel.setId(id);
            countryModel.setCnName(split[1]);
            countryModel.setFullName(split[2]);
            countryModel.setEnumName("COUNTRY" + "_" + split[0]);
            countryModelList.add(countryModel);
            countryModelMap.put(id, countryModel);
        }
        bufferedReader.close();

        countryModelMap.forEach((k, v) -> {
            //System.out.print(k+":"+v.getCnName());
        });

        List<CountryModel> sortedList = countryModelList.stream().sorted(Comparator.comparingInt(CountryModel::getId)).collect(Collectors.toList());

        sortedList.forEach(a -> {
            StringBuilder builder = new StringBuilder();
            builder.append(a.getEnumName()).append("(").append(a.getId() + ",").append("\"" + a.getCnName() + "\",").append("\"" + a.getMark() + "\",").append("\"" + a.getFullName() + "\"").append(")").append(",");
            System.out.println(builder);
        });

    }

    public static void parseLineTwo(String filepath) throws IOException {
        File file = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        Map<Integer, CountryModel> countryModelMap = new HashMap<>();
        ArrayList<CountryModel> countryModelList = new ArrayList<>();
        String line = "";
        int flag = 17;
        for (int i = 1; i <= 131; i++) {
                line = bufferedReader.readLine();
            if (i >= 83) {
                String[] split = line.split("-");
                CountryModel countryModel = new CountryModel();
                int id = flag + 1 + i;
                countryModel.setMark(split[0]);
                countryModel.setId(id);
                countryModel.setCnName(split[1]);
                countryModel.setFullName(split[2]);
                countryModel.setEnumName("COUNTRY" + "_" + split[0]);
                countryModelList.add(countryModel);
                countryModelMap.put(id, countryModel);
            }

            List<CountryModel> sortedList = countryModelList.stream().sorted(Comparator.comparingInt(CountryModel::getId)).collect(Collectors.toList());

            sortedList.forEach(a -> {
                StringBuilder builder = new StringBuilder();
                builder.append(a.getEnumName()).append("(").append(a.getId() + ",").append("\"" + a.getCnName() + "\",").append("\"" + a.getMark() + "\",").append("\"" + a.getFullName() + "\"").append(")").append(",");
                System.out.println(builder);
            });
        }
        bufferedReader.close();
    }

    @Data
    public static class CountryModel {
        private String enumName;
        private Integer id;
        private String cnName;
        private String fullName;
        private String mark;
    }
}
