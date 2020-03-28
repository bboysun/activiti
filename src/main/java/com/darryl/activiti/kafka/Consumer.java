package com.darryl.activiti.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * @Auther: Darryl
 * @Description: kafak消费者
 * @Date: created in 2020/3/14 22:18
 */

public class Consumer implements Runnable{

    private KafkaConsumer<Integer, String> consumer;
    private String topic;

    public Consumer(String topic) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "demo-consumer");
        // 设置offset自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 自动提交间隔时间
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // session超时时间
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 对于当前group id来说，从最早的offset开始消费
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer<Integer, String>(properties);

        this.topic = topic;
    }

    @Override
    public void run() {
        // 消费方式1：consumer订阅一个topic
        consumer.subscribe(Collections.singleton(topic));

        // 消费方式2：还可以通过指定分区进行消费,指定该topic的0分区进行消费
        //TopicPartition topicPartition = new TopicPartition(topic, 0);
        //consumer.assign(Arrays.asList(topicPartition));

        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
            records.forEach(record -> {
                System.out.println("消费到的消息：key:" + record.key() + " | value:" + record.value() +
                        " | offset:" + record.offset() + " | topic:" + record.topic() +
                        " | partition:" + record.partition());
            });

        }
    }

    public static void main(String[] args) {
        new Thread(new Consumer("DEMO")).start();
    }
}
