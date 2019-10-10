//package com.zh.device.service;
//
//import com.bugull.mongo.fs.Uploader;
//import com.bugull.mongo.fs.HttpFileGetter;
//import com.zh.device.Entity.File;
//import com.zh.device.dao.FileDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.InputStream;
//import java.util.Date;
//
//@Service
//public class FileUploadService {
//
//    @Autowired
//    private FileDao fileDao;
//
//    public String upload(MultipartFile file,HttpServletRequest request , HttpServletResponse response){
//        File file2 = fileDao.query().is("fileName", file.getOriginalFilename()).result();
//        if(file2!=null){
//            return "上传失败，该文件已存在";
//        }
//        try {
//            File file1 = new File();
//            InputStream stream = file.getInputStream();
//            String originalFilename = file.getOriginalFilename();
//            file1.setFileName(originalFilename);
//
//
//            Uploader uploader = new Uploader(stream,originalFilename,true);
//            uploader.save();
//            file1.setUploadTime(new Date());
//            fileDao.insert(file1);
//            HttpFileGetter getter = new HttpFileGetter(request,response);
//            getter.setContentMD5(true);
//            getter.response(uploader.getFilename());
//            return "上传成功";
//        }catch (Exception e){
//            return "上传失败";
//        }
//    }
//    public void getterFile(HttpServletRequest request , HttpServletResponse response){
//
//    }
//}
