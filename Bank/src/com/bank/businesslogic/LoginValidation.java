package com.bank.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dbo.DatabaseConnection;
import com.bank.main.BankLauncher;
import com.bank.vo.AccountLogin;

public class LoginValidation {

	private static final Logger logger = Logger.getLogger(BankLauncher.class);
	DatabaseConnection db = new DatabaseConnection();
	BankLauncher launcher = new BankLauncher();

	String numeric = ".*[0-9].*";
	String aplphanumeric = "^([a-zA-Z+]+[0-9+]+[&@!#+]+).*$";
	boolean flag = false;

	public boolean userNameValidation(String userid) {

		if (userid.matches(numeric)) {
			if (userid.length() >= 5) {
				flag = true;
			} else {
				logger.warn(
						"the username should be equal or greater than 5 in length and it should contain only numeric");
				System.exit(0);
			}
		} else {
			logger.warn("the username should be only in numeric ");
			System.exit(0);
		}

		return flag;

	}

	public boolean passwordValidation(String pwd) {
		if (pwd.matches(aplphanumeric)) {
			if (pwd.length() >= 6) {
				flag = true;
			} else {
				logger.warn("The password length should be equal or greater than 6.");
				System.exit(0);
			}
		} else {
			logger.warn("Sorry!!the password should contain atleast one capital,one number and one number");
			System.exit(0);
		}
		return flag;
	}

	public boolean loginValidation(String userid, String pwd) {

		// Check input for null or space

		boolean flag = false;
      if(userid!=null && pwd!=null) {
		if (!userNameValidation(userid) || !passwordValidation(pwd)) {
			System.exit(0);
		} else {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet result = null;
			String selectSQL = "SELECT password FROM accountlogin WHERE accountno = ?";
			try {
				connection = db.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, userid);
				result = preparedStatement.executeQuery();
				while (result.next()) {

					String password = result.getString("password");

					if (pwd.equals(password)) {
						flag = true;
						logger.info("login successfull");
						
					} else {
						logger.warn("Sorry!either the username and password did not watch");
						System.exit(0);
					}
				}

			} catch (SQLException e) {
				logger.error("unknown system error,please try again later");
				e.printStackTrace();
				System.exit(0);
			} finally {
				try {
					result.close();
					preparedStatement.close();
				} catch (SQLException e) {
					logger.error("unknown system error,please try again later");
					e.printStackTrace();
					System.exit(0);
				}
			}

		}
	}else {
		logger.error("The fields should not be empty.please enter the values");
	}
		return flag;

	}

}
