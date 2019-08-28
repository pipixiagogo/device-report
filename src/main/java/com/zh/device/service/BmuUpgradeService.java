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

@Service("bmuUpgradeService")
public class BmuUpgradeService {
    private static final Logger log = LoggerFactory.getLogger(RdbUpgradeService.class);
    @Autowired
    private MessageHandler mqttOutbound;

    public void handle(Message<JMessage> message) {
        JMessage jMessage = message.getPayload();
        byte mid = jMessage.getMid();
        switch (mid) {
            case (byte) 0x01:
                handleBmuUpgrade(jMessage);
                break;

            case (byte) 0x10:
                cancelUpgrande(jMessage);
                break;
        }
    }

    private void cancelUpgrande(JMessage jMessage) {
        log.info("收到平台发送来的取消升级请求", ByteUtil.toHexString(jMessage.getServiceData()));
        ByteBuf buf = Unpooled.wrappedBuffer(jMessage.getServiceData());
        buf.skipBytes(2);
        byte[] uid = new byte[32];
        buf.readBytes(uid);
        byte[] serviceData = getSendServiceData(uid);
        String sendTopic = Const.getSendTopic(jMessage.getDeviceName());
        MsgBuilder msgBuilder1 = new MsgBuilder();
        byte[] bytes = msgBuilder1.setAid(jMessage.getAid()).setMid((byte) 0x90).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setServiceData(serviceData).build();
        Message<byte[]> message1 = MessageBuilder.withPayload(bytes).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message1);
    }

    private byte[] getSendServiceData(byte[] uid) {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(new byte[4]);
        buffer.writeBytes(uid);
        byte[] serviceData = new byte[buffer.readableBytes()];
        buffer.readBytes(serviceData);
        return serviceData;
    }

    private void handleBmuUpgrade(JMessage jMessage) {
        ByteBuf buf = Unpooled.wrappedBuffer(jMessage.getServiceData());
        buf.skipBytes(84);
        byte[] urlByte = new byte[100];
        buf.readBytes(urlByte);
        System.out.println("url为" + new String(urlByte));
        log.info("收到平台发送来的升级请求", ByteUtil.toHexString(jMessage.getServiceData()));
        String deviceName = jMessage.getDeviceName();
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] bytes = msgBuilder.setAid((byte) 0x43).setMid((byte) 0x81).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setServiceData(new byte[]{(byte) 0x00}).build();
        Message<byte[]> message = MessageBuilder.withPayload(bytes).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

}
