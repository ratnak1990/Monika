package com.bank.dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.bank.main.BankLauncher;

public class DatabaseConnection {
	
	private static final Logger logger = Logger.getLogger(BankLauncher.class);
	
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	private final String DB_URL = "com.mysql.jdbc.Driver";
	private final String dbUserid = "root";
	private final String dbPassword = "root";

	public Connection getConnection() {

		try {
			Class.forName(DB_URL);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_project", dbUserid, dbPassword);

		} catch (SQLException e) {
			logger.error("unknown system error,please try again later");;
			e.printStackTrace();
			System.exit(0);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return connection;

	}

	public void closeStatement() {
		try {
			connection.close();

		} catch (SQLException e) {
			logger.error("unknown system error ,please try again later ");
			e.printStackTrace();
			System.exit(0);
		} finally {
			connection = null;
		}
	}
}
