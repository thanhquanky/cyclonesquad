/*
 * Author: Thanh Ky Quan
 * GTid: 902977469
 */
package edu.gatech.cs2340.whereismystuff.helpers;

import org.json.*;

import edu.gatech.cs2340.whereismystuff.R;
import edu.gatech.cs2340.whereismystuff.models.IObserver;
import edu.gatech.cs2340.whereismystuff.models.IUserModel;
import edu.gatech.cs2340.whereismystuff.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class APIHelper {
	private final String API_URL = "http://10.0.2.2/wims/api.php?";
	private static APIHelper instance = null;
	private static JSONObject resultJSON = null;
	public static IObserver callback;
	// prevent instantiation
	protected APIHelper() { }
	
	public static APIHelper getInstance() {
		if (instance == null)
			instance = new APIHelper();
		return instance;
	}
	
	private JSONObject apiCall(String api) {
		APICaller apiCaller = new APICaller();
		AsyncTask task = apiCaller.execute(api);
		return resultJSON;
	}
	
	public JSONObject getUser(String id) throws IOException, JSONException {
		String api = API_URL + "action=get&obj=users&id=";
		if (id == null)
			api += "null";
		else
			api += id;
		return apiCall(api);
	}
	
	public JSONObject getUserByEmail(String email) throws IOException, JSONException {
		String api = API_URL + "action=get&objective=users&email=";
		if (email == null)
			api += "null";
		else
			api += email;
		return apiCall(api);
	}
	
	public JSONObject authenticate(User u) {
		String api = API_URL + "action=authenticate&objective=users&";
		if (u != null) {
			String username = u.getUsername();
			String password = u.getPassword();
			api+="username=" + username + "&password=" + password;
		}
		return apiCall(api);
	}
	
	public JSONObject register(User u) {
		String api = API_URL + "action=register&objective=users&";
		if (u != null) {
			String username = u.getUsername();
			String password = u.getPassword();
			String email = u.getEmail();
			api+="username=" + username + "&password=" + password + "&email=" + email;
		}
		return apiCall(api);
	}
	
	public void setResult(JSONObject obj) {
		resultJSON = obj;
	}
	
	public class APICaller extends AsyncTask<String, Boolean, JSONObject>{
		private String readAll(Reader reader) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = reader.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
		@Override
		protected JSONObject doInBackground(String... params) {
			String api = params[0];
			String jsonText = "";
			JSONObject result = null;
			Log.d("API", api);
			URL url;
			try {
				url = new URL(api);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				try {
					InputStream myInputStream = conn.getInputStream();
					InputStreamReader myInputStreamReader = new InputStreamReader(myInputStream);
			        jsonText = readAll(myInputStreamReader);
					Log.d("API", jsonText);
					result = new JSONObject(jsonText);
					resultJSON = result;
					return result;
				}
				finally {
					conn.disconnect();
				}
				
			} catch (MalformedURLException e) {
				Log.d("Err", "Malformed URL");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("Err", "IO Exception");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("Err", "JSON exception");
				e.printStackTrace();
			}
			return result;
		}
		
		protected void onPostExecute(JSONObject jsonObj) {
			if (jsonObj != null) {
				callback.notify(jsonObj);
			}
		}
	}
}