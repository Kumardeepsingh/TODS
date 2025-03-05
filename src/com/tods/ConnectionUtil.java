package com.tods;

import java.sql.*;
import javax.swing.*;

public class ConnectionUtil {
	
	private static Connection conn = null;
			
	public static void connectdb()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TODS","root","12345");
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	

	public static Connection getConn() {
		return conn;
	}

	
//	
}