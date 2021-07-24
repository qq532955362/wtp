package org.example.wtp;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wtp
 * @date 2021年7月23日17:31:42
 *
 */
public class TxtFileReadUtil {

    public static void readPhoneFromTxt(String sourceFilepath) throws IOException {

        File file = new File(sourceFilepath);

        //暂时写死,如有必要可以传入
        File desFile = new File("D:\\phone.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(desFile));
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            if (line == null) {
                break;
            }
            stringBuilder.append("'").append(line).append("',");
        }
        bufferedWriter.write(stringBuilder.toString());
        bufferedWriter.flush();
    }

    public static void main(String[] args) throws IOException {
        readPhoneFromTxt("C:\\Users\\Administrator\\Documents\\WXWork\\1688850835112175\\Cache\\File\\2021-07\\记忆力主修训练营2期.txt");
    }

}
