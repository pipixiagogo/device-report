//package com.zh.device.controller;
//
//import com.zh.device.service.FileUploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Controller
//public class FileUploadController {
//
//    @Autowired
//    private FileUploadService uploadService;
//    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    @ResponseBody
//    public String upload(MultipartFile file, HttpServletRequest request , HttpServletResponse response){
//        System.out.println("321");
//        return uploadService.upload(file,request,response);
//    }
//
//
//}
