/*
 * Author: Thanh Ky Quan
 * GTid: 902977469
 */

package edu.gatech.cs2340.whereismystuff.models;

import java.util.ArrayList;

import org.json.JSONObject;

import android.util.Log;

import edu.gatech.cs2340.whereismystuff.helpers.APIHelper;
import edu.gatech.cs2340.whereismystuff.helpers.UserAPIHelper;

public class UserModelRest implements IUserModel, IObserver{
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
	public void authenticateUser(User u) {
		UserAPIHelper userAPIHelper = new UserAPIHelper(u, this);
		Log.d("API","At Model, ready to call API Helper");
		userAPIHelper.authenticate();
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
		UserAPIHelper userAPIHelper = new UserAPIHelper(u, this);
		Log.d("API","At Model, ready to call API Helper");
		userAPIHelper.register();
	}

}
