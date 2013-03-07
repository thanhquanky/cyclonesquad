package edu.gatech.cs2340.whereismystuff.presenters;

import org.json.JSONObject;

import edu.gatech.cs2340.whereismystuff.models.IObserver;
import edu.gatech.cs2340.whereismystuff.models.IUserModel;
import edu.gatech.cs2340.whereismystuff.models.User;
import edu.gatech.cs2340.whereismystuff.views.IUserProfileView;

public class UserProfilePresenter implements IObserver{
	private IUserProfileView myView;
	private IUserModel myModel;
	
	public UserProfilePresenter(IUserProfileView pView, IUserModel pModel) {
		myView = pView;
		myModel = pModel;
	}
	
	public void onSignoutClick() {
		myView.setUser(User.NULL_USER);
		myView.advance();
	}
	
	@Override
	public void notify(IUserModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notify(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
	}

}
