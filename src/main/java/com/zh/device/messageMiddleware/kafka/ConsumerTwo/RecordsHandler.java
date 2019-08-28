package com.zh.device.messageMiddleware.kafka.ConsumerTwo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.List;
import java.util.Map;

public class RecordsHandler extends Thread {
    ConsumerRecords<String, String> records;
    Map<TopicPartition, OffsetAndMetadata> offsets;

    public RecordsHandler(ConsumerRecords<String, String> records, Map<TopicPartition, OffsetAndMetadata> offsets) {
        this.records = records;
        this.offsets = offsets;
    }

    @Override
    public void run() {
        for (TopicPartition tp : records.partitions()) {
            List<ConsumerRecord<String, String>> tpRecords = records.records(tp);
            //处理tpRecords.
            long lastConsumedOffset = tpRecords.get(tpRecords.size() - 1).offset();
            synchronized (offsets) {
                if (!offsets.containsKey(tp)) {
                    offsets.put(tp, new OffsetAndMetadata(lastConsumedOffset + 1));
                } else {
                    long position = offsets.get(tp).offset();
                    if (position < lastConsumedOffset + 1) {
                        offsets.put(tp, new OffsetAndMetadata(lastConsumedOffset + 1));
                    }
                }
            }
        }
    }
}
