package com.zh.device.excelTest.poiExcel;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelAnnotation {
    /**
     * 表头名字
     */
    String headName() default "";

    /**
     * 列序号
     */
    int order() default 0;

    /**
     * 列宽
     */
    int columnWidth() default 3500;
}
