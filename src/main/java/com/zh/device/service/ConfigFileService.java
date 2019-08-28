package com.zh.device.service;

import com.zh.device.Entity.CollectionObject;
import com.zh.device.Entity.ConfigFile;
import com.zh.device.common.Const;
import com.zh.device.config.PropertiesConfig;
import com.zh.device.dao.ConfigFileDao;
import com.zh.device.message.JMessage;
import com.zh.device.util.ByteReader;
import com.zh.device.util.ByteUtil;
import com.zh.device.util.MsgBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service(value = "configFileService")
public class ConfigFileService {

    @Autowired
    private MessageHandler mqttOutbound;
    @Autowired
    private ConfigFileDao configFileDao;
    @Autowired
    private PropertiesConfig propertiesConfig;

    public void handle(Message<JMessage> message) {
        System.out.println("收到平台配置文件");
        JMessage jMessage = message.getPayload();
        String deviceName = jMessage.getDeviceName();
        ByteBuf buf = Unpooled.wrappedBuffer(jMessage.getServiceData());
        byte[] checkSum = new byte[24];
        buf.readBytes(checkSum);
        long fileSize = buf.readUnsignedInt();
        long begin = buf.readUnsignedInt();
        long dataLength = buf.readUnsignedInt();
        System.out.println("fileSize" + fileSize + "   begin" + begin + "   dataLength" + dataLength);
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        ByteReader byteReader = new ByteReader(data);
        byte[] _checkSum = byteReader.read(24);
        if (ByteUtil.toHexString(checkSum).equalsIgnoreCase(ByteUtil.toHexString(_checkSum))) {
            byte[] sendServiceData = buildServiceData(checkSum);
            if (sendServiceData.length > 0 && sendServiceData != null) {
                String sendTopic = Const.getSendTopic(deviceName);
                MsgBuilder msgBuilder = new MsgBuilder();
                byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION).setAid((byte) 0x41)
                        .setMid((byte) 0x81).setServiceData(sendServiceData).build();
                Message<byte[]> message1 = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
                mqttOutbound.handleMessage(message1);
            } else {
                System.out.println("checkSum为空");
            }
        }
    }

    private byte[] buildServiceData(byte[] checkSum) {
        byte[] serviceData = null;
        if (checkSum != null && checkSum.length > 0) {
            ByteBuf buffer = Unpooled.buffer();
            buffer.writeBytes(checkSum);
            buffer.writeByte((byte) 0x01);
            buffer.writeInt(10);
            serviceData = new byte[buffer.readableBytes()];
            buffer.readBytes(serviceData);
        }
        return serviceData;
    }

    public void getAndSaveDefault() {
        ConfigFile configFile = configFileDao.query().is("deviceName", "default").result();
        if (configFile == null) {
            configFile = init();
            configFileDao.insert(configFile);
        }
    }

    public ConfigFile init() {
        ConfigFile configFile = new ConfigFile();
        configFile.setDeviceName("default");
        configFile.setReportingInterval(300);
        configFile.setBasicReportInterval(60);
        configFile.setHttpPort(80);
        configFile.setMqttPort(1883);
        configFile.setDomain("localhost");

        configFile.setCanCollectionInterval(30);
        configFile.setFreCollectionInterval(5);
        configFile.setFreCollectionSwitch(true);

        Set<Long> msgIds = propertiesConfig.getMsgIds();
        Set<CollectionObject> objs = new HashSet<>();
        for (Long msgId : msgIds) {
            CollectionObject object = new CollectionObject();
            object.setCanId(msgId);
            object.setCollectionTime(CollectionObject.COLLECTION_TIME);
            objs.add(object);
        }
        configFile.setObjs(objs);

        configFile.setGeneralInterval(10);
        configFile.setGeneralPreInterval(4);
        configFile.setDebugInterval(1);
        configFile.setDetailInterval(60);
        configFile.setExtendInterval(10);
        configFile.setGeneralIntervalSwitch(true);
        configFile.setDetailIntervalSwitch(false);
        configFile.setExtendIntervalSwitch(false);
        configFile.setDebugIntervalSwitch(false);
        configFile.setPreIntervalSwitch(true);

        configFile.update();

        return configFile;
    }


    public byte[] getDefaultChecSum() {
        ConfigFile file = configFileDao.query().is("deviceName", "default").result();

        return file.getChecksum();
    }

    public String doSendConfig(Integer startPos) {
        String deviceName = "453ef8a8ed914a57b9da031756951991";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] configData = buildConfigData(startPos);
        byte[] messages = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setAid((byte) 0x41).setMid((byte) 0x01).setServiceData(configData).build();
        Message<byte[]> message = MessageBuilder.withPayload(messages).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
        return null;
    }

    private byte[] buildConfigData(Integer startPos) {
        ConfigFile file = configFileDao.query().is("deviceName", "default").result();
        if (file == null) {
            file = init();
        }
        long length = 0;
        if (ConfigFile.EXTEND) {
            length = file.getFileLengthWithOutExtend();
        } else {
            length = file.getFileLength();
        }
        byte[] fileFileByte = file.getFileBytes();
        long needToSendLength = length - startPos;
        byte[] needToSendBytes = new byte[(int) needToSendLength];
        System.arraycopy(fileFileByte, startPos, needToSendBytes, 0, (int) needToSendLength);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(file.getChecksum());
        buffer.writeInt((int) length);
        buffer.writeInt(startPos);
        buffer.writeInt((int) needToSendLength);
        buffer.writeBytes(needToSendBytes);
        byte[] sendData = new byte[buffer.readableBytes()];
        buffer.readBytes(sendData);
        return sendData;
    }
}
