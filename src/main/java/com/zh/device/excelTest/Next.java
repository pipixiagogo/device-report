package com.zh.device.excelTest;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zh.device.excelTest.ListOut.ExcelPropertyIndexModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Next {
    public static void main(String[] args) throws FileNotFoundException {
        // 生成EXCEL并指定输出路径
        OutputStream out = new FileOutputStream("D:\\excel\\withoutHead1.xlsx");
//        Table table = new Table(1);
//        List<List<String>> titles = new ArrayList<>();
//        titles.add(Arrays.asList("用户ID"));
//        titles.add(Arrays.asList("名称"));
//        titles.add(Arrays.asList("年龄"));
//        titles.add(Arrays.asList("生日"));
//        table.setHead(titles);
        // 查询数据导出即可 比如说一次性总共查询出100条数据
        List<List<String>> userList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));
        }
        ExecutorService pool = null;
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            synchronized (writer) {
            final int sheetCount = 5;
            pool = Executors.newFixedThreadPool(4);
            for (int i = 1; i < 5; i++) {
                final int index = i;
                pool.execute(() -> {
                    // 设置SHEET
                    try {
                        Thread.sleep(1000L * index);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Sheet sheet = new Sheet(index, 0, ExcelPropertyIndexModel.class);
                    sheet.setSheetName("sheet" + index);
                    writer.write0(userList, sheet);
//                        writer.finish();
                });
            }
            Thread.sleep(12000);
            writer.finish();
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                pool.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
