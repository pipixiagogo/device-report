package com.zh.device.service;

import com.zh.device.common.Const;
import com.zh.device.message.JMessage;
import com.zh.device.util.ByteUtil;
import com.zh.device.util.MsgBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service("rdbUpgradeService")
public class RdbUpgradeService {
    private static final Logger log = LoggerFactory.getLogger(RdbUpgradeService.class);
    @Autowired
    private MessageHandler mqttOutbound;

    public void handle(Message<JMessage> message) {
        JMessage jMessage = message.getPayload();
        byte mid = jMessage.getMid();
        switch (mid){
            case (byte)0x01:
                handleUpgrade(jMessage);
                break;
            case (byte)0x84:
                handleUpgradeResult(jMessage);
                break;
            case (byte)0x10:
                cancelUpgrande(jMessage);
                break;
        }
    }

    private void handleUpgradeResult(JMessage jMessage) {
        log.info("收到平台发送来的升级应答:",ByteUtil.toHexString(jMessage.getServiceData()));
        String deviceName = jMessage.getDeviceName();
        String sendTopic = Const.getSendTopic(deviceName);
        byte[] sendData= new byte[4];
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] bytes = msgBuilder.setVersion(Const.DEFAULT_VERSION).setBid(Const.DEFAULT_BID)
                .setAid((byte) 0x42).setMid((byte) 0x05).setServiceData(sendData)
                .build();
        Message<byte[]> message = MessageBuilder.withPayload(bytes).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    private void cancelUpgrande(JMessage jMessage) {
        log.info("收到平台发送来的取消升级请求", ByteUtil.toHexString(jMessage.getServiceData()));
        ByteBuf buf = Unpooled.wrappedBuffer(jMessage.getServiceData());
        buf.readByte();
        buf.skipBytes(2);
        byte[] uid=new byte[32];
        buf.readBytes(uid);
        byte[] serviceData=getSendServiceData(uid);
        String sendTopic = Const.getSendTopic(jMessage.getDeviceName());
        MsgBuilder msgBuilder1 =new MsgBuilder();
        byte[] bytes = msgBuilder1.setAid(jMessage.getAid()).setMid((byte) 0x90).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setServiceData(serviceData).build();
        Message<byte[]> message1 = MessageBuilder.withPayload(bytes).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message1);
    }

    private byte[] getSendServiceData(byte[] uid) {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(0);
        buffer.writeBytes(new byte[3]);
        buffer.writeByte(0);
        buffer.writeBytes(uid);
        byte[] serviceData = new byte[buffer.readableBytes()];
        buffer.readBytes(serviceData);
        return serviceData;
    }

    private void handleUpgrade(JMessage jMessage) {
        log.info("收到平台发送来的升级请求", ByteUtil.toHexString(jMessage.getServiceData()));
        ByteBuf buf = Unpooled.wrappedBuffer(jMessage.getServiceData());
        byte b = buf.readByte();
        System.out.println("设备编号"+b);
        buf.skipBytes(4);
        byte[] md5Byte=new byte[16];
        buf.readBytes(md5Byte);
        byte[]msgCheck=new byte[32];
        buf.readBytes(msgCheck);
        byte[]msgCompa=new byte[32];
        buf.readBytes(msgCompa);
        byte[] url=new byte[100];
        buf.readBytes(url);
        String urls=new String(url, Charset.forName("utf-8"));
        System.out.println(urls);
        String sendTopic = Const.getSendTopic(jMessage.getDeviceName());
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setAid(jMessage.getAid()).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setMid((byte) 0x81).setServiceData(new byte[]{(byte) 0x00}).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        System.out.println("321");
        mqttOutbound.handleMessage(message);
//        MsgBuilder msgBuilder1 =new MsgBuilder();
//        byte[] bytes = msgBuilder1.setAid(jMessage.getAid()).setMid((byte) 0x02).setVersion(Const.DEFAULT_VERSION)
//                .setBid(Const.DEFAULT_BID).setServiceData(new byte[4]).build();
//        Message<byte[]> message1 = MessageBuilder.withPayload(bytes).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        mqttOutbound.handleMessage(message1);
    }
}
