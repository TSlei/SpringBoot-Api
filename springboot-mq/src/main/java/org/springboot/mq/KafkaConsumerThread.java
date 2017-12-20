package org.springboot.mq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;


/**
 * Created by zl on 2017/10/18.
 */
public class KafkaConsumerThread extends Thread {

    private final ConsumerConnector consumer;
    private final String topic;
    
    String data = "2017-12-14 16:51:53 INFO  AndroidHttpCombPathListMR:280 - Running execid:8273 flow:AndroidHttpCombPathListMR project:98 version:2";

    public KafkaConsumerThread(String topic) {
        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig());
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "192.168.31.223:2181,192.168.31.224:2181,192.168.31.225:2181");
        props.put("group.id", "group1");
        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");  
        //序列化类  
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        return new ConsumerConfig(props);
    }

    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()){
        	String message = new String(it.next().message());
        	HashMap<String, String> taskMap = getProjectParams(message);
        	System.out.println(taskMap);
        }
    }
    
    
    public static HashMap<String, String> getProjectParams(String message ){
    	String[] messages = message.split(" ");
    	HashMap<String, String> map = new HashMap<>();
    	for(int i=0; i < messages.length; i++){
    		if(i == 0){
    			map.put("date", messages[0]);
    			continue;
    		}
    		if(i == 1){
    			map.put("time", messages[1]);
    			continue;
    		}
    		if(i == 4){
    			continue;
    		}
    		if(messages[i].contains(":")){
    			String[] kv = messages[i].split(":");
    			map.put(kv[0], kv[1]);
    		}
    	}
    	for(String key : map.keySet()){
    		System.out.println(key + "_" + map.get(key));
    	}
    	return map;
    }
    
    
    public static void main(String[] args) {
//    	KafkaConsumerThread consumerThread = new KafkaConsumerThread(KafkaProperties.topic);
//      consumerThread.start();
 	}
}
