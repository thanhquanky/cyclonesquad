package edu.gatech.cs2340.whereismystuff.views;

import edu.gatech.cs2340.whereismystuff.R;
import edu.gatech.cs2340.whereismystuff.models.UserModelRest;
import edu.gatech.cs2340.whereismystuff.models.User;
import edu.gatech.cs2340.whereismystuff.presenters.RegisterPresenter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements IRegisterView{
	private RegisterPresenter registerPresenter;
	private String message;
	private User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		registerPresenter = new RegisterPresenter(this, new UserModelRest());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void onRegisterClick(View source) {
		String usernameText = getUsernameField().getText().toString();
		String passwordText = getPasswordField().getText().toString();
		String emailText = getEmailField().getText().toString();
		registerPresenter.onRegisterClick(usernameText, passwordText, emailText);
	}

	private TextView getUsernameField() {
		return (TextView)findViewById(R.id.username);
	}
	
	private TextView getPasswordField() {
		return (TextView)findViewById(R.id.password);
	}
	
	private TextView getEmailField() {
		return (TextView)findViewById(R.id.email);
	}

	@Override
	public void setMessage(String pMessage) {
		// TODO Auto-generated method stub
		message = pMessage;
	}

	@Override
	public void advance() {
		// TODO Auto-generated method stub
		if (user.getEmail()!="") {
			Intent intent = new Intent("edu.gatech.cs2340.whereismystuff.UserProfile");
			Bundle b = new Bundle();
			b.putString("username", user.getUsername());
			b.putString("email", user.getEmail());
			intent.putExtras(b);
			startActivity(intent);
		}
		else {
			Toast toast = Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	@Override
	public void setUser(User u) {
		// TODO Auto-generated method stub
		user = u;
	}

}
