package com.api.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.pojo.UserPOJO;
import com.api.services.UserCRUDService;
import com.api.utils.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class UserCRUDTest extends BaseTest {

	UserCRUDService userService = new UserCRUDService();
	int createdUserId;
	Random random = new Random();
	String user_email;
	ObjectMapper mapper = new ObjectMapper();

	@Test(priority = 1)
	public void testCreateUser() throws JsonProcessingException {
		Log.info("Starting test case testCreateUser ");
		UserPOJO user_payload = new UserPOJO("Jack", "jack" + random.nextInt(1000) + "@gmail.com", "male", "Active");
		String request_json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user_payload);
		test.info("Request payload for create user" + request_json);
		Response createRes = userService.createUser(user_payload);
		Log.info("Response of create user" + createRes.asPrettyString());
		test.info("Response of create user" + createRes.asPrettyString());
		Assert.assertEquals(createRes.statusCode(), 201);
		Assert.assertEquals(createRes.jsonPath().get("name"), "Jack");
		createdUserId = createRes.jsonPath().getInt("id");
		System.out.println("created user id" + createdUserId);
		test.info("created user id" + createdUserId);
		user_email = createRes.jsonPath().get("email");
		Log.info("Endning test case testCreateUser ");

	}

	@Test(priority = 2)
	public void testGetUser() {
		Log.info("Starting test case testGetUser ");
		Response getRes = userService.getUserById(createdUserId);
		Log.info("Response of get user" + getRes.asPrettyString());
		test.info("Response of get user" + getRes.asPrettyString());
		Assert.assertEquals(getRes.statusCode(), 200);
		Assert.assertEquals(getRes.jsonPath().get("email"), user_email);
		Log.info("Endning test case testGetUser ");

	}

	@Test(priority = 3)
	public void testUpdateUser() throws JsonProcessingException {
		String updated_user_email = user_email.replace("@", "+updated@");
		UserPOJO updated_user_payload = new UserPOJO("Jack", updated_user_email, "male", "inactive");
		String request_json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updated_user_payload);
		test.info("Request payload for update user" + request_json);
		Response updateRes = userService.updateUser(createdUserId, updated_user_payload);
		System.out.println("Response of update user" + updateRes.asPrettyString());
		test.info("Response of update user" + updateRes.asPrettyString());
		Assert.assertEquals(updateRes.statusCode(), 200);
		Assert.assertEquals(updateRes.jsonPath().get("email"), updated_user_email);
		Assert.assertEquals(updateRes.jsonPath().get("status"), "inactive");
	}

	@Test(priority = 4)
	public void testDeleteUser() {

		Response delRes = userService.deleteUser(createdUserId);
		System.out.println("Response of delete user for id " + createdUserId);
		test.info("Response of delete user for id " + createdUserId);
		Assert.assertEquals(delRes.statusCode(), 204);

		// verify delete
		Response verifyRes = userService.getUserById(createdUserId);
		System.out.println("get user Response after deletion user" + verifyRes.asPrettyString());
		test.info("get user Response after deletion user" + verifyRes.asPrettyString());
		Assert.assertEquals(verifyRes.statusCode(), 404);
		Assert.assertEquals(verifyRes.jsonPath().get("message"), "Resource not found");
	}

}
