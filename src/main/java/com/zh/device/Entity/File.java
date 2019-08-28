package com.zh.device.Entity;

import com.bugull.mongo.SimpleEntity;
import com.bugull.mongo.annotations.Entity;

import java.util.Date;

@Entity
public class File extends SimpleEntity{
    private String fileName;
    private Date uploadTime;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
