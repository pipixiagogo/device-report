package com.zh.device.excelTest.poiExcel;

import com.alibaba.excel.util.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static void main(String[] args) throws IOException, NoSuchMethodException {
        ArrayList<Shop> objects = new ArrayList<>();
        Shop shop ;
        for(int i=0;i<100000;i++){
            shop= new Shop();
            shop.setAccountName("tbwendwnencnwenc"+i);
            shop.setShopName("测试1店铺");
            shop.setShopUrl("https://blog.csdn.net/zxl646801924/article/details/80061954");
            objects.add(shop);
        }
        HSSFWorkbook sheets = ExcelUtil.generateExcelBook(Shop.class, objects);
        System.out.println("sheets");

        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("D://aaa.xlsx");
            sheets.write(fout);
            String str = "导出成功！";
            System.out.println(str);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            String str1 = "导出失败！";
            System.out.println(str1);
        }
    }

    /**
     * 导出数据生成EXCEL方法
     * @param list
     * @throws IOException
     */
    public static <T> HSSFWorkbook generateExcelBook(Class tClass, List<T> list)
            throws IOException, NoSuchMethodException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        if (CollectionUtils.isEmpty(list)) {
            return workbook;
        }
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet();

        Field[] allFields = tClass.getDeclaredFields();
        //有效字段
        List<Field> validFields =new ArrayList<>();
        // 收集有效的属性字段，设置列宽（注解中columnWidth）
        for(Field f : allFields){
            if(f.isAnnotationPresent(ExcelAnnotation.class)){
                ExcelAnnotation annotation = f.getAnnotation(ExcelAnnotation.class);
                int order = annotation.order();
                validFields.add(order, f);
                sheet.setColumnWidth(order, annotation.columnWidth());
            }
        }
        // 设置表头字体样式
        HSSFFont columnHeadFont = workbook.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short) 10);

        // 列头的样式
        HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        // 左右居中
        columnHeadStyle.setAlignment(HorizontalAlignment.CENTER);
        // 上下居中
        columnHeadStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
        // 左边框的颜色 可忽略
        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        // 边框的大小
        columnHeadStyle.setBorderLeft(BorderStyle.THIN);
        // 右边框的颜色 可忽略
        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);
        // 边框的大小
        columnHeadStyle.setBorderRight(BorderStyle.THIN);
        // 设置单元格的边框为粗体
        columnHeadStyle.setBorderBottom(BorderStyle.THICK);
        // 设置单元格的边框颜色
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        // 设置普通单元格字体样式
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);

        //创建Excel工作表第一行
        int headRow = 0;
        HSSFRow row0 = sheet.createRow(headRow);
        // 设置行高
        short defaultHeight = 750;
        row0.setHeight(defaultHeight);

        for(Field f : validFields){
            ExcelAnnotation annotation = f.getAnnotation(ExcelAnnotation.class);
            HSSFCell cell = row0.createCell(annotation.order());
            //设置单元格内容
            cell.setCellValue(new HSSFRichTextString(annotation.headName()));
        }

        // 循环写入数据
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            for(int j = 0; j < validFields.size(); j ++){
                Field field = validFields.get(j);
                Method method = tClass.getMethod(getMethod(field));
                if(method != null){
                    try {
                        HSSFCell cell = row.createCell(j);
                        String value = method.invoke(t) == null ? "" : method.invoke(t).toString();
                        cell.setCellValue(new HSSFRichTextString(value));
                        cell.setCellStyle(columnHeadStyle);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return workbook;
    }

    private static String getMethod(Field field) {
        char[] ch = field.getName().toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return "get" + String.valueOf(ch);
    }
}
