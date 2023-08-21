package Testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

// @Test
public void helper1() {

	Response res=get("https://reqres.in/api/users/23");  // get method parameter is url
	System.out.println(res.getStatusCode());
	System.out.println(res.getTime());
	System.out.println(res.getBody().asPrettyString());
	System.out.println(res.getStatusLine());
	System.out.println(res.getHeader("content-type"));
	System.out.println(res.prettyPrint());
	System.out.println("Cookies :"+res.cookies());
}
	

	// @Test
	public void helper2() {
		
		baseURI = "https://reqres.in/api";  // base url
		given().
			get("users?page=2").   // extension if i get then() execute remaining
		then().
			statusCode(200);
		
		System.out.println("Helper 2 function success");
	}
	
	// @Test
	public void helper3() {
		System.out.println("\t\t Helper 3");
		baseURI = "https://reqres.in/api";  // base url
		given().
			get("users?page=2").   // extension if i get then() execute remaining
		then().
			statusCode(200).
		//here am accessing json content from the program using api
		body("data[1].id",equalTo(8));
	}
	
	
	
//	@Test
	public void helper4() {  //Get  methods using API means i am trying to access data from the server 
		System.out.println("\t\tHelper 4");
		baseURI="https://reqres.in/api";
		given().
			get("users?page=2").
		then().
			body("data.first_name",hasItems("Rachel"));  //validate a json to get a json object from server
		System.out.println("Sucess");
		
	}
	

	@Test
	public void helper5() {  //POST  methods using API means i am trying to post data to the server 
		System.out.println("\t\tHelper 5 POST MEthod");
//		Map<String,Object> map=new HashMap<>();
//		
//		map.put("name","Vinay");
//		map.put("age", 18);
//		System.out.println(" No Formating");
//		//this way of displaying maps will have no double quotes to our string ""
//		System.out.println(map);
//		
//		
//		//so in order to have quotes and proper format am using JSONObject class
//		//first we have to have dependency of json simple in pom.xml
//		System.out.println(" With Formating");
//		JSONObject json=new JSONObject(map);  // formating using map
//		System.out.println(json);
		
		//not using map to display
		System.out.println("not using map to display just jsonobject");
		JSONObject j1=new JSONObject();  // formating by not  using map
		j1.put("name", "Vinay");
		j1.put("age", 18);
		System.out.println(j1.toJSONString());
		
		// url link
		baseURI="https://reqres.in/api";
		
		
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(j1.toJSONString()).  //posting a json req using a body(param1)
		when().
			post("/users").
		then().
			statusCode(201).  //201 code is for successful creation.
		log().all();
		
	}
	
//	@Test
	public void helper6() {
		System.out.println("Put Method");
		
		JSONObject j1=new JSONObject();  // formating by not  using map
		j1.put("name", "Vinay");
		j1.put("age", 18);
		System.out.println(j1.toJSONString());
		
		// url link
		baseURI="https://reqres.in/api";
		
		given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(j1.toJSONString()).  //posting a json req using a body(param1)
	when().
		put("/users/2").  //put method like updating what is present on server.
	then().
		statusCode(200).  //201 code is for successful updation 200 OK.
	log().all();
		
		
	}
	
//	@Test
	public void helper7() {
		System.out.println("Patch Method");
		
		JSONObject j1=new JSONObject();  // formating by not  using map
		j1.put("name", "Vinay");
		j1.put("age", 18);
		System.out.println(j1.toJSONString());
		
		// url link
		baseURI="https://reqres.in/api";
		
		given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(j1.toJSONString()).  //posting a json req using a body(param1)
	when().
		patch("/users/2").   //partial updation 
	then().
		statusCode(200).  //201 code is for successful updation 200 OK.
	log().all();
		
		
	}
	
	//@Test
	public void helper8() {
		System.out.println("Delete Method");  //delete response is 204 on success
		
		// url link
		baseURI="https://reqres.in/api";
		
	when().
		delete("/users/2").
	then().
		statusCode(204).  //204 code is for successful Deletion 200 OK.
	log().all();
		
		
	}
	

}
