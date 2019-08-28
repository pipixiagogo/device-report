package com.zh.device.message.trans;

import com.zh.device.message.JMessage;
import com.zh.device.message.OnOffMessage;
import com.zh.device.message.type.MessageType;
import com.zh.device.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.mqtt.support.MqttHeaders;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class MessageTransformer {
    private static final String DISCONNECT = "disconnected";
    private static final String CONNECT = "connected";
    private static final short MAGIC_NUM  = ByteUtil.toShort( new byte[]{(byte) 0xbb,(byte) 0xcd} );
    private static final short MIN_DATA_LEN = 11;
    private static final Logger log = LoggerFactory.getLogger(MessageTransformer.class);
    public static JMessage mqttMessage2JMessage(byte[] payload, Map<String, Object> headers){
        try{
            String topic = headers.get(MqttHeaders.RECEIVED_TOPIC).toString();
            if( topic.startsWith("$RDB") ){
                return doRdbMsg( payload, headers, topic );
            }else{
                return doOnOffMsg( payload, headers,topic );
            }
        }catch (Exception e){
            log.error("数据解析错误。",e);
            JMessage jMessage = new JMessage();
            jMessage.setMessageType(MessageType.UNKNOW);
            return jMessage;
        }
    }

    private static JMessage doOnOffMsg(byte[] payload, Map<String, Object> headers, String topic) throws UnsupportedEncodingException {
        String json = new String(payload,"utf-8");
        log.info("设备上下线消息:{}",json);
        OnOffMessage message = new OnOffMessage();
        message.setMessageType( MessageType.LIVE );
        message.setHeads( headers );
        message.setPayload( payload );
        message.setTopic(topic);
        message.setJson(json);
        if( !topic.endsWith( DISCONNECT ) ){
            message.setOnline( true );
        }else {
            message.setOnline( false );
        }
        return message;
    }

    private static JMessage doRdbMsg(byte[] payload, Map<String, Object> headers, String topic) {
        String[] topicSlices = topic.split( JMessage.TOPIC_SPLIT );
        String deviceName = topicSlices[1];
        log.info("设备上报数据：deviceName:{}, data:{},  headers:{}",deviceName,
                StringUtil.toHexString( payload ),headers);
        if( payload.length < MIN_DATA_LEN ){
            log.error("数据包长度错误，deviceName:{}",deviceName);
            throw new RuntimeException("数据包长度错误。");
        }
        ByteBuf buf = Unpooled.wrappedBuffer( payload );
        short magicNum = buf.readShort();
        if( MAGIC_NUM  != magicNum ){
            log.error("数据包格式错误，deviceName:{}",deviceName);
            throw new RuntimeException("数据包格式错误，魔数不匹配。");
        }
        JMessage message = new JMessage();
        message.setHeads(headers);
        message.setPayload( payload );
        message.setTopic(topic);
        message.setDeviceName(deviceName);
        byte[] bid = new byte[4];
        buf.readBytes( bid );
        message.setBid( bid );
        message.setVersion( buf.readByte() );
        message.setAid( buf.readByte() );
        message.setMid( buf.readByte() );
        message.setMessageType( MessageType.valueOf( message.getAid() ) );
        int serviceDataLen = buf.readUnsignedShort();
        message.setServiceDataLength( serviceDataLen );
        if( serviceDataLen + MIN_DATA_LEN != payload.length ){
            log.error("报文长度有误。");
            throw new RuntimeException();
        }
        byte[] des = new byte[serviceDataLen];
        buf.readBytes(des);
        message.setServiceData(des);
        return message;
    }
}
