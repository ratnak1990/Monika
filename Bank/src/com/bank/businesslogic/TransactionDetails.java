package com.bank.businesslogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import org.apache.log4j.Logger;

import com.bank.dbo.DatabaseConnection;
import com.bank.main.BankLauncher;
import com.bank.vo.UserDetails;

public class TransactionDetails {

	private static final Logger logger = Logger.getLogger(BankLauncher.class);
	DatabaseConnection db = new DatabaseConnection();
	ArrayList<UserDetails> userList = new ArrayList();
	UserDetails user;

	public void lastTenTransaction(String accountno) {

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet result = null;
		String SQL = "SELECT * from usertable where accountno=? order by transactiondate ";
		try {
			int count = 1;
			connection = db.getConnection();
			preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, accountno);
			result = preparedstatement.executeQuery();
			while (result.next() && count <= 10) {
				count++;
				user = new UserDetails();

				user.setAccountNo(result.getInt("accountno"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setBalance(result.getInt("balance"));
				user.setTransfer(result.getInt("transfer"));
				user.setDeposit(result.getInt("deposit"));
				user.setWithdraw(result.getInt("withdraw"));
				user.setDate(result.getString("transactiondate"));
				user.setAccountType(result.getString("accounttype"));
				userList.add(user);

			}
			result.close();
			for (UserDetails userDetails : userList) {
				logger.info(userDetails.getAccountNo() + "|" + userDetails.getFirstName() + "|"
						+ userDetails.getLastName() + "|" + userDetails.getBalance() + "--" + userDetails.getTransfer()
						+ "|" + userDetails.getDeposit() + "|" + userDetails.getWithdraw() + "|" + userDetails.getDate()
						+ "|" + userDetails.getAccountType());
			}

		} catch (SQLException e) {
			logger.warn("unknown system error in query");
			e.printStackTrace();
		} finally {
			try {
				preparedstatement.close();
				connection.close();
			} catch (SQLException e) {
				logger.warn("unknown system requirement");
				e.printStackTrace();
			} finally {
				connection = null;
			}
		}
	}

	public void ShowSavingBalance(String accountno) {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet result = null;
		String SQL = "SELECT balance from usertable where accountno=? and accounttype ='S' order by transactiondate desc limit 1";
		try {
			connection = db.getConnection();
			preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, accountno);

			result = preparedstatement.executeQuery();
			while (result.next()) {
				int savingBalance = result.getInt("balance");
				logger.info("your saving balance is " + savingBalance);
				ShowCheckingBalance(accountno);
			}
		} catch (SQLException e) {
			logger.error("unknown system error");
			e.printStackTrace();
		} finally {
			try {
				result.close();
				preparedstatement.close();
				connection.close();
			} catch (SQLException e) {
				logger.warn("unknown systen error");
				e.printStackTrace();
			}
		}

	}

	public void ShowCheckingBalance(String accountno) {

		
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet result = null;
		String SQL = "SELECT balance from usertable where accountno=? and accounttype ='C' order by transactiondate desc limit 1";
		try {
			connection = db.getConnection();
			preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, accountno);

			result = preparedstatement.executeQuery();
			while (result.next()) {
				int checkingBalance = result.getInt("balance");
				logger.info("your checking balance is " + checkingBalance);
				lastTenTransaction(accountno);
			}
		} catch (SQLException e) {
			logger.error("unknown system error");
			e.printStackTrace();
		} finally {
			try {
				result.close();
				preparedstatement.close();
				connection.close();
			} catch (SQLException e) {
				logger.warn("unknown systen error");
				e.printStackTrace();
			}
		}

	}

	public void searchByDate(String startDate, String endDate ) throws ParseException {
		
		 //String Date1=startDate;  
		 //SimpleDateFormat df = new SimpleDateFormat("\"yyyy/MM/dd HH:mm:ss\"");
		 //Date parsedDate=df.parse(Date1); 
		 //java.util.Date startDate1 = new java.util.Date();
		
		//java.util.Date dt = new java.util.Date();

		//java.text.SimpleDateFormat sdf = 
		     //new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		//String currentTime = sdf.format(dt);
		
		ArrayList<UserDetails> userListMain = new ArrayList<UserDetails>();
		UserDetails userMain;
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = sdf1.parse(startDate);
		//logger.info(date);
		
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
		java.util.Date date2 = sdf1.parse(endDate);
		java.sql.Date sqlEndDate = new java.sql.Date(date2.getTime()); 
		
		
		logger.info("The startdate is "+ sqlStartDate);
		logger.info("The enddate is"+  sqlEndDate);
		
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet result = null;
		String SQL = "SELECT * from usertable where   transactiondate>= ? and transactiondate <= ? order by transactiondate ";
		try {
			connection = db.getConnection();
			preparedstatement = connection.prepareStatement(SQL);

			preparedstatement.setDate(1,sqlStartDate);
			preparedstatement.setDate(2,sqlEndDate);
			//logger.info();
			
			result = preparedstatement.executeQuery();
			//boolean result1=result.next();
			//logger.info( result1);
			
			while (result.next() ) {
				
				userMain = new UserDetails();

				userMain.setAccountNo(result.getInt("accountno"));
				userMain.setFirstName(result.getString("firstname"));
				userMain.setLastName(result.getString("lastname"));
				userMain.setBalance(result.getInt("balance"));
				userMain.setTransfer(result.getInt("transfer"));
				userMain.setDeposit(result.getInt("deposit"));
				userMain.setWithdraw(result.getInt("withdraw"));
				userMain.setDate(result.getString("transactiondate"));
				userMain.setAccountType(result.getString("accounttype"));
				userListMain.add(userMain);
     
			}
			
			  logger.info(userListMain.size());
			//result.close();
			for (UserDetails userDetails : userListMain) {
				logger.info(userDetails.getAccountNo() + "|" + userDetails.getFirstName() + "|"
						+ userDetails.getLastName() + "|" + userDetails.getBalance() + "--" + userDetails.getTransfer()
						+ "|" + userDetails.getDeposit() + "|" + userDetails.getWithdraw() + "|" + userDetails.getDate()
						+ "|" + userDetails.getAccountType());
			}
			
		}catch (SQLException e) {
			logger.error("unknown system error");
			e.printStackTrace();
		} finally {
			try {

				preparedstatement.close();
				connection.close();
			} catch (SQLException e) {
				logger.warn("unknown systen error");
				e.printStackTrace();
			}
		}
	}
	
	public void makeTransfer() {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String SQL
		
	}
}
