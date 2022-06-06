package com.wtp.base.json;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ResolvingJsonForEachCountry {


    public static List<OutState> resolveAmerican(String filename) throws IOException {

        String jsonString = ResolvingJson.readFileToJson(filename);
        List<OutState> outStateList = JSONObject.parseArray(jsonString, OutState.class);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/美国各州sql.sql"));
        AtomicInteger flag = new AtomicInteger(236);
        outStateList.forEach(a -> {
            try {
                bufferedWriter.write("INSERT INTO `app_administration` ( `id`,`en_name`, `parent_id`) VALUES " + String.format(" ( '%d','%s','%s');", flag.get(), a.getName(), 107));
                bufferedWriter.newLine();
                List<OutState.InnerCity> values = a.getValues();
                values.forEach(innerCity -> {
                    innerCity.getItems().forEach(city -> {
                        try {
                            bufferedWriter.write("INSERT INTO `app_administration` ( `en_name`, `parent_id`) VALUES " + String.format(" ( '%s','%s');", city.getName(), flag.get()));
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag.incrementAndGet();

        });
        bufferedWriter.close();
        return outStateList;
    }

    @Data
    public static class OutState {
        private String name;
        private List<InnerCity> values;
        private Integer parentId;


        @Data
        public static class InnerCity {
            private String label;
            private List<City> items;

            @Data
            public static class City {
                private String name;
                private String id;
                private Integer parentId;
            }
        }
    }


    @Test
    public void testOut() throws IOException {
        List<OutState> outStates = resolveAmerican("resources/美国.json");
    }


}
