package com.zh.device;


import com.bugull.mongo.BuguConnection;
import com.bugull.mongo.BuguFramework;
import com.mongodb.WriteResult;
import com.zh.device.Entity.ConfigFile;
import com.zh.device.dao.ConfigFileDao;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class TestDomain {
    public static void main(String[] args) {
//        byte[] bytes = new byte[]{(byte)0x31,(byte)0x31,(byte)0x39,
//                (byte)0x2E,(byte)0x32,(byte)0x33,(byte)0x2E,(byte)0x36,(byte)0x32,(byte)0x2E,
//                (byte)0x35};
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format1 = format.format(new Date());
//        System.out.println(format1);

//        DecimalFormat df = new DecimalFormat("0%");
//        double progress = (double)80.5 / 100;
//        System.out.println(df.format(progress));


        String mongoHost = "11hhw.tpddns.cn";
        int mongoPort = 27017;
        String mongoDB = "farm";
        String userName = "farm";
        String password = "catl";
        BuguConnection connection = BuguFramework.getInstance().createConnection();
        connection.connect(mongoHost, mongoPort, mongoDB, userName, password);
        System.out.println("mongoDB连接完成");
        ConfigFileDao configFileDao = new ConfigFileDao();
        ConfigFile result = configFileDao.query().is("deviceName", "default").result();
        Map<String,Object> parmas= new HashMap<>();
        parmas.put("checksum",result.getChecksum());
        parmas.put("objs",result.getObjs());
        parmas.put("fileBytes",result.getFileBytes());
        parmas.put("domain",result.getDomain());
        WriteResult execute = configFileDao.update().set(parmas).execute(configFileDao.query().notEquals("deviceName", "default"));
        System.out.println(execute.isUpdateOfExisting());

    }
}
