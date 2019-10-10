package com.zh.device;

import com.mongodb.WriteResult;
import com.zh.device.Entity.Blog;
import com.zh.device.Entity.People;
import com.zh.device.dao.BlogDao;
import com.zh.device.dao.PeopleDao;
import com.zh.device.dao.SecondaryBlogDao;
import com.zh.device.service.ConfigFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CommandRunner implements CommandLineRunner {

    @Autowired
    private ConfigFileService configFileService;

    @Autowired
    private PeopleDao peopleDao;
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private SecondaryBlogDao secondaryBlogDao;

    @Override
    public void run(String... args) {
        //分表
        People people = new People();
        people.setAddress("福州");
        people.setName("张三");
        people.setAge(18);
        Date date = new Date();
        String s1 = dateToStrLong(date);
        peopleDao.setSplitSuffix(s1);
        WriteResult insert = peopleDao.insert(people);
        System.out.println(insert.wasAcknowledged());
        System.out.println(insert.isUpdateOfExisting());

//        try {
//            Thread.sleep(60000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        Date date2 = new Date();
//        People people2 = new People();
//        people2.setAddress("广州");
//        people2.setName("张三");
//        people2.setAge(15);
//        String s = dateToStrLong(date2);
//        peopleDao.setSplitSuffix(s);
//        peopleDao.insert(people2);
//        System.out.println("插入成功");
//        //查询数据s
//        peopleDao.setSplitSuffix(s);
//        List<People> all = peopleDao.findAll();
//        for(People people1 :all){
//            System.out.println(people1.getAddress()+""+people1.getName()+""+people1.getAge());
//        }
//        peopleDao.setSplitSuffix(s1);
//        List<People> all1 = peopleDao.findAll();
//        for(People p:all1){
//            System.out.println(p.getAge()+""+p.getName()+""+p.getAddress());
//        }


        //读写分离
        Blog blog = new Blog();
        blog.setTitle("皮皮虾");
        blog.setContent("你是真的皮");
        blogDao.save(blog);

        Blog one = blogDao.findOne("title", "皮皮虾");
        System.out.println(one.getTitle()+""+one.getContent());

        //从从库读取
        Blog daoOne = secondaryBlogDao.findOne("title", "皮皮虾");
        System.out.println(daoOne.getTitle()+""+daoOne.getContent());
    }
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
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
        domain += "/DL?file=" + files+"&sn=01991939502464";
        System.out.println(domain);
        System.out.println(domain.getBytes().length);
//        DecimalFormat df = new DecimalFormat("0.00%");
//        System.out.println(df.format(0.1234));

    }
}
