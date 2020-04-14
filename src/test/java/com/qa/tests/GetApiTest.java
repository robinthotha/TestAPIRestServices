package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Util.TestUtil;
import com.qa.base.TestBase;
import come.qa.client.RestClient;

public class GetApiTest extends TestBase{
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String URL;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	JSONObject jsonResponse;
	
	@BeforeMethod	
	public void  setup() throws ClientProtocolException, IOException{
		
		testBase = new TestBase();
		serviceUrl=prop.getProperty("serviceURL");
		apiUrl = prop.getProperty("apiURL");
		URL = serviceUrl+apiUrl;
		restClient = new RestClient();
		closeableHttpResponse=restClient.get(URL);
		
		String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		jsonResponse = new JSONObject(responseString);
	}
	
	@Test(priority=1)
	public void verifyResponseStatusCode() throws ClientProtocolException, IOException{
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		String num=Integer.toString(statusCode);
		Assert.assertEquals(num, TestBase.RESPONSE_STATUS_CODE,"Status code does not match");
		
	}
	
	@Test(priority=2)
	public void verifyResponsePerPageElementTest() throws ClientProtocolException, IOException{
		String perPage_Value= TestUtil.getValueByJPath(jsonResponse, "/per_page");
		int num = Integer.parseInt(perPage_Value);
		Assert.assertEquals(num, TestBase.PER_PAGE);
	}
	
	@Test(priority=4)
	public void verifyResponseTotalElementTest() throws ClientProtocolException, IOException{
		String total= TestUtil.getValueByJPath(jsonResponse, "/total");
		int num = Integer.parseInt(total);
		Assert.assertEquals(num, TestBase.TOTAL);
	}
	
	@Test(priority=3)
	public void verifyHeaderServerValue() throws ClientProtocolException, IOException{
		
		Header[] headerArray=closeableHttpResponse.getAllHeaders();
		HashMap<String,String> hashMap = new HashMap<String,String>();
		for(Header header : headerArray){
		
			hashMap.put(header.getName(),header.getValue());
		}
		String actualValue=hashMap.get("Server").toString();
		Assert.assertEquals(actualValue, TestBase.HEADER_VALUE);
	}	
	@Test(priority=5)
	public void verifyResponseArrayDataElementTest() throws ClientProtocolException, IOException{
		String last_name= TestUtil.getValueByJPath(jsonResponse, "/data[0]/last_name");
		Assert.assertEquals(last_name,TestBase.LAST_NAME) ;
	}
	
	@Test(priority=6)
	public void verifyResponseWithHeader() throws ClientProtocolException, IOException{
		
		HashMap<String,String> hashMapObj = new HashMap<String,String>();
		hashMapObj.put("Content-type", "application/json");
		closeableHttpResponse=restClient.get(URL, hashMapObj);
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		String num=Integer.toString(statusCode);
		Assert.assertEquals(num, TestBase.RESPONSE_STATUS_CODE,"Status code does not match");
		
	}
}
