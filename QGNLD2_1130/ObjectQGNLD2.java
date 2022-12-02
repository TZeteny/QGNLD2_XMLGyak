package QGNLD2;

import java.io.*;

import org.json.simple.*;

public class ObjectQGNLD2 {
  
	public static void main(String[] args) {
    
		JSONObject j = new JSONObject(); 
    
		j.put("hallgato", "hallgato neve");
		j.put("neptunkod", "kkklll");
		j.put("szak", "PTI");
		System.out.println(j.toString());
	
	}	
 
}
