package com.zh.device.service;

import com.zh.device.common.Const;
import com.zh.device.message.JMessage;
import com.zh.device.util.MsgBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service(value = "restartService")
public class RestartService {

    @Autowired
    private MessageHandler mqttOutbound;

    public void handle(Message<JMessage> message) {
        System.out.println("收到平台发送重启指令");
        JMessage jMessage = message.getPayload();
        String deviceName = jMessage.getDeviceName();
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder=new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION).setAid((byte) 0x45)
                .setMid((byte) 0x81).setServiceData(new byte[]{(byte) 0x01}).build();
        Message<byte[]> message1 = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message1);
    }


}
