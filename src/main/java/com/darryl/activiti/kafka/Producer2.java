package com.darryl.activiti.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Darryl
 * @Description: kafka生产者 异步方法
 * @Date: created in 2020/3/14 22:47
 */

public class Producer2 implements Runnable{

    private static KafkaProducer<Integer, String> producer;
    private String topic;

    public Producer2(String topic) {
        Properties properties = new Properties();
        // kafka服务端的IP地址和端口号信息
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "demo-producer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<Integer, String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < 10) {
            String msg = "producer send message " + count;
            try {
                producer.send(new ProducerRecord<>(topic, msg), new Callback() {
                            @Override
                            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                                System.out.println("callback: topic:" + recordMetadata.topic() + " | offset:" +
                                        recordMetadata.offset() + " | partition:" + recordMetadata.partition());
                            }
                        }
                );
                TimeUnit.SECONDS.sleep(2);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer("DEMO")).start();
    }
}
