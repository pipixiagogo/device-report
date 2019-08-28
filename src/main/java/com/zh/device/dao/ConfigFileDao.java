package com.zh.device.dao;

import com.bugull.mongo.BuguDao;
import com.zh.device.Entity.ConfigFile;
import org.springframework.stereotype.Repository;

//使用无参构造器 spring注入IOC时候会调用无参构造器
@Repository
public class ConfigFileDao extends BuguDao<ConfigFile> {
    public ConfigFileDao(){
        super(ConfigFile.class);
    }
}
