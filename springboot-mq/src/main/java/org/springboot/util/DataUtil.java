package org.springboot.util;

import java.util.HashMap;

public class DataUtil {

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
    	return map;
    }
}
