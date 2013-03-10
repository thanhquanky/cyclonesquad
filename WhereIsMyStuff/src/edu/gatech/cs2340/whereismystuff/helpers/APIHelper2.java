/*
 * Author: Thanh Ky Quan
 * 
 */
package edu.gatech.cs2340.whereismystuff.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import edu.gatech.cs2340.whereismystuff.models.IObserver;

/*
 * APIHelper2 handles GET/POST HTTPrequest from client and return response from server via callback
 * 
 * Improved for abstraction and clarity from APIHelper 
 */
public class APIHelper2{
	private List<BasicNameValuePair> parameters;
	private String apiCommand = "";
	private String method = "GET";
	private IObserver callback;
	private final String API_URL = "http://wims.eu1.frbit.net/api/";
	
	/*
	 * APICaller class handles POST/GET HTTPRequest
	 * 
	 */
	public class APICaller extends AsyncTask<HttpRequest, Boolean, JSONObject> {

		@Override
		protected JSONObject doInBackground(HttpRequest... params) {
			HttpRequest request = params[0];
			Log.d("API", "at APICaller and ready to connect");
			if (getMethod().equals("POST")) {
				request = (PostRequest) request;
				return request.execute();
			}
			request = (GetRequest) request;
			return request.execute(); 
		}
		
		@Override
		protected void onPostExecute(JSONObject result) {
			callback.notify(result);
		}
		
	}
	
	/*
	 * Execute method instantiate APICaller object to carry out API-calling task in different thread
	 */
	public void execute() {
		HttpRequest request = null;
		if (this.getMethod().equals("GET")) {
			try {
				Log.d("API","About to connect server at " + this.API_URL);
				request = new GetRequest(new URL(this.API_URL + this.getApiCommand()), this.getParameters());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				request = new PostRequest(new URL(this.API_URL + this.getApiCommand()), this.getParameters());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		APICaller apiCaller = new APICaller();
		apiCaller.execute(request);
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the apiCommand
	 */
	public String getApiCommand() {
		return apiCommand;
	}

	/**
	 * @param apiCommand the apiCommand to set
	 */
	public void setApiCommand(String apiCommand) {
		this.apiCommand = apiCommand;
	}

	/**
	 * @return the parameters
	 */
	public List<BasicNameValuePair> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<BasicNameValuePair> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the callback
	 */
	public IObserver getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(IObserver callback) {
		this.callback = callback;
	}

}
