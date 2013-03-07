package edu.gatech.cs2340.whereismystuff.commands;

import edu.gatech.cs2340.whereismystuff.models.IUserModel;
import edu.gatech.cs2340.whereismystuff.models.User;

public class AuthenticateUserCommand implements ICommand {
	private User myUser;
	private IUserModel myModel;
	
	public AuthenticateUserCommand(User user, IUserModel userModel) {
		myUser = user;
		myModel = userModel;
	}

	@Override
	public boolean execute() {
		myModel.authenticateUser(myUser);
		return false;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
