package com.shop.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(PlainTextConfig.getUrl(),
					PlainTextConfig.getUsernmae(), PlainTextConfig.getPassword());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}