package org.springboot.azkaban;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class AzkabanApi {

	public static void main(String[] args) {
		submitFlow("ssss","","","");
	}

	public static Map<?, ?> getSessionMap(String url) {
		String[] cmds = { "curl", "-k", "-X", "POST", "--data", "action=login&username=azkaban&"
				+ "password=azkaban","https://" + url + ":8443" };
		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		StringBuffer buffer = new StringBuffer();
		Map<?, ?> jsonResult = null;
		try {
			p = pb.start();
			BufferedReader br = null;
			String line = null;
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			String result = buffer.toString();
			result = result.substring(result.indexOf("{"));
			jsonResult = JSON.parseObject(result, Map.class);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	public static Map<?, ?> submitFlow(String sessionId, String projectName, String flowName, String url){
		String[] cmds = {"curl", "-k", "--get", "--data", "session.id=" + sessionId,
				"--data", "ajax=executeFlow", "--data", "project=" + projectName, 
				"--data", "flow=" + flowName, "https://" + url + ":8443/executor"};
		
		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		StringBuffer buffer = new StringBuffer();
		Map<?, ?> jsonResult = null;
		try {
			p = pb.start();
			BufferedReader br = null;
			String line = null;
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			String result = buffer.toString();
			result = result.substring(result.indexOf("{"));
			jsonResult = JSON.parseObject(result, Map.class);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
	
}
