//package com.zh.device.controller;
//
//import com.zh.device.common.Const;
//import com.zh.device.util.MsgBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.integration.mqtt.support.MqttHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHandler;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(value = "/pipi")
//public class PipixiaController {
//
//    @Autowired
//    private MessageHandler mqttOutbound;
//
//    @RequestMapping("/sendRDB1")
//    @ResponseBody
//    public void doSendBmu1() {
//        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d1";
//        String sendTopic = Const.getSendTopic(deviceName);
//        MsgBuilder msgBuilder = new MsgBuilder();
//        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
//                .setMid((byte) 0x81).setAid((byte) 0x42).setServiceData(new byte[]{(byte) 0x00}).build();
//        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        mqttOutbound.handleMessage(message);
//    }
//
//    @RequestMapping("/sendRDB2")
//    @ResponseBody
//    public void doSendBmu2() {
//        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d1";
//        String sendTopic = Const.getSendTopic(deviceName);
//        MsgBuilder msgBuilder = new MsgBuilder();
//        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
//                .setMid((byte) 0x02).setAid((byte) 0x42).setServiceData(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}).build();
//        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        mqttOutbound.handleMessage(message);
//    }
//
//    @RequestMapping("/sendRDB3")
//    @ResponseBody
//    public void doSendBmu3() {
//        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d1";
//        String sendTopic = Const.getSendTopic(deviceName);
//        MsgBuilder msgBuilder = new MsgBuilder();
//        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
//                .setMid((byte) 0x03).setAid((byte) 0x42).setServiceData(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}).build();
//        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        mqttOutbound.handleMessage(message);
//    }
//    @RequestMapping(value = "upgradeSuccess")
//    @ResponseBody
//    public void upgradeSuccess(){
//        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d1";
//        String sendTopic = Const.getSendTopic(deviceName);
//        MsgBuilder msgBuilder = new MsgBuilder();
//        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
//                .setMid((byte) 0x04).setAid((byte) 0x42).setServiceData(new byte[3]).build();
//        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        mqttOutbound.handleMessage(message);
//    }
//
//    @RequestMapping("/restart")
//    @ResponseBody
//    public void doRestart() {
//        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d1";
//        String sendTopic = Const.getSendTopic(deviceName);
//        MsgBuilder msgBuilder = new MsgBuilder();
//        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION).setAid((byte) 0x45)
//                .setMid((byte) 0x02).setServiceData(new byte[]{(byte) 0x00}).build();
//        Message<byte[]> message1 = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        mqttOutbound.handleMessage(message1);
//    }
//
//
//}
