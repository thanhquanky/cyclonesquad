package edu.gatech.cs2340.whereismystuff.commands;

import edu.gatech.cs2340.whereismystuff.models.IUserModel;
import edu.gatech.cs2340.whereismystuff.models.User;

public class RegisterUserCommand implements ICommand {
	private User myUser;
	private IUserModel myModel;
	
	public RegisterUserCommand(User user, IUserModel model) {
		myUser = user;
		myModel = model;
	}
	
	@Override
	public boolean execute() {
		myModel.register(myUser);
		return true;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
