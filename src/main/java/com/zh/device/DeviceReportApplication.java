package com.zh.device;

import com.bugull.mongo.BuguConnection;
import com.bugull.mongo.BuguFramework;
import com.bugull.mongo.BuguQuery;
import com.mongodb.ServerAddress;
import com.mongodb.event.ServerListener;
import com.zh.device.Entity.ConfigFile;
import com.zh.device.dao.ConfigFileDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class DeviceReportApplication {
    //01991917500001
    //12345678901234567890
    public static void main(String[] args) {
        String mongoHost = "192.168.32.100";
//        String mongoHost = "61.154.32.197";
        int mongoPort = 27017;
        String mongoDB = "catl";
        String userName = "catl";
        String password = "catl";
        //集群连接方式
//        List<ServerAddress> list = new ArrayList<>();
//        ServerAddress serverAddress1 = new ServerAddress("192.168.49.128",20000);
//        ServerAddress serverAddress2 = new ServerAddress("192.168.49.128",20001);
//        ServerAddress serverAddress3 = new ServerAddress("192.168.49.128",20002);
//        list.add(serverAddress1);
//        list.add(serverAddress2);
//        list.add(serverAddress3);
//        BuguConnection connection = BuguFramework.getInstance().createConnection();
//        connection.setServerList(list);
//        connection.setDatabase("catl");
//
//        connection.connect();
        BuguConnection connection = BuguFramework.getInstance().createConnection();
        connection.connect(mongoHost, mongoPort, mongoDB, userName, password);
        System.out.println("mongoDB连接完成");

        SpringApplication.run(DeviceReportApplication.class, args);
    }
}
