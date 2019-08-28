package com.zh.device.messageMiddleware.kafka.ConsumerOne;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerThread extends Thread{

    KafkaConsumer<String, String> consumer ;
    public KafkaConsumerThread(Properties props, String topic) {
        this.consumer = new KafkaConsumer<String, String>(props);
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    //处理消息模块
                    System.out.println(record.value());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            consumer.close();
        }

    }
}
