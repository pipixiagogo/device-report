package com.zh.device.dao;

import com.bugull.mongo.BuguDao;
import com.mongodb.ReadPreference;
import com.zh.device.Entity.Blog;
import org.springframework.stereotype.Repository;

@Repository
public class SecondaryBlogDao extends BuguDao<Blog> {
    public SecondaryBlogDao() {
        super(Blog.class);
        //设置该DAO优先从Secondary读取
        super.setReadPreference(ReadPreference.secondaryPreferred());
    }
}
