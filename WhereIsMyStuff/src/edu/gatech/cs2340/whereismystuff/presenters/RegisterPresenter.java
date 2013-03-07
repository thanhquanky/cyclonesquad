package edu.gatech.cs2340.whereismystuff.presenters;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import edu.gatech.cs2340.whereismystuff.commands.AuthenticateUserCommand;
import edu.gatech.cs2340.whereismystuff.commands.CommandProcessor;
import edu.gatech.cs2340.whereismystuff.commands.ICommand;
import edu.gatech.cs2340.whereismystuff.commands.RegisterUserCommand;
import edu.gatech.cs2340.whereismystuff.models.IObserver;
import edu.gatech.cs2340.whereismystuff.models.IUserModel;
import edu.gatech.cs2340.whereismystuff.models.User;
import edu.gatech.cs2340.whereismystuff.views.IRegisterView;
import edu.gatech.cs2340.whereismystuff.views.ISigninView;

public class RegisterPresenter implements IObserver {
	private IRegisterView registerView;
	private IUserModel userModel;
	
	public RegisterPresenter(IRegisterView pRegisterView, IUserModel pModel) {
		registerView = pRegisterView;
		userModel = pModel;
		userModel.addObserver(this);
	}
	
	public void onRegisterClick(String username, String password, String email) {
		ICommand cmd = new RegisterUserCommand(new User(username, password, email), userModel);
		CommandProcessor proc = CommandProcessor.getInstance();
		proc.execute(cmd);
	}
	
	@Override
	public void notify(IUserModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notify(JSONObject jsonObject) {
		// TODO Auto-generated method stub
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
				registerView.setMessage("Invalid authentication");
				registerView.advance();
			}
			else {
				registerView.setUser(u);
				registerView.advance();
//				profileView.setUser(u);
//				profileView.advance();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
