package com.bank.vo;

public class AccountLogin {

	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public AccountLogin(String Username, String Password) {
		username= Username;
		password= Password;
	
	}
	
	
	
	
}
