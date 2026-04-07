package com.api.services;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import com.api.config.ConfigReader;
import com.api.pojo.UserPOJO;

import io.restassured.response.Response;

public class UserCRUDService {
	
	    String baseURI = ConfigReader.getProperty("baseURI");
		String token = ConfigReader.getProperty("token");
		
		
		public Response createUser(UserPOJO user) {
			
			return given()
			.baseUri(baseURI)
			.header("Authorization",token)
			.header("Content-Type","application/json")
			.body(user)
			.when()
			.post("/users");
			
		}
		
       public Response getUserById(int id) {
			
			return given()
			.baseUri(baseURI)
			.header("Authorization",token)
			.header("Content-Type","application/json")
			.when()
			.get("/users/"+id);
			
		}
		
		
       public Response updateUser(int id,UserPOJO user) {
			
			return given()
			.baseUri(baseURI)
			.header("Authorization",token)
			.header("Content-Type","application/json")
			.body(user)
			.when()
			.put("/users/"+id);
			
		}
       
       public Response deleteUser(int id) {
			
			return given()
			.baseUri(baseURI)
			.header("Authorization",token)
			.header("Content-Type","application/json")
			.when()
			.delete("/users/"+id);
			
		}
	
	

}
