package edu.gatech.cs2340.whereismystuff.models;
/*
 * Author: Thanh Ky Quan
 */

import java.io.Serializable;

public class User implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String email;
	private boolean isLocked;
	private int loginAttempts;
	public static final User NULL_USER = new User("", "", "");
	
	public User(String pUsername, String pPassword)
	{
		username = pUsername;
		password = pPassword;
		email = "";
	}
	
	public User(String pUsername, String pPassword, String pEmail)
	{
		username = pUsername;
		password = pPassword;
		email = pEmail;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the isUnlocked
	 */
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * @param isUnlocked the isUnlocked to set
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * @return the loginAttempts
	 */
	public int getLoginAttempts() {
		return loginAttempts;
	}

	/**
	 * @param loginAttempts the loginAttempts to set
	 */
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public boolean verify(String password)
	{
		if(password.equals(this.password) && !isLocked)
			return true;
		else
			loginAttempts++;
		return true;
	}
}
