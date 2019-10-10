package com.zh.device.messageMiddleware.kafka.ConsumerOne;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

//线程消费者处理  要对位移提交
public class FirstMultiConsumerThreadDemo {
    public static final String brokerList = "192.168.32.142:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";

    public static Properties initConfig(){
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        //通过多线程消费
        int consumerThreadNum = 4;
        for(int i=0;i<consumerThreadNum;i++) {
            new KafkaConsumerThread(props,topic).start();
        }
    }

}
