//package com.zh.device.service;
//
//import com.zh.device.common.Const;
//import com.zh.device.message.JMessage;
//import com.zh.device.util.ByteUtil;
//import com.zh.device.util.MsgBuilder;
//import com.zh.device.util.PlusArrayUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.integration.mqtt.support.MqttHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHandler;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service(value = "restartService")
//public class RestartService {
//
//    @Autowired
//    private MessageHandler mqttOutbound;
//
//    private volatile int i;
//    private volatile int j;
//
//    public void handle(Message<JMessage> message) {
//        System.out.println("收到平台发送重启指令");
//        JMessage jMessage = message.getPayload();
//        String deviceName = jMessage.getDeviceName();
//        String sendTopic = Const.getSendTopic(deviceName);
//        MsgBuilder msgBuilder=new MsgBuilder();
//        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION).setAid((byte) 0x45)
//                .setMid((byte) 0x81).setServiceData(new byte[]{(byte) 0x01}).build();
//        Message<byte[]> message1 = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        mqttOutbound.handleMessage(message1);
//    }
//
////    @Scheduled(cron = "0/5 * * * * ?")
//    public void handleSyTime(){
//        String deviceName = "453ef8a8ed914a57b9da031756951991";
//        String sendTopic = Const.getSendTopic(deviceName);
//        long currentTime = System.currentTimeMillis() / 1000;
//        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
//        byte[] millis = ByteUtil.fromInt(10);
//        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
//        MsgBuilder msgBuilder = new MsgBuilder();
//        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
//                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
//        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        for(int i=0;i<1000;i++){
////            mqttOutbound.handleMessage(message);
////        }
//    }
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void handleSyTime1(){
//        String deviceName = "f30c8e5a68594e77a87b46be4a7bb5";
//        String sendTopic = Const.getSendTopic(deviceName);
//        long currentTime = System.currentTimeMillis() / 1000;
//        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
//        byte[] millis = ByteUtil.fromInt(10);
//        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
//        MsgBuilder msgBuilder = new MsgBuilder();
//        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
//                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
//        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        while (true){
//            mqttOutbound.handleMessage(message);
//            i++;
//            try {
//                Thread.sleep(1);
//                if( i%1000==0){
//                    break;
//                }
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//        System.out.println("i+j="+(i+j));
//       // mqttOutbound.handleMessage(message);
//    }
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void handleSyTim2(){
//        String deviceName = "f30c8e5a68594e77a87b46be4a7b11";
//        String sendTopic = Const.getSendTopic(deviceName);
//        long currentTime = System.currentTimeMillis() / 1000;
//        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
//        byte[] millis = ByteUtil.fromInt(10);
//        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
//        MsgBuilder msgBuilder = new MsgBuilder();
//        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
//                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
//        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
//        while (true){
//            j++;
//            mqttOutbound.handleMessage(message);
//            try {
//                Thread.sleep(1);
//                if(j%1000 == 0){
//                    break;
//                }
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
////        System.out.println(j);
//        //mqttOutbound.handleMessage(message);
//    }
////    @Scheduled(cron = "0/5 * * * * ?")
////    public void handleSyTim3(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a72";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/5 * * * * ?")
////    public void handleSyTim1(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7111";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/6 * * * * ?")
////    public void handleSyTime2(){
////        String deviceName = "6f563bba93f8494481e2a1460675234d";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////
////    @Scheduled(cron = "0/11 * * * * ?")
////    public void handleSyTime3(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d1";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////
////    @Scheduled(cron = "0/7 * * * * ?")
////    public void handleSyTime4(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d2";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////
////    @Scheduled(cron = "0/8 * * * * ?")
////    public void handleSyTime5(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d3";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////
////    @Scheduled(cron = "0/9 * * * * ?")
////    public void handleSyTime6(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d4";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/10 * * * * ?")
////    public void handleSyTime7(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d7";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/11 * * * * ?")
////    public void handleSyTime8(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d8";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/11 * * * * ?")
////    public void handleSyTime9(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d79";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/12 * * * * ?")
////    public void handleSyTime10(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb010";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/13 * * * * ?")
////    public void handleSyTime13(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb013";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/13 * * * * ?")
////    public void handleSyTime14(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb014";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/13 * * * * ?")
////    public void handleSyTime15(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d15";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
////    @Scheduled(cron = "0/16 * * * * ?")
////    public void handleSyTime17(){
////        String deviceName = "f30c8e5a68594e77a87b46be4a7bb0d17";
////        String sendTopic = Const.getSendTopic(deviceName);
////        long currentTime = System.currentTimeMillis() / 1000;
////        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
////        byte[] millis = ByteUtil.fromInt(10);
////        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
////        MsgBuilder msgBuilder = new MsgBuilder();
////        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
////                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
////        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
////        mqttOutbound.handleMessage(message);
////    }
//}
