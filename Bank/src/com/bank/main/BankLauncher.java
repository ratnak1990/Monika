package com.bank.main;

import java.text.ParseException;
import java.util.ArrayList;

import javax.rmi.CORBA.Stub;

import org.apache.log4j.Logger;

import com.bank.businesslogic.LoginValidation;
import com.bank.businesslogic.Student;
import com.bank.businesslogic.TransactionDetails;

public class BankLauncher {
	private static final Logger logger = Logger.getLogger(BankLauncher.class);
	
	public static void main(String[] args) throws ParseException {
		//new LoginValidation().loginValidation(args[0], args[1]);
		//TransactionDetails transfer=  new TransactionDetails();
		//logger.info("enter your account number to view your last ten transactions");
		//transfer.ShowSavingBalance(args[0]);
		
		//logger.info("enter the start and end date of your statement in the format mm/dd/yyyy");
		//transfer.searchByDate(args[0], args[1]);
		
		

//		ArrayList lists= new ArrayList(); 
//	    
//		
//		
//		for(int i=0;i<10;i++) {
//		lists.add(i);
//		logger.info( ""+i);
//		}
//		
//		
//		logger.info("-----------------------------");
//		for(int i=0;i<lists.size();i++) {
//			
//			logger.info("Value :"+lists.get(i));
//			if(lists.contains(2))
//			{
//				lists.remove(i);
//				logger.info("INDEX :" +i);
//				logger.info(lists.get(i));
//				
//			}
//			
//		}
//		
//		logger.info("----------------------");
//		for(int i=0;i<lists.size();i++) {
//			
//			logger.info(lists.get(i));
//				}
			
		//tostring method()

		Student s=new Student();
		s.setName1("monika");
		s.setName2("ratna");
		s.setAge(50);
		System.err.println(s.hashCode());
		
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
}
