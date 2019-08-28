package com.zh.device.service;

import com.zh.device.common.Const;
import com.zh.device.message.JMessage;
import com.zh.device.util.MsgBuilder;
import com.zh.device.util.PlusArrayUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigService {

    public static final short CLEAN_SUCCESS=0;

    @Autowired
    private MessageHandler mqttOutbound;

    public void handle(Message<JMessage> message){
        JMessage jMessage = message.getPayload();
        byte mid = jMessage.getMid();
        switch (mid){
            case (byte)0x31:
                cleanFlush(jMessage);
                break;
            case (byte)0x45:
                bmuVersionMsg(jMessage);
                break;
        }
    }
    private void bmuVersionMsg(JMessage jMessage) {
        String deviceName= jMessage.getDeviceName();
        String sendTopic=Const.getSendTopic(deviceName);
        byte[] sendData=prepare();
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] bytes = msgBuilder.setAid((byte) 0xEF).setMid((byte) 0xC5).setBid(Const.DEFAULT_BID)
                .setVersion(Const.DEFAULT_VERSION).setServiceData(sendData).build();
        Message<byte[]> message = MessageBuilder.withPayload(bytes).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }


    private byte[] prepare() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(0);
        byte[] str="你是真的皮皮虾".getBytes();
        if(str.length<64){
            byte[] supper= new byte[64-str.length];
            str=PlusArrayUtils.combine(str,supper);
        }
        buffer.writeBytes(str);
        String matchInfo="S00BMUH778V32Y17";
        int matchLength = matchInfo.getBytes().length;
        buffer.writeByte(matchLength);
        buffer.writeBytes(matchInfo.getBytes());
        byte[] sendByte= new byte[buffer.readableBytes()];
        buffer.readBytes(sendByte);
        return sendByte;
    }


    private void cleanFlush(JMessage jMessage) {
        String deviceName = jMessage.getDeviceName();
        String sendTopic = Const.getSendTopic(deviceName);
        ByteBuf buf = Unpooled.wrappedBuffer(jMessage.getServiceData());
        byte readByte = buf.readByte();
        System.out.println(readByte);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(readByte).writeByte((byte)0x01).writeInt(10).writeInt(20);
        byte[] serviceData=new byte[buffer.readableBytes()];
        buffer.readBytes(serviceData);
        MsgBuilder msgBuilder=new MsgBuilder();
        byte[] build = msgBuilder.setAid((byte) 0xEF).setMid((byte) 0xB1).setServiceData(serviceData).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }


}
