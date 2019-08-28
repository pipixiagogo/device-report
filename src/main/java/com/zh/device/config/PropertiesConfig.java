package com.zh.device.config;

import com.zh.device.util.ByteUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "mqtt")
public class PropertiesConfig {

    private Set<Long> msgIds;

    private Object msgObj = new Object();
    @Value("${mqtt.server}")
    private String mqttServer;
    @Value("${mqtt.username}")
    private String mqttUsername;
    @Value("${mqtt.password}")
    private String mqttPassword;
    @Value("${mqtt.clientid.subscriber}")
    private String mqttSub;
    @Value("${mqtt.clientid.publisher}")
    private String mqttPub;
    @Value("${mqtt.subtopic}")
    private String mqttSubtopics;


    public String getMqttServer() {
        return mqttServer;
    }

    public String getMqttUsername() {
        return mqttUsername;
    }

    public String getMqttPassword() {
        return mqttPassword;
    }

    public String getMqttSub() {
        return mqttSub;
    }

    public String getMqttPub() {
        return mqttPub;
    }

    public String getMqttSubtopics() {
        return mqttSubtopics;
    }

    public Set<Long> getMsgIds() {
        if (msgIds == null) {
            synchronized (msgObj) {
                if (msgIds == null) {
                    InputStream stream = null;
                    try {
                        stream = PropertiesConfig.class.getClassLoader().getResourceAsStream("msgids.txt");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                        Set<Long> sets = new HashSet<>();
                        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                            if (line.length() != 10) {
                                continue;
                            }
                            byte[] array = ByteUtil.parseHexStringToArray(line.substring(2));
                            sets.add(ByteUtil.toUnsignedInt(array));
                        }
                        this.msgIds = sets;
                    } catch (Exception e) {
                        System.out.println("MSGID解析失败");
                    } finally {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return msgIds;
    }
}
