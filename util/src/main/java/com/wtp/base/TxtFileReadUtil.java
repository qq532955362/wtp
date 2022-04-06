package com.wtp.base;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @author wtp
 * @date 2021年7月23日17:31:42
 */
public class TxtFileReadUtil {

    /**
     * 将文本文件中的电话号码以 'a','b'...的格式读到另外的文本中
     * 目标文本文件目录暂时写死
     *
     * @param sourceFilepath 源文本目录
     * @throws IOException IOException
     */
    public static void readPhoneFromTxt(String sourceFilepath) throws IOException {

        File file = new File(sourceFilepath);

        //暂时写死,如有必要可以传入
//        File desFile = new File("D:\\phone.txt");
        File desFile = new File("/Users/wangtaiping/Desktop/phone.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(desFile));
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            //TODO 这里写个正则过滤一下电话 不是电话的输出一个提示
            stringBuilder.append("'").append(line).append("',");
        }
        bufferedWriter.write(stringBuilder.toString());
        bufferedWriter.flush();
    }

    public static void main(String[] args) throws IOException {
//        readPhoneFromTxt("C:\\Users\\Administrator\\Documents\\WXWork\\1688850835112175\\Cache\\File\\2021-07\\记忆力主修课第2期用户-0720(1).txt");
        readPhoneFromTxt("/Users/wangtaiping/Desktop/8.5-8.6.txt");
    }

}
