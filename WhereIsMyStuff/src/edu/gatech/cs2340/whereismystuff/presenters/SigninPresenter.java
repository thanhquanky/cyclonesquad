package edu.gatech.cs2340.whereismystuff.presenters;

import org.json.JSONException;
import org.json.JSONObject;

import edu.gatech.cs2340.whereismystuff.commands.AuthenticateUserCommand;
import edu.gatech.cs2340.whereismystuff.commands.CommandProcessor;
import edu.gatech.cs2340.whereismystuff.commands.ICommand;
import edu.gatech.cs2340.whereismystuff.models.IObserver;
import edu.gatech.cs2340.whereismystuff.models.IUserModel;
import edu.gatech.cs2340.whereismystuff.models.User;
import edu.gatech.cs2340.whereismystuff.views.ISigninView;



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

	/*
	 * callback function from API call
	 * 
	 * @see edu.gatech.cs2340.whereismystuff.models.IObserver#notify(org.json.JSONObject)
	 */
	@Override
	public void notify(JSONObject jsonObject) {
		User u = User.NULL_USER;

		try {
			boolean authenticate = false;
			if (jsonObject != null) {
				authenticate = jsonObject.getBoolean("authenticate");
				if (authenticate) {
					JSONObject userInfo = jsonObject.getJSONObject("user"); 
					u = new User(userInfo.getString("username"), userInfo.getString("password"), userInfo.getString("email"));
					u.setLocation(userInfo.getString("location"));
					u.setPhone(userInfo.getString("phone"));
					u.setToken(userInfo.getString("token"));
					u.setFirstName(userInfo.getString("firstname"));
					u.setLastName(userInfo.getString("lastname"));
					signinView.setUser(u);
				}
				else {
					signinView.setErrorMessage("Invalid authentication");
				}
			}
			else {
				signinView.setErrorMessage("Oops, something wrong happen. Please try again");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			signinView.setErrorMessage("Oops, something wrong happen. Please try again");
			e.printStackTrace();
		}			
		signinView.advance();
	}
	
	

}
