package org.example.wtp.controller;

import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test/excel")
public class ExcelController {

    @GetMapping
    public void export(HttpServletResponse response) throws IOException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream()).head(head()).sheet("模板").doWrite(new ArrayList<>());
    }

    public List<List<String>> head() {
        //初始化第一行表头
        String bigTitle = "1" + "至" + "2" + "不动产登记费用清单";
        //创建表头集合
        List<List<String>> head = new ArrayList<>();
        //这里的每一个list都是代表一列，按照顺序来，相同的名称会自动合并单元格，比如说两个序号，
        //就是把第第一列的第二第三格合并到了一起
        //然后行单元格合并是同理的，可以看到bigTitle每一个list都有，都是在第一个，说明每一列的
        //第一个单元格都是这个值，所以最终的效果就是你生成的表格第一行全部合并，然后可以展示大表
        //头,
        //第一列
        List<String> title = new ArrayList<>(Arrays.asList(bigTitle, "序号", "序号", "序号", "序号", "序号", "序号", "序号", "序号", "序号"));
        head.add(title);
        //第二列
        List<String> title1 = new ArrayList<>(Arrays.asList(bigTitle, "业务号", "业务号", "业务号", "业务号", "业务号", "业务号", "业务号", "业务号", "业务号"));
        head.add(title1);
        //第三列
        List<String> title2 = new ArrayList<>(Arrays.asList(bigTitle, "抵押权人", "抵押权人", "抵押权人", "抵押权人", "抵押权人", "抵押权人", "抵押权人", "抵押权人", "抵押权人"));
        head.add(title2);
        //第四列
        List<String> title3 = new ArrayList<>(Arrays.asList(bigTitle, "抵押权", "抵押权", "抵押权", "抵押权", "抵押权", "抵押权", "抵押权", "抵押权", "抵押权"));
        head.add(title3);

        //第五列
        List<String> title4 = new ArrayList<>(Arrays.asList(bigTitle, "坐落", "坐落", "坐落", "坐落", "坐落", "坐落", "坐落", "坐落", "坐落"));
        head.add(title4);

        //第六列
        List<String> title5 = new ArrayList<>(Arrays.asList(bigTitle, "抵押金额(万元)", "抵押金额(万元)", "抵押金额(万元)", "抵押金额(万元)", "抵押金额(万元)", "抵押金额(万元)", "抵押金额(万元)", "抵押金额(万元)", "抵押金额(万元)"));
        head.add(title5);

        //第七列
        List<String> title6 = new ArrayList<>(Arrays.asList(bigTitle, "登记时间", "登记时间", "登记时间", "登记时间", "登记时间", "登记时间", "登记时间", "登记时间", "登记时间"));
        head.add(title6);

        //第八列
        List<String> title7 = new ArrayList<>(Arrays.asList(bigTitle, "登记费(元)", "登记费(元)", "登记费(元)", "登记费(元)", "登记费(元)", "登记费(元)", "登记费(元)", "登记费(元)", "登记费(元)"));
        head.add(title7);

        //第九列
        List<String> title8 = new ArrayList<>(Arrays.asList(bigTitle, "交易服务费(元)", "交易服务费(元)", "交易服务费(元)", "交易服务费(元)", "交易服务费(元)", "交易服务费(元)", "交易服务费(元)", "交易服务费(元)", "交易服务费(元)"));
        head.add(title8);

        //最后直接把这些生成的表头集合返回就行了
        return head;
    }
}
