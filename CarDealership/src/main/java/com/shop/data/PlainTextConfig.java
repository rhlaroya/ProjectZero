package com.shop.data;

public class PlainTextConfig {
	private static final String url = "jdbc:postgresql://regaedb.ce8a70kibcmu.us-east-2.rds.amazonaws.com:5432/zero?currentSchema=shop";
	private static final String usernmae = "admin";
	private static final String password = "password";
	
	public static String getUrl() {
		return url;
	}
	public static String getUsernmae() {
		return usernmae;
	}
	public static String getPassword() {
		return password;
	}
}

