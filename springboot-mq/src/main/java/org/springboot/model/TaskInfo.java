package org.springboot.model;

import java.io.Serializable;

public class TaskInfo implements Serializable{

	private static final long serialVersionUID = -5775565191634950626L;

	private String id;
	
	private String projectId;
	
	private String failureTime;
	
	private String projectName;
	
	private String flowVersion;
	
	private String flowName;
	
	private String executeId;
	
	private int status;
	
	public TaskInfo(String projectId, String failureTime, String projectName, String flowVersion,
			String flowName, String executeId, int status) {
		super();
		this.projectId = projectId;
		this.failureTime = failureTime;
		this.projectName = projectName;
		this.flowVersion = flowVersion;
		this.flowName = flowName;
		this.executeId = executeId;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(String failureTime) {
		this.failureTime = failureTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getFlowVersion() {
		return flowVersion;
	}

	public void setFlowVersion(String flowVersion) {
		this.flowVersion = flowVersion;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getExecuteId() {
		return executeId;
	}

	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
