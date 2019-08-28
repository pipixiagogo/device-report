package com.zh.device.messageMiddleware.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerFastStart {
    public static final String brokerList = "192.168.32.142:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
//        broker 端接收的消息填写的序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        //生产者客户端连接 Kafka 集群所需的 broker 地址清单
//        具体的内容格式为 host1:port1,host2:port2
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        //如果配置了 retries 参数，那么只要在规定的重试次数内自行恢复了
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, "hello, Kafka!");
        try {
            //send方法重载有同步 有异步
            producer.send(record,(x,y)->{
                //异步调用
                String topic = x.topic();
                y.printStackTrace();
                System.out.println("发送成功"+topic);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
