package com.zh.device.excelTest.ListOut;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.WriteContext;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.ExcelBuilderImpl;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Out {
    public static void main(String[] args)throws FileNotFoundException {
        long l = System.currentTimeMillis();
        OutputStream out = new FileOutputStream("D:/80.xlsx");
        try {

            ExcelWriter writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLSX,true);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            //设置列宽 设置每列的宽度
            Map columnWidth = new HashMap();
            columnWidth.put(0,3000);columnWidth.put(1,40);columnWidth.put(2,10);columnWidth.put(3,10);

            // 构造单元格样式
            Sheet sheet1 = new Sheet(1, 0,ExcelPropertyIndexModel.class);
            sheet1.setColumnWidthMap(columnWidth);
//            sheet1.setHead(createTestListStringHead());
            //or 设置自适应宽度
            sheet1.setAutoWidth(Boolean.TRUE);
            List<ExcelPropertyIndexModel> list=new ArrayList<>();
            ExcelPropertyIndexModel model;
            for(int i=0;i<70000;i++){
                 model= new ExcelPropertyIndexModel();
                model.setName("皮皮虾");
                model.setSax("pipixia"+i);
                model.setLast("22");
                model.setHeigh("11");
                model.setEmail("454092626@qq.com");
                model.setAddress("马尾区");
                model.setAge("15");
                list.add(model);
            }
            writer.write(list, sheet1);
            long l1 = System.currentTimeMillis();
            System.out.println("填充数据花费时间"+(l1-l));
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * **获取workbook**
     * 因为EasyExcel这个库设计的原因
     * 只能使用反射获取workbook
     *
     * @param writer
     * @return
     */
    private static Workbook getWorkbook(ExcelWriter writer) {
        Workbook workbook = null;
        try {
            Class<?> clazz1 = Class.forName("com.alibaba.excel.ExcelWriter");
            Field[] fs = clazz1.getDeclaredFields();
            for (Field field : fs) {
                // 要设置属性可达，不然会抛出IllegalAccessException异常
                field.setAccessible(true);
                if ("excelBuilder".equals(field.getName())) {
                    ExcelBuilderImpl excelBuilder = (ExcelBuilderImpl) field.get(writer);
                    Class<?> clazz2 = Class.forName("com.alibaba.excel.write.ExcelBuilderImpl");
                    Field[] fs2 = clazz2.getDeclaredFields();
                    for (Field field2 : fs2) {
                        field2.setAccessible(true);
                        if ("context".equals(field2.getName())) {
                            WriteContext context = (WriteContext) field2.get(excelBuilder);
                            workbook = context.getWorkbook();
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return workbook;
    }

}
