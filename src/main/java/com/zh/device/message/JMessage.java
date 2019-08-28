package com.zh.device.message;


import com.zh.device.message.type.MessageType;

import java.util.Arrays;
import java.util.Map;

public class JMessage {

    public static final String TOPIC_SPLIT = "/";

    private String deviceName;

    private byte aid;

    private byte mid;

    private String clientId;

    private byte[] payload;

    private Map<String,Object> heads;

    private String topic;

    private byte[] bid;

    private byte version;

    private int serviceDataLength;

    private byte[] serviceData;

    private MessageType messageType;

    public void setAid(byte aid) {
        this.aid = aid;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setBid(byte[] bid) {
        this.bid = bid;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public void setServiceDataLength(int serviceDataLength) {
        this.serviceDataLength = serviceDataLength;
    }

    public void setServiceData(byte[] serviceData) {
        this.serviceData = serviceData;
    }

    public byte[] getBid() {
        return bid;
    }

    public byte getVersion() {
        return version;
    }

    public int getServiceDataLength() {
        return serviceDataLength;
    }

    public byte[] getServiceData() {
        return serviceData;
    }

    public String getClientId() {
        return clientId;
    }

    public void setMid(byte mid) {
        this.mid = mid;
    }

    public byte getAid() {
        return aid;
    }

    public byte getMid() {
        return mid;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public void setHeads(Map<String, Object> heads) {
        this.heads = heads;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public byte[] getPayload() {
        return payload;
    }

    public Map<String, Object> getHeads() {
        return heads;
    }

    public String getTopic() {
        return topic;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public String toString() {
        return "JMessage{" +
                "deviceName='" + deviceName + '\'' +
                ", aid=" + aid +
                ", mid=" + mid +
                ", payload=" + Arrays.toString(payload) +
                ", topic='" + topic + '\'' +
                ", version=" + version +
                ", serviceDataLength=" + serviceDataLength +
                ", serviceData=" + Arrays.toString(serviceData) +
                '}';
    }
}
