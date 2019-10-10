package com.zh.device.controller;

import com.zh.device.common.Const;
import com.zh.device.service.ConfigFileService;
import com.zh.device.util.ByteUtil;
import com.zh.device.util.MsgBuilder;
import com.zh.device.util.PlusArrayUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Controller
public class RestartController {

    @Autowired
    private MessageHandler mqttOutbound;
    @Autowired
    private ConfigFileService configFileService;

    @RequestMapping("/sendRDB1")
    @ResponseBody
    public void doSendBmu1() {
        String deviceName = "6f563bba93f8494481e2a1460675234d";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x81).setAid((byte) 0x42).setServiceData(new byte[]{(byte) 0x00}).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    @RequestMapping("/sendRDB2")
    @ResponseBody
    public void doSendBmu2() {
        String deviceName = "6f563bba93f8494481e2a1460675234d";
//        String deviceName = "3015790210c94626950618ca4b69715a";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x02).setAid((byte) 0x42).setServiceData(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    @RequestMapping("/sendRDB3")
    @ResponseBody
    public void doSendBmu3() {
        String deviceName = "6f563bba93f8494481e2a1460675234d";
//        String deviceName = "3015790210c94626950618ca4b69715a";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x03).setAid((byte) 0x42).setServiceData(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    @RequestMapping(value = "upgradeSuccess")
    @ResponseBody
    public void upgradeSuccess() {
        String deviceName = "6f563bba93f8494481e2a1460675234d";
//        String deviceName = "3015790210c94626950618ca4b69715a";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION)
                .setMid((byte) 0x04).setAid((byte) 0x42).setServiceData(new byte[3]).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
    }

    @RequestMapping("/restart")
    @ResponseBody
    public String doRestart() {
        String deviceName = "453ef8a8ed914a57b9da031756951991";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setBid(Const.DEFAULT_BID).setVersion(Const.DEFAULT_VERSION).setAid((byte) 0x45)
                .setMid((byte) 0x02).setServiceData(new byte[]{(byte) 0x00}).build();
        Message<byte[]> message1 = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message1);
        return "321";
    }

    @RequestMapping("sendBmu")
    public void doSendBmu() {
        for(int i=0;i<100000;i++){
            String deviceName = "6f563bba93f8494481e2a1460675234d";
            MsgBuilder msgBuilder = new MsgBuilder();
            byte[] sendData1 = getSendData();
//        byte[] combine = PlusArrayUtils.combine(packNum, sendData1);
            byte[] build = msgBuilder.setVersion(Const.DEFAULT_VERSION).setBid(Const.DEFAULT_BID)
                    .setAid((byte) 0x81).setMid((byte) 0x10).setServiceData(sendData1).build();
            Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, Const.getSendTopic(deviceName)).build();
            mqttOutbound.handleMessage(message);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println("321");
            }

        }
    }

    public byte[] getSendData() {
        Set<String> commonids = getCommonids("commonids.txt");
        Iterator<String> iterator = commonids.iterator();
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(25);
        long l1 = System.currentTimeMillis();
        int count = 0;

        while (iterator.hasNext()){
            if(count == 6){
                break;
            }
            count++;
            prepare(buf,iterator.next(),l1,count);
        }
        long l = l1 + 2000;
        if (commonids.size() == 19) {
            for (String commonid : commonids) {
                count++;
                prepare(buf, commonid, l, count);
            }
            byte[] sendData = new byte[buf.readableBytes()];
            buf.readBytes(sendData);
            return sendData;
        } else {
            return null;
        }
    }

    private void prepare(ByteBuf buf, String conmmonid, long l, int count) {
        buf.writeByte((byte) 0x01);
        buf.writeByte((byte) 0x00);
        long reqTime = l / 1000;
        buf.writeBytes(ByteUtil.fromInt((int) reqTime));
        short time = 10;
        buf.writeBytes(ByteUtil.fromShort(time));
        buf.writeBytes(ByteUtil.hexStringToByte(conmmonid));
        String str = "000000000000000" + count;
        byte[] bytes = ByteUtil.hexStringToByte(str);
        buf.writeBytes(bytes);
    }

    @RequestMapping("test")
    @ResponseBody
    public String doTest() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            try {
                String deviceName = "453ef8a8ed914a57b9da031756951991";
                MsgBuilder msgBuilder = new MsgBuilder();
                byte[] packNum = new byte[]{0x03};
                byte[] sendData1 = getSendData();
                byte[] sendData2 = getSendData();
                byte[] sendData3 = getSendData();
                byte[] combine = PlusArrayUtils.combine(packNum, sendData1, sendData2, sendData3);
                byte[] build = msgBuilder.setVersion(Const.DEFAULT_VERSION).setBid(Const.DEFAULT_BID)
                        .setAid((byte) 0x81).setMid((byte) 0x01).setServiceData(combine).build();
                Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, Const.getSendTopic(deviceName)).build();
                mqttOutbound.handleMessage(message);
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/syTime")
    public String dosyTime() {
        String deviceName = "453ef8a8ed914a57b9da031756951991";
        String sendTopic = Const.getSendTopic(deviceName);
        long currentTime = System.currentTimeMillis() / 1000;
        byte[] currentTimeByte = ByteUtil.fromInt((int) currentTime);
        byte[] millis = ByteUtil.fromInt(10);
        byte[] sendDatas = PlusArrayUtils.combine(millis, currentTimeByte);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setAid((byte) 0x44).setMid((byte) 0x01).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setServiceData(sendDatas).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        long l = System.currentTimeMillis();
        int i=0;
        while (true){
            i++;
            mqttOutbound.handleMessage(message);
            try {
                Thread.sleep(1);
                if(i == 1000){
                    break;
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        long l1 = System.currentTimeMillis();
        return "皮皮虾"+(l1-l);
    }

    @RequestMapping("/postition")
    @ResponseBody
    public String postition() {
        String deviceName = "453ef8a8ed914a57b9da031756951991";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] build = msgBuilder.setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setAid((byte) 0x81).setMid((byte) 0x05)
                .setServiceData(getSendService()).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
        return null;
    }

    public byte[] getSendService() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(0);
        byte[] float2byte = ByteUtil.float2byte(119.442001F);
        buffer.writeBytes(float2byte);
        byte[] aByte = ByteUtil.float2byte(26.002296F);
        buffer.writeBytes(aByte);
        byte[] fromInt = ByteUtil.fromInt((int) System.currentTimeMillis() / 1000);
        buffer.writeBytes(fromInt);
        byte[] sendService = new byte[buffer.readableBytes()];
        buffer.readBytes(sendService);
        return sendService;
    }

    @RequestMapping("doBmuOld")
    @ResponseBody
    public String doSendBmuOld() {
        String deviceName = "453ef8a8ed914a57b9da031756951991";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] needToSendDate = getServiceDate();
        byte[] build = msgBuilder.setAid((byte) 0x82).setMid((byte) 0x01).setBid(Const.DEFAULT_BID)
                .setVersion(Const.DEFAULT_VERSION)
                .setServiceData(needToSendDate).build();
        Message<byte[]> message = MessageBuilder.withPayload(build).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
        return null;
    }

    @RequestMapping(value = "RDBstatus")
    @ResponseBody
    public String doSendRdbStatus() {
        String deviceName = "453ef8a8ed914a57b9da031756951991";
        String sendTopic = Const.getSendTopic(deviceName);
        MsgBuilder msgBuilder = new MsgBuilder();
        byte[] sendDate = getRdbServiceDate();
        byte[] sendMessage = msgBuilder.setAid((byte) 0x81).setMid((byte) 0x03).setVersion(Const.DEFAULT_VERSION)
                .setBid(Const.DEFAULT_BID).setServiceData(sendDate).build();
        Message<byte[]> message = MessageBuilder.withPayload(sendMessage).setHeader(MqttHeaders.TOPIC, sendTopic).build();
        mqttOutbound.handleMessage(message);
        return null;
    }

    @RequestMapping(value = "doSendConfigFile")
    @ResponseBody
    public String doSendConfigFile(@RequestParam(value = "startPos") Integer startPos) {
        if (startPos == null) {
            return "出错了";
        }
        return configFileService.doSendConfig(startPos);
    }

    public byte[] getServiceDate() {
        ByteBuf buf = Unpooled.buffer();
        long utcTime = System.currentTimeMillis() / 1000;
        byte[] utcByte = ByteUtil.fromInt((int) utcTime);
        buf.writeBytes(utcByte);
        buf.writeByte(1);
        buf.writeByte(0);
        buf.writeByte(3);
        buf.writeBytes(new byte[4]);
        buf.writeByte(1);
        buf.writeByte(0);
        byte[] sendDate = new byte[buf.readableBytes()];
        buf.readBytes(sendDate);
        return sendDate;
    }

    public byte[] getRdbServiceDate() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(new byte[3]);
        buf.writeByte((byte) 22);
        buf.writeByte(0);
        buf.writeInt(2222);
        buf.writeInt(1111);
        byte[] checSum = configFileService.getDefaultChecSum();
        buf.writeBytes(checSum);
        byte[] sendMes = new byte[buf.readableBytes()];
        buf.readBytes(sendMes);
        return sendMes;
    }

    public static Set<String> getCommonids(String path) {
        Set<String> commonIds = new HashSet<>();
        try {
            InputStream stream =
                    RestartController.class.getClassLoader().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                commonIds.add(line.substring(2));
            }
            return Collections.unmodifiableSet(commonIds);
        } catch (IOException e) {
            return commonIds;
        }
    }


}
