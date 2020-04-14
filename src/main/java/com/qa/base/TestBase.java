package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	public static String RESPONSE_STATUS_CODE = "200";
	public static int RESPONSE_STATUS_CODE_201 = 201;
	public static int PER_PAGE = 6;
	public static int TOTAL = 12;
	public static String HEADER_VALUE = "cloudflare";
	public static String LAST_NAME="Lawson";
	
	public TestBase(){
		
		prop = new Properties();
		
		try {
			FileInputStream fs = new FileInputStream("D:\\Workspace\\testapi\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

}
