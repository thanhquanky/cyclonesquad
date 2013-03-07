/*
 * Author: Thanh Ky Quan
 * GTid: 902977469
 */

package edu.gatech.cs2340.whereismystuff.models;

import org.json.JSONObject;

public interface IObserver {
	void notify(IUserModel model);
	void notify(JSONObject jsonObject);
}
