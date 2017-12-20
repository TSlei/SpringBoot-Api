package org.springboot.service;

import org.springboot.dao.TaskInfoDao;
import org.springboot.model.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskInfoService {
	
	 @Autowired
	 private TaskInfoDao taskInfoMapper;

	 public int save(TaskInfo taskInfo) {
	     return taskInfoMapper.save(taskInfo);
	 }
}
