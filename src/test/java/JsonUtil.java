package test.java;

import java.util.Map;

import com.google.gson.Gson;

public class JsonUtil {
    public static Map<String, String> parse(String object) {
    	System.out.println("SOme Value");
        return new Gson().fromJson(object, Map.class);
}
}