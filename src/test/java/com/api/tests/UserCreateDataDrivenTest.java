package com.api.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.base.BaseTest;
import com.api.pojo.UserPOJO;
import com.api.services.UserCRUDService;
import com.api.utils.ExcelUtils;
import com.api.utils.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class UserCreateDataDrivenTest extends BaseTest {
	
	UserCRUDService userService = new UserCRUDService();
	int createdUserId;
	Random random = new Random();
	String user_email;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test(dataProvider="userData",dataProviderClass= ExcelUtils.class)
	public void testCreateUserDataDriven(String name,String email,String gender,String status) throws JsonProcessingException {
		
		user_email = email;
		Log.info("Starting test case testCreateUser ");
		UserPOJO user_payload = new UserPOJO(name, user_email.replace("@", random.nextInt(1000)+"@"), gender, status);
		String request_json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user_payload);
		test.info("Request payload for create user" + request_json);
		Response createRes = userService.createUser(user_payload);
		Log.info("Response of create user" + createRes.asPrettyString());
		test.info("Response of create user" + createRes.asPrettyString());
		Assert.assertEquals(createRes.statusCode(), 201);
		Assert.assertEquals(createRes.jsonPath().get("name"), name);
		createdUserId = createRes.jsonPath().getInt("id");
		System.out.println("created user id" + createdUserId);
		test.info("created user id" + createdUserId);
		user_email = createRes.jsonPath().get("email");
		Log.info("Endning test case testCreateUser ");

	}
	
	

}
