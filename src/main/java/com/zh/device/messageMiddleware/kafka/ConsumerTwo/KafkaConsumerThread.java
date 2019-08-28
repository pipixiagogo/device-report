package com.zh.device.messageMiddleware.kafka.ConsumerTwo;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KafkaConsumerThread extends Thread{

    KafkaConsumer<String,String> kafkaConsumer;
    Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
    int threadNum;
    ThreadPoolExecutor executor ;
    public KafkaConsumerThread(Properties props, String topic, int threadNum) {
        kafkaConsumer=new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList(topic));
        this.threadNum=threadNum;
        this.executor = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Override
    public void run() {
        try {
            while (true){
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                if(!records.isEmpty()){
                    executor.submit(new RecordsHandler(records,offsets));
                }
                synchronized (offsets) {
                    if (!offsets.isEmpty()) {
                        kafkaConsumer.commitSync(offsets);
                        offsets.clear();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaConsumer.close();
        }
    }
}
