package com.cpi.is.util;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

private static String db_url, db_schema, db_password;
	
	public static Connection getConn() 
			throws FileNotFoundException, SQLException, ClassNotFoundException {
		//String[] dbConfig = FileUtil.parseConfigFile("C:\\CLIS\\db.config");
		db_url		= "jdbc:oracle:thin:@training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com:1521:ORCL";
		db_schema 	= "TRNG";
		db_password = "trng";
		
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection(db_url, db_schema, db_password);
	}
	
	public static Statement getStmt(String query, Boolean callable) 
			throws SQLException, FileNotFoundException, ClassNotFoundException {
		Statement stmt;
		
		if (callable) {
			stmt = getConn().prepareCall(query);
		} else {
			stmt = getConn().prepareStatement(query);
		}
		return stmt;
	}
	
	public static ResultSet select(PreparedStatement stmt, String[] params) 
			throws SQLException {
		for (int i=0; i<params.length; i++) {
			stmt.setString(i+1, params[i]);
		}
		return stmt.executeQuery();
	}
	
	public static void update(PreparedStatement stmt, String[] params) 
			throws SQLException {
		for (int i=0; i<params.length; i++) {
			stmt.setString(i+1, params[i]);
		}
		stmt.executeUpdate();
	}
	
}
