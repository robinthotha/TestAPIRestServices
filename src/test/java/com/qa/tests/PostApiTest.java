package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.data.Users;

import come.qa.client.RestClient;

public class PostApiTest extends TestBase{
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String URL;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	JSONObject jsonResponse;

	
	
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException{
		
		testBase = new TestBase();
		serviceUrl=prop.getProperty("serviceURL");
		apiUrl = prop.getProperty("apiURL");
		URL = serviceUrl+apiUrl;
		restClient = new RestClient();
		
	}
	
	
	@Test
	public void verifyPostAPITest() throws ClientProtocolException, IOException{
		
		
		HashMap<String, String> hashMapObj = new HashMap<String, String>();
		hashMapObj.put("content", "application/json");
		
		//jackson API
	
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("test","QE Lead");	
		mapper.writeValue(new File("D:\\Workspace\\testapi\\src\\main\\java\\com\\qa\\data\\Users.json"), users);
		String jsonString=mapper.writeValueAsString(users);
		
		CloseableHttpResponse response = restClient.post(URL, jsonString, hashMapObj);
		
		int status_Code=response.getStatusLine().getStatusCode();
		Assert.assertEquals(status_Code,TestBase.RESPONSE_STATUS_CODE_201);
		
		String responseString= EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject jsonResponse = new JSONObject(responseString);	
		
		System.out.println(jsonResponse);

	
		
		
	}
	
	
}
