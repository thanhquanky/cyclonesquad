package edu.gatech.cs2340.whereismystuff.views;

import edu.gatech.cs2340.whereismystuff.R;
import edu.gatech.cs2340.whereismystuff.R.layout;
import edu.gatech.cs2340.whereismystuff.R.menu;
import edu.gatech.cs2340.whereismystuff.models.IUserModelRest;
import edu.gatech.cs2340.whereismystuff.models.User;
import edu.gatech.cs2340.whereismystuff.presenters.UserProfilePresenter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class UserProfileActivity extends Activity implements IUserProfileView{
	private User user;
	private UserProfilePresenter userProfilePresenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Log.d("thanhquanky", "Loading UserProfileActivity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		TextView usernameText = getUsernameField();
		Bundle b = getIntent().getExtras();
		usernameText.setText(b.getString("username"));
		userProfilePresenter = new UserProfilePresenter(this, new IUserModelRest());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

	@Override
	public void setUser(User u) {
		// TODO Auto-generated method stub
		user = u;
	}
	
	private TextView getUsernameField() {
		return (TextView)findViewById(R.id.username);
	}

	@Override
	public void advance() {
		Intent intent = null;
		if (User.NULL_USER.equals(user)) {
			intent = new Intent("edu.gatech.cs2340.whereismystuff.SigninActivity");
		}
		startActivity(intent);
	}
	
	public void onSignoutClick(View source) {
		userProfilePresenter.onSignoutClick();
	}

}
