package com.zh.device.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static Date transForDate(Integer ms){
        if(ms==null){
            ms=0;
        }
        long msl=(long)ms*1000;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp=null;
        if(ms!=null){
            try {
                String str=sdf.format(msl);
                temp=sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

}
