package edu.gatech.cs2340.whereismystuff.views;

import edu.gatech.cs2340.whereismystuff.models.User;

public interface ISigninView {
	void advance();
	void setErrorMessage(String message);
	void setUser(User u);
}
