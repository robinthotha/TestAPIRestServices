package come.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	
	
	//1. GET Method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); //Http get request
		CloseableHttpResponse httpResponse=closeableHttpClient.execute(httpGet); // Hit the Get URL
		
		return httpResponse;
		
		
	/*	//GET status code
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code = " + statusCode);
		String reponseString =EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject jsonObject = new JSONObject(reponseString);
		System.out.println(jsonObject);
		
		Header[] headerArr=httpResponse.getAllHeaders();
		HashMap<String, String> hashMapArr = new HashMap<String, String>();
		
		for(Header header : headerArr){
			hashMapArr.put(header.getName(),header.getValue());
			
		}
		
		System.out.println(hashMapArr);*/
		
	}
	
	public CloseableHttpResponse get(String url,HashMap<String, String> hashMapObj) throws ClientProtocolException, IOException{
		
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();		
		HttpGet httpGet = new HttpGet(url); //Http get request
		
		for(Map.Entry<String,String> map : hashMapObj.entrySet()){			
			httpGet.addHeader(map.getKey(), map.getValue());;
		}
			
		CloseableHttpResponse httpResponse=closeableHttpClient.execute(httpGet); // Hit the Get URL
		
		return httpResponse;
	}
	
	public CloseableHttpResponse post(String url,String stringEntity,HashMap<String, String> hashMapObj) throws ClientProtocolException, IOException{
		
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();	
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(stringEntity));
		
		for(Map.Entry<String,String> map : hashMapObj.entrySet()){			
			httpPost.addHeader(map.getKey(), map.getValue());;
		}

		
		CloseableHttpResponse httpResponsePost=closeableHttpClient.execute(httpPost);
		return httpResponsePost;
		
	}
}
