package edu.gatech.cs2340.whereismystuff.views;

import edu.gatech.cs2340.whereismystuff.R;
import edu.gatech.cs2340.whereismystuff.models.UserModelRest;
import edu.gatech.cs2340.whereismystuff.models.User;
import edu.gatech.cs2340.whereismystuff.presenters.UserProfilePresenter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
		TextView phoneText = getPhoneField();
		TextView emailText = getEmailField();
		TextView nameText = getNameField();
		TextView locationText = getLocationField();
		
		User u = getIntent().getExtras().getParcelable("user");
		nameText.setText(u.getFullName());
		locationText.setText(u.getLocation());
		emailText.setText(u.getEmail());		
		usernameText.setText(u.getUsername());
		phoneText.setText(u.getPhone());
		
		userProfilePresenter = new UserProfilePresenter(this, new UserModelRest());
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
	
	private TextView getNameField() {
		return (TextView)findViewById(R.id.nameTextField);
	}
	
	private TextView getPhoneField() {
		return (TextView)findViewById(R.id.phoneTextField);
	}
	
	private TextView getEmailField() {
		return (TextView)findViewById(R.id.emailTextField);
	}
	
	private TextView getLocationField() {
		return (TextView)findViewById(R.id.locationTextField);
	}

	@Override
	public void advance() {
		Intent intent = null;
		if (user.getEmail().equals("")) {
			intent = new Intent("edu.gatech.cs2340.whereismystuff.SigninActivity");
		}
		startActivity(intent);
	}
	
	public void onSignoutClick(View source) {
		userProfilePresenter.onSignoutClick();
	}

}
