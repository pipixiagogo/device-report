package com.zh.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.bugull.mongo.utils.StringUtil;
import com.zh.device.common.Const;
import com.zh.device.util.HttpClientUtil;
import org.apache.http.client.config.RequestConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HttpConnec {

    @RequestMapping(value = "get")
    @ResponseBody
    public String get(String rdbsn){
        Map<String,String> params=new HashMap<>();
        params.put("rdbsn",rdbsn);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(5000)// 请求超时时间
                .setSocketTimeout(10000)// 数据读取超时时间
                .build();
        JSONObject jsonObject=null;
        //"localhost:8088/validateDevice"
        String url="http://localhost"+":"+8088+"/validateDevice";
        url=url.trim();
        try {
            jsonObject=HttpClientUtil.getInstanceWithoutSSL().get(url,params,null,requestConfig);
        }catch (IOException e){
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/validateDevice")
    @ResponseBody
    public String validateDevice(@RequestParam String rdbsn){
        JSONObject jsonObject = new JSONObject();
        if(StringUtil.isEmpty(rdbsn)){
            jsonObject.put("code", Const.DOMAIN_DENY);
            jsonObject.put("msg","验证失败");
            return jsonObject.toString();
        }
        if(!StringUtil.isEmpty(rdbsn)){
            jsonObject.put("code",Const.DOMAIN_ACCESS);
            jsonObject.put("msg","验证成功");
        }else {
            jsonObject.put("code",Const.DOMAIN_DENY);
            jsonObject.put("msg","验证失败");
        }
        return jsonObject.toString();
    }
}
