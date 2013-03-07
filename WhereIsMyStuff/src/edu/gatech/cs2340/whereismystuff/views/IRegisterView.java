package edu.gatech.cs2340.whereismystuff.views;

import edu.gatech.cs2340.whereismystuff.models.User;

public interface IRegisterView {
	void setMessage(String message);
	
	void advance();

	void setUser(User u);
}
