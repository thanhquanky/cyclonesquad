package edu.gatech.cs2340.whereismystuff.models;
/*
 * Author: Thanh Ky Quan
 */

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable 
{
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String location;
	private String token;
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
		this(pUsername, pPassword);
		email = pEmail;
	}
	
	public User(Parcel parcel) {
		username = parcel.readString();
		email = parcel.readString();
		firstName = parcel.readString();
		lastName = parcel.readString();
		location = parcel.readString();
		phone = parcel.readString();
		token = parcel.readString();
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String pFirstName) {
		this.firstName = pFirstName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String pLastName) {
		this.lastName = pLastName;
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

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
//		HashMap<String, String> parameters = new HashMap<String, String>();
//		parameters.put("username", this.getUsername());
//		parameters.put("location", this.getLocation());
//		parameters.put("email", this.getEmail());
//		parameters.put("firstName", this.getFirstName());
//		parameters.put("lastName", this.getLastName());
//		parameters.put("phone", this.getPhone());
//		parameters.put("token", this.getToken());
//		dest.writeMap(parameters);
		dest.writeString(this.getUsername());
		dest.writeString(this.getEmail());
		dest.writeString(this.getFirstName());
		dest.writeString(this.getLastName());
		dest.writeString(this.getLocation());
		dest.writeString(this.getPhone());
		dest.writeString(this.getToken());
	}

	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
		    return new User(in);
		}
		
		@Override
		public User[] newArray(int size) {
		    return new User[size];
		}
	};
}
