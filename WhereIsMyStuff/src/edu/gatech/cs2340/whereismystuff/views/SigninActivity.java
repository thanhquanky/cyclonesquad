package edu.gatech.cs2340.whereismystuff.views;

import edu.gatech.cs2340.whereismystuff.R;
import edu.gatech.cs2340.whereismystuff.models.User;
import edu.gatech.cs2340.whereismystuff.models.UserModelRest;
import edu.gatech.cs2340.whereismystuff.presenters.SigninPresenter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SigninActivity extends Activity implements ISigninView{

	private SigninPresenter signinPresenter;
	private User user = User.NULL_USER;
	private String errorMessage = null; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);

		signinPresenter = new SigninPresenter(this, new UserModelRest());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signin, menu);
		return true;
	}
	
	public void onSigninClick(View source) {
		String usernameText = getUsernameField().getText().toString();
		String passwordText = getPasswordField().getText().toString();
		signinPresenter.onSigninClick(usernameText, passwordText);
	}
	
	public void onRegisterClick(View source) {
		Intent intent = new Intent("edu.gatech.cs2340.whereismystuff.Register");
		startActivity(intent);
	}
	
	private EditText getUsernameField() {
		return (EditText) findViewById(R.id.usernameSignIn);
	}
	
	private EditText getPasswordField() {
		return (EditText) findViewById(R.id.passwordSignin);
	}

	
	@Override
	public void advance() {
		// TODO Auto-generated method stub
		if (user.getEmail()!="") {
			Intent intent = new Intent("edu.gatech.cs2340.whereismystuff.UserProfile");
			Bundle b = new Bundle();
			b.putParcelable("user", user);
			intent.putExtras(b);
			startActivity(intent);
		}
		else {
//			Toast toast = Toast.makeText(getBaseContext(), errorMessage, Toast.LENGTH_SHORT);
//			toast.show();
			getPasswordField().setError(errorMessage);
			getPasswordField().requestFocus();
		}
	}

	@Override
	public void setUser(User u) {
		// TODO Auto-generated method stub
		user = u;
	}

	@Override
	public void setErrorMessage(String message) {
		// TODO Auto-generated method stub
		errorMessage = message;
	}


}
