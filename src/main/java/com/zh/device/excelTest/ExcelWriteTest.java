package com.zh.device.excelTest;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.ExcelBuilder;
import com.zh.device.excelTest.ListOut.ExcelPropertyIndexModel;

import java.io.OutputStream;
import java.util.List;

public class ExcelWriteTest extends ExcelWriter {
    private ExcelBuilder excelBuilder;
    public Object LOCK=new Object();
    public Object LOCK2=new Object();
    private int sheetNo;
    private int headLineMub;
    private String sheetName;
    public ExcelWriteTest(OutputStream outputStream, ExcelTypeEnum typeEnum,int sheetNo,int headLineMub,String sheetName) {
       this(outputStream,typeEnum,true);
        this.sheetNo=sheetNo;
        this.headLineMub=headLineMub;
        this.sheetName=sheetName;
    }

    public ExcelWriteTest(OutputStream outputStream, ExcelTypeEnum typeEnum, boolean needHead) {
        super(outputStream, typeEnum);
        this.excelBuilder = new ExcelBuilder1(null,outputStream,typeEnum,needHead,null);

    }

    public void finish() {
        this.excelBuilder.finish();
    }
    @Override
    public ExcelWriter write0(List<List<String>> data, Sheet sheet) {
        synchronized (LOCK){
            Sheet sheet1 = new Sheet(sheetNo, headLineMub, ExcelPropertyIndexModel.class);
            sheet.setSheetName(sheetName);
            this.excelBuilder.addContent(data, sheet1);
            return this;
        }
    }
}
