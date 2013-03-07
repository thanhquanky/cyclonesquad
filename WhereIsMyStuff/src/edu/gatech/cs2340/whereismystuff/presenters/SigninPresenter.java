package edu.gatech.cs2340.whereismystuff.presenters;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import edu.gatech.cs2340.whereismystuff.commands.AuthenticateUserCommand;
import edu.gatech.cs2340.whereismystuff.commands.CommandProcessor;
import edu.gatech.cs2340.whereismystuff.commands.ICommand;
import edu.gatech.cs2340.whereismystuff.models.IObserver;
import edu.gatech.cs2340.whereismystuff.models.IUserModel;
import edu.gatech.cs2340.whereismystuff.models.User;
import edu.gatech.cs2340.whereismystuff.views.ISigninView;
import edu.gatech.cs2340.whereismystuff.views.IUserProfileView;



public class SigninPresenter implements IObserver {
	private final ISigninView signinView;
	private final IUserModel userModel;
	
	public SigninPresenter(ISigninView pSigninView, IUserModel pModel) {
		signinView = pSigninView;
		userModel = pModel;
		userModel.addObserver(this);
	}
	
	
	/**
	 * Handle the accept button click in the ui,
	 * Authenticate user, add it to the model, and
	 * ask the view to go to the next screen.
	 * 
	 * @param username 
	 * @param password
	 */
	public void onSigninClick(String username, String password) {
		ICommand cmd = new AuthenticateUserCommand(new User(username, password), userModel);
		CommandProcessor proc = CommandProcessor.getInstance();
		proc.execute(cmd);
		//signinView.advance();
	}
	
	@Override
	public void notify(IUserModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notify(JSONObject jsonObject) {
		String username;
		String email;
		String password;
		try {
			username = jsonObject.getString("username");
			email = jsonObject.getString("email");
			password = jsonObject.getString("password");
			User u = new User(username, password, email);
			Log.d("API", "Email is : " + email);
			if ("".equals(u.getEmail())) {
				signinView.setErrorMessage("Invalid authentication");
				signinView.advance();
			}
			else {
				signinView.setUser(u);
				signinView.advance();
//				profileView.setUser(u);
//				profileView.advance();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	

}
