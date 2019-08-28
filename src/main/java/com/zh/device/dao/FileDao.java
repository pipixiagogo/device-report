package com.zh.device.dao;

import com.bugull.mongo.BuguDao;
import com.zh.device.Entity.File;
import org.springframework.stereotype.Repository;

@Repository
public class FileDao extends BuguDao<File> {
    public FileDao(){
        super(File.class);
    }
}
