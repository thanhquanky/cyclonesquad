package edu.gatech.cs2340.whereismystuff.views;

import edu.gatech.cs2340.whereismystuff.models.User;

public interface IUserProfileView {
	void setUser(User u);
	void advance();
}
