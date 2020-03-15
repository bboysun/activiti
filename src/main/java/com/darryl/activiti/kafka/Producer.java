package com.darryl.activiti.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Darryl
 * @Description: kafka生产者 同步方法
 * @Date: created in 2020/3/14 21:30
 */
public class Producer implements Runnable{

    private static KafkaProducer<Integer, String> producer;
    private String topic;

    public Producer(String topic) {
        Properties properties = new Properties();
        // kafka服务端的IP地址和端口号信息
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "demo-producer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "100");
        producer = new KafkaProducer<Integer, String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < 10) {
            String msg = "producer send message " + count;
            try {
                producer.send(new ProducerRecord<>(topic, msg)).get();
                TimeUnit.SECONDS.sleep(2);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer("DEMO")).start();
    }
}
