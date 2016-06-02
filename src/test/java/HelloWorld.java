package test.java;
import static spark.Spark.*;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Version;

import java.lang.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import spark.ModelAndView;
import spark.Request;
import spark.Route;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;
import j2html.*;



public class HelloWorld {
	HelloWorld() {
		System.out.println("Création d'une instance");
	}
	
	private static boolean shouldReturnHtml(Request request) {
	    String accept = request.headers("Accept");
	    return accept != null && accept.contains("text/html");
	}
	
	// The Data
	private String Data = new String();

    public static void main(String[] args) {
    	FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration(new Version(2,3,24));
    	freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(HelloWorld.class, "/"));
    	freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
    	
    	HelloWorld Hl = new HelloWorld();
    	
    	
    	// Test to receive Data from URL and the body request
    	// The JSON received is ->
    	// {"employees":[
        // {"firstName":"John", "lastName":"Doe"},
        // {"firstName":"Anna", "lastName":"Smith"},
        // {"firstName":"Peter", "lastName":"Jones"}
    	// ]}
    	
    	post("/data", (req, res) ->{
    		String jsoon = req.body();
    		Hl.Data = req.queryParams("name");
    		System.out.println("----->" + jsoon);
    		
    		
    		final JSONObject obj = new JSONObject(jsoon);
    		System.out.println("°°");
    		final JSONArray geodata = obj.getJSONArray("employees");
    		System.out.println("°°°");
    		final int n = geodata.length();
    		System.out.println("Debut boucle");
    	    for (int i = 0; i < n; ++i) {
    	        final JSONObject person = geodata.getJSONObject(i);
    	        System.out.println("Betw");
    	        System.out.println(person.getString("firstName"));
    	        System.out.println(person.getString("lastName"));
        		System.out.println("Turn");
    	    }
    		return "lol";
    		});
    	
    	
        get("/hello", (req, res) -> "Hello World");
        
        
        // Get Method for /posts root.
        // return posts.ftl page with the Hl.Data in "title"
        // Hl.Data is fill in posts method to "/data" root.
        get("/posts", (request, response) -> {
			if (shouldReturnHtml(request)) {
				System.out.println("Yes");
                response.status(200);
                response.type("text/html");
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("title", Hl.Data);
                return freeMarkerEngine.render(new ModelAndView(attributes, "posts.ftl"));
            } else {
                response.status(200);
                response.type("application/json");
                return ("lol");
              //  return dataToJson(model.getAllPosts());
            }
        });
    }
}

