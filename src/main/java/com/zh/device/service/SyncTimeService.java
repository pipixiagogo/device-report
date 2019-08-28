package com.zh.device.service;

import com.zh.device.message.JMessage;
import com.zh.device.util.ByteUtil;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service(value = "syncTimeService")
public class SyncTimeService {

    public void handle(Message<JMessage> message){
        JMessage jMessage = message.getPayload();
        System.out.println("平台应答设备时间同步"+ByteUtil.toHexString(jMessage.getServiceData()));
    }
}
