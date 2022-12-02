package QGNLD2;

import java.io.*;

import org.json.simple.*;

public class JsonWriteQGNLD2 {
	
	public static void main(String[] args) {
		
		JSONObject student2 = new JSONObject();
		
    student2.put("hallgato", "hallgato neve");
    student2.put("neptunkod", "kkklll");
    student2.put("szak", "PTI");
        
    JSONArray studentList = new JSONArray();
    studentList.add(student2);
        
    System.out.println(studentList);
        
    FileWriter file = new FileWriter("JSONQGNLD2.json");
        
    file.writestudentList.toJSONString()); 
    file.flush();
	}

}
