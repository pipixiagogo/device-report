package com.zh.device;

import com.zh.device.service.ConfigFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {

    @Autowired
    private ConfigFileService configFileService;

    @Override
    public void run(String... args) throws Exception {

//        configFileService.getAndSaveDefault();
    }

    public static void main(String[] args) {
        String domain="http://rdb.catlbattery.com:8090";
        String fileName ="eeca311b-d853-4e7d-a231-7a67f486e26d.pkg";
        String file="2032f43e-1466-4c5b-bc74-04e1eb7aa069.ctl";
        String files="e3902311-0aa7-44be-8bf3-a253473be8fb";
//        String str=domain1+"/download?file=" + fileName+"&rdbsn"+rdbsn;
//        System.out.println(str.getBytes().length);
        //01991907500067
        //01991923500381
        domain += "/DL?file=" + files+"&sn=01991907500067";
        System.out.println(domain);
        System.out.println(domain.getBytes().length);
//        DecimalFormat df = new DecimalFormat("0.00%");
//        System.out.println(df.format(0.1234));

    }
}
