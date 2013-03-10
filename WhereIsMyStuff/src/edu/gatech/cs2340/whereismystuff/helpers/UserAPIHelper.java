package edu.gatech.cs2340.whereismystuff.helpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import edu.gatech.cs2340.whereismystuff.models.IObserver;
import edu.gatech.cs2340.whereismystuff.models.User;

public class UserAPIHelper{
	protected User user;
	protected APIHelper2 APIHelper;
	public UserAPIHelper(User pUser, IObserver pCallback) {
		user = pUser;
		APIHelper = new APIHelper2(); 
		APIHelper.setCallback(pCallback);
	}
		
	public void getData() {
		APIHelper.setApiCommand("");
		APIHelper.setMethod("POST");
	}
	public void authenticate() {
		APIHelper.setApiCommand("login");
		APIHelper.setMethod("POST");
		BasicNameValuePair username = new BasicNameValuePair("username", user.getUsername());
		BasicNameValuePair password = new BasicNameValuePair("password", user.getPassword());
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		parameters.add(username);
		parameters.add(password);
		APIHelper.setParameters(parameters);
		APIHelper.execute();
	}
	public void register() {
		APIHelper.setApiCommand("register");
		APIHelper.setMethod("POST");
		BasicNameValuePair username = new BasicNameValuePair("username", user.getUsername());
		BasicNameValuePair password = new BasicNameValuePair("password", user.getPassword());
		BasicNameValuePair email = new BasicNameValuePair("email", user.getEmail());
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		parameters.add(username);
		parameters.add(password);
		parameters.add(email);
		APIHelper.setParameters(parameters);
		APIHelper.execute();
	}
	
//	public void authenticate() {
//		String command = "user/authenticate/";
//		APIHelper.setMethod("GET");
//		BasicNameValuePair username = new BasicNameValuePair("username", user.getUsername());
//		BasicNameValuePair password = new BasicNameValuePair("password", user.getPassword());
//		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
//		parameters.add(username);
//		parameters.add(password);
//		APIHelper.setParameters(parameters);
//		APIHelper.setApiCommand(command);
//		APIHelper.execute();
//	}
}
