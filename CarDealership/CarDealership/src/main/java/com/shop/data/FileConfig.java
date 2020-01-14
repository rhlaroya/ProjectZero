package com.shop.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileConfig {
	
	private static String url;
	private static String username;
	private static String password;
	
	static {
		FileInputStream f;
		try {
			f = new FileInputStream("src/main/resources/connection.properties");
			Properties p = new Properties();
			p.load(f);
			username = p.getProperty("username");
			url = p.getProperty("url");
			password = p.getProperty("password");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		FileConfig.url = url;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		FileConfig.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		FileConfig.password = password;
	}
	
}

