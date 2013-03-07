/*
 * Author: Thanh Ky Quan
 * GTid: 902977469
 */

package edu.gatech.cs2340.whereismystuff.models;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import edu.gatech.cs2340.whereismystuff.helpers.APIHelper;

public class IUserModelRest implements IUserModel, IObserver{
	private APIHelper apiHelperInstance = APIHelper.getInstance();
	private ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
	@Override
	public void addObserver(IObserver obs) {
		if (!observers.contains(obs)) {
			observers.add(obs);
		}
		
	}

	@Override
	public void removeObserver(IObserver obs) {
		if (observers.contains(obs)) {
			observers.remove(obs);
		}	
	}

	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserById(String id) {
		User result = User.NULL_USER;
		try {
			JSONObject userInJSON = apiHelperInstance.getUser(id);
			String username = null;
			String email = null;
			String password = null;
			
			username = userInJSON.getString("username");
			email = userInJSON.getString("email");
			password = userInJSON.getString("password");
			
			result = new User(username, email, password);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User findUserByEmail(String email) {
		User result = User.NULL_USER;
		try {
			JSONObject userInJSON = apiHelperInstance.getUserByEmail(email);
			String username = null;
			String password = null;
			
			username = userInJSON.getString("username");
			email = userInJSON.getString("email");
			password = userInJSON.getString("password");
			
			result = new User(username, email, password);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void authenticateUser(User u) {
		APIHelper apiHelper = APIHelper.getInstance();
		APIHelper.callback = this;
		apiHelper.authenticate(u);
	}

	@Override
	public void notify(IUserModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notify(JSONObject jsonObject) {
		for (IObserver observer : observers) {
			observer.notify(jsonObject);
		}
		
	}

	@Override
	public void register(User u) {
		// TODO Auto-generated method stub
		APIHelper apiHelper = APIHelper.getInstance();
		APIHelper.callback = this;
		apiHelper.register(u);
	}

}
