package org.springboot;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.azkaban.AzkabanApi;
import org.springboot.model.TaskInfo;
import org.springboot.service.TaskInfoService;
import org.springboot.util.DataUtil;
import org.springboot.util.LoadClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"org.springboot"})
@RestController
@EnableAutoConfiguration
public class KafkaController {

    public static Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private TaskInfoService taskInfoService;
    
    @Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/send")
    @ResponseBody
    String send(String topic, String key, String data) {
        template.send(topic, key, data);
        return "success";
    }
    
	@RequestMapping(value = "/addInfo")
	public int insertTaskInfo(@RequestBody TaskInfo taskInfo) {
		
		int status = taskInfoService.save(taskInfo);
		return status;
	}
	
	@RequestMapping(value = "/add")
	public int insert() {
		TaskInfo taskInfo = new TaskInfo("1", "2017-07-04 15:48:58", "server", "1", "serverMR", "1", 1);
		int status = taskInfoService.save(taskInfo);
		return status;
	}

    public static void main(String[] args) throws Exception {
    	LoadClassUtil.loadProperties();
        SpringApplication.run(KafkaController.class, args);
    }

//    @KafkaListener(id = "group1", topics = "flume-topic2")
//    public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
//    	logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
//    }

    @KafkaListener(id = "azkaban3", topics = "flume-topic")
    public void listenT2(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
    	HashMap<String, String> taskMap = DataUtil.getProjectParams(cr.value().toString());
    	String url = LoadClassUtil.properties.getProperty("url").toString();
        Map<?, ?> map = AzkabanApi.getSessionMap(url);
        if(map.containsKey("session.id")){
        	String sessionId = map.get("session.id").toString();
        	String projectId = taskMap.get("project");
        	String projectName = LoadClassUtil.properties.getProperty(projectId).toString();
        	String flowName = taskMap.get("flow");
        	String failureTime = taskMap.get("date") + " " + taskMap.get("time");
        	String flowVersion = taskMap.get("version");
        	String executeId = taskMap.get("execid");
        	Map<?, ?> resultMap = AzkabanApi.submitFlow(sessionId, projectName, flowName, url);
        	if(resultMap.containsKey("execid")){
        		TaskInfo taskInfo = new TaskInfo(projectId, failureTime, projectName, flowVersion, flowName, executeId, 1);
        		taskInfoService.save(taskInfo);
        	}else{
        		TaskInfo taskInfo = new TaskInfo(projectId, failureTime, projectName, flowVersion, flowName, executeId, 0);
        		taskInfoService.save(taskInfo);
        	}
        }
    }
}
