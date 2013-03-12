/*
 * Author: Thanh Ky Quan
 * GTid: 902977469
 */

package edu.gatech.cs2340.whereismystuff.models;

public interface IUserModel extends IObservable{
	void addUser(User u);
	
	void removeUser(User u);
	
	void register(User u);
	
	void authenticateUser(User u);
}
