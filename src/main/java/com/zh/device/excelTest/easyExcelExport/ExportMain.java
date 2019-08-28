//package com.zh.device.excelTest.easyExcelExport;
//
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.support.ExcelTypeEnum;
//import com.bugull.mongo.utils.ThreadUtil;
//import com.zh.device.excelTest.ExcelWriteTest;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class ExportMain {
//    private static Object lock = new Object();
//
//    public static void main(String[] args) throws FileNotFoundException {
//        // 生成EXCEL并指定输出路径
//        OutputStream out = new FileOutputStream("D:\\excel\\withoutHead1.xlsx");
////        Table table = new Table(1);
////        List<List<String>> titles = new ArrayList<>();
////        titles.add(Arrays.asList("用户ID"));
////        titles.add(Arrays.asList("名称"));
////        titles.add(Arrays.asList("年龄"));
////        titles.add(Arrays.asList("生日"));
////        table.setHead(titles);
//        // 查询数据导出即可 比如说一次性总共查询出100条数据
//        List<List<String>> userList = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));
//        }
//        ExecutorService pool = null;
//        try {
//            final int sheetCount = 5;
//            pool = Executors.newFixedThreadPool(sheetCount);
//            ExcelWriter writeTest = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            for (int i = 1; i < 2; i++) {
//                pool.execute(new excel(writeTest,i,out,userList));
//            }
//            try {
//                Thread.sleep(12000L);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//            writeTest.finish();
//
//        } finally {
//            try {
//                ThreadUtil.safeClose(pool);
//                out.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private class excel implements Runnable {
//        private ExcelWriter excelWriter;
//        private int index;
//        private OutputStream out;
//        private List<List<String>> data;
//        public excel(ExcelWriter excelWriter,int index,OutputStream out,List<List<String>> data) {
//            this.excelWriter=excelWriter;
//            this.data=data;
//            this.index=index;
//            this.out=out;
//        }
//
//        @Override
//        public void run() {
//            excelWriter = new ExcelWriteTest(out, ExcelTypeEnum.XLSX
//                    , index, 0, "sheetName" + index);
//            excelWriter.write0(data, null);
//        }
//    }
//}
//
