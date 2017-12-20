package org.springboot.mq;


import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by zl on 2017/10/18.
 */
public class KafkaProducerThread extends Thread {

	private Producer<String, String> producer;
	private Properties props;
    private final String topic = "flume-topic";

    public KafkaProducerThread(String topic){
        props = new Properties();
        // 此处配置的是kafka的broker地址:端口列表
        props.put("metadata.broker.list", "192.168.31.221:9092,192.168.31.222:9092,192.168.31.223:9092");
        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks","-1");
        producer = new KafkaProducer<String, String>(props);
    }

    public void run() {
        int messageNum = 1;
        final int COUNT = 101;

        int messageCount = 0;
        while (messageNum < COUNT) {
            String key = String.valueOf(messageNum);
            String data = "Hello kafka message :" + key;
            producer.send(new ProducerRecord<String, String>(topic, data));
            System.out.println(data);
            messageNum ++;
            messageCount++;
        }
        System.out.println("Producer端一共产生了" + messageCount + "条消息！");
    }

}
