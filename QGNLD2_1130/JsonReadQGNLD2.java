package QGNLD2;

import java.io.*;

import org.json.simple.*;

public class JsonReadQGNLD2 {
	
	public static void main(String[] args) {
		
		JSONParser parser = new parser();

		FileReader parse = new FileReader("JSONQGNLD2.json");
		
		 Object obj = parser.parse(parse);
		 
         System.out.println(obj);
		
	}
  
}
