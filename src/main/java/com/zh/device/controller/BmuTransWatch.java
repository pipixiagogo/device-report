package com.zh.device.controller;

import com.bugull.mongo.BuguQuery;
import com.zh.device.Entity.ConfigFile;
import com.zh.device.common.Const;
import com.zh.device.dao.ConfigFileDao;
import com.zh.device.util.ByteUtil;
import com.zh.device.util.MsgBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BmuTransWatch {

    @Autowired
    private MessageHandler mqttOutbound;
    @Autowired
    private ConfigFileDao configFileDao;

    @RequestMapping(value = "reportBreakLog")
    public void reportBreakLog(int gsm, int falsh, int errpom, int can, int tsp) {
        //f30c8e5a68594e77a87b46be4a7bb0d1   3015790210c94626950618ca4b69715a
        String deviceName = "3015790210c94626950618ca4b69715a";
        String sendTopic = Const.getSendTopic(deviceName);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeShort(5);

        buffer.writeInt(4);
        buffer.writeByte(1);
        //GSM
        buffer.writeShort(0);
        buffer.writeShort(gsm);

        buffer.writeInt(4);
        buffer.writeByte(1);
        //FALSH
        buffer.writeShort(1);
        buffer.writeShort(falsh);
        buffer.writeInt(4);
        buffer.writeByte(1);
        //errpom
        buffer.writeShort(2);
        buffer.writeShort(errpom);

        buffer.writeInt(4);
        buffer.writeByte(1);
        //can
        buffer.writeShort(3);
        buffer.writeShort(can);

        buffer.writeInt(4);
        buffer.writeByte(1);
        //tsp
        buffer.writeShort(4);
        buffer.writeShort(tsp);
        byte[] send = new byte[buffer.readableBytes()];
        buffer.readBytes(send);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setAid((byte) 0x82).setMid((byte) 0x02).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setServiceData(send).build();
        Message<byte[]> build1 = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(build1);
    }

    @RequestMapping(value = "reportOldBreakLog")
    public void reportOldBreakLog(int can, int canState, int gsm, int flash) {
        //f30c8e5a68594e77a87b46be4a7bb0d1  3015790210c94626950618ca4b69715a
        String deviceName = "3015790210c94626950618ca4b69715a";
        String sendTopic = Const.getSendTopic(deviceName);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(1);
        buffer.writeByte(2);
        buffer.writeByte(can);//CAN0-链路状态
        buffer.writeByte(canState);//CAN0-协议状态
        buffer.writeBytes(new byte[4]);
        buffer.writeByte(gsm);//GSM状态
        buffer.writeByte(flash);//FLASH状态
        byte[] send = new byte[buffer.readableBytes()];
        buffer.readBytes(send);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setAid((byte) 0x82).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setServiceData(send).build();
        Message<byte[]> build1 = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(build1);
    }

    @RequestMapping(value = "startDownload")
    public void startDownload(String deviceName) {
        String sendTopic = Const.getSendTopic(deviceName);
        //上报开始下载
        MsgBuilder msgBuilder1 = new MsgBuilder();
        byte[] sendData = prepareStartDown();
        byte[] bytes1 = msgBuilder1.setAid((byte) 0x43).setMid((byte) 0x02).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setServiceData(sendData).build();
        Message<byte[]> message1 = MessageBuilder.withPayload(bytes1).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message1);
    }

    private byte[] prepareStartDown() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(new byte[2]);
        buffer.writeShort((byte) 0x00);
        byte[] sendData = new byte[buffer.readableBytes()];
        buffer.readBytes(sendData);
        return sendData;
    }

    @RequestMapping(value = "downLoadResult")
    public void downLoadResult(String deviceName) {
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x03).setAid((byte) 0x43).setServiceData(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    @RequestMapping(value = "startTrans")
    public void startTrans(String deviceName) {
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x04).setAid((byte) 0x43).setServiceData(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00}).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    @RequestMapping(value = "transProgress")
    public void transProgress(String deviceName) {
        String sendTopic = Const.getSendTopic(deviceName);
        byte[] sendData = prepare((byte) 0x03);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x03).setAid((byte) 0x81).setServiceData(sendData).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    @RequestMapping(value = "startTrans2")
    public void startTrans2(String deviceName) {
        String sendTopic = Const.getSendTopic(deviceName);
        byte[] sendData = prepare((byte) 0x01);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x03).setAid((byte) 0x81).setServiceData(sendData).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    private byte[] prepare(byte type) {
        BuguQuery<ConfigFile> query = configFileDao.query().is("deviceName", "default");
        ConfigFile result = query.result();
        byte[] checSum = result.getChecksum();
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(0);
        buf.writeByte(type);
        buf.writeByte((byte) 0x50);
        buf.writeByte(15);
        buf.writeByte(0);
        buf.writeInt(50);
        buf.writeInt(100);
        buf.writeBytes(checSum);
        byte[] sendByte = new byte[buf.readableBytes()];
        buf.readBytes(sendByte);
        return sendByte;
    }

    @RequestMapping(value = "transEnd")
    public void transEnd(String deviceName, Integer code) {
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] transIdentity = buildTransIdentity(code);
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x05).setAid((byte) 0x43).setServiceData(transIdentity).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    private byte[] buildTransIdentity(Integer cide) {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(new byte[2]);
        buffer.writeByte(cide);
        byte[] transIdentity = new byte[4];
        if (cide != 0 && cide != 2) {
            transIdentity = new byte[]{(byte) 0x02, (byte) 0x02, (byte) 0x03, (byte) 0x04};
        }
        buffer.writeBytes(transIdentity);
        byte[] sendData = new byte[buffer.readableBytes()];
        buffer.readBytes(sendData);
        return sendData;
    }

    @RequestMapping(value = "transErrorLog")
    public void transErrorLog(String deviceName) {
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] serviceData = buildTransErrorData();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setAid((byte) 0x43).setMid((byte) 0x07)
                .setServiceData(serviceData).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    private byte[] buildTransErrorData() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(new byte[2]);
        buffer.writeBytes(new byte[]{(byte) 0x02, (byte) 0x02, (byte) 0x03, (byte) 0x04});
        buffer.writeShort(1);
        buffer.writeShort(0);
        buffer.writeShort(1);
        buffer.writeBytes(ByteUtil.fromInt(417002745));
        buffer.writeBytes(ByteUtil.fromInt(143228));
        //21FF59724406172E
        buffer.writeBytes(new byte[]{(byte) 0x21, (byte) 0xFF, (byte) 0x59, (byte) 0x72, (byte) 0x44,
                (byte) 0x06, (byte) 0x17, (byte) 0x2E});
        byte[] sendData = new byte[buffer.readableBytes()];
        buffer.readBytes(sendData);
        return sendData;
    }

    @RequestMapping(value = "upgradeResult")
    public void upgradeResult(String deviceName) {
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] sendData = new byte[3];
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x06).setAid((byte) 0x43).setServiceData(sendData).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }
}
