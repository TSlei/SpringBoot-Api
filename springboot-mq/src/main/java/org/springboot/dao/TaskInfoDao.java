package org.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.model.TaskInfo;

@Mapper
public interface TaskInfoDao {

	public int save(@Param("taskInfo") TaskInfo taskInfo);
	
}
