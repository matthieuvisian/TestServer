package test.java;

import java.io.StringWriter;
import java.lang.*;
import java.util.*;
import org.json.*;
import org.json.JSONObject;


public class JsonConv {
	private JSONObject obj = new JSONObject();
	JsonConv() {
		obj.put("name","foo");
		obj.put("name2","fooll");
		obj.put("name3","asdafa");
		
		JSONArray ja = new JSONArray();
		ja.put(obj);

		JSONObject mainObj = new JSONObject();
		mainObj.put("DATA", ja);
		
		System.out.print(mainObj);
	}
	public JSONObject getJson() {
		return obj;
	}
	
	   public static void main(String[] args){
		   JSONObject obj = new JSONObject();
		 //     JsonConv obj = new JsonConv();
		   
		   obj.put("name","foo");
		   obj.put("num",new Integer(100));
		   obj.put("balance",new Double(1000.21));
		   obj.put("is_vip",new Boolean(true));
		   
		   StringWriter out = new StringWriter();
		   
		   String jsonText = out.toString();
		   System.out.print(jsonText);
			}
}
