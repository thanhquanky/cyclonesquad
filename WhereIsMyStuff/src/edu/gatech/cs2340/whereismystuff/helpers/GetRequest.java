package edu.gatech.cs2340.whereismystuff.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;

public class GetRequest extends HttpRequest implements Strategy{
	private List<BasicNameValuePair> parameters;
	private String query;
	/*
	 * GetRequest constructor
	 * 
	 * @param URL pURL
	 * @param HashMap<K,V> pParameters
	 *
	 */
	public GetRequest(URL pURL, List<BasicNameValuePair> pParameters) {
		super(pURL);
		parameters = pParameters;
		query = "";
		this.prepare();
	}
	
	/*
	 * Prepare url for REST call
	 */
	@SuppressLint("DefaultLocale")
	private void prepare() {

		for (int i=0; i<parameters.size(); i++) {
			BasicNameValuePair parameter = parameters.get(i);
			//url += parameter.getName().toLowerCase();
			if (!parameter.getValue().equals("")) {
				query += parameter.getValue().toLowerCase() + "/";
			}
		}
		if (query.charAt(query.length()-1) == '/')
			query = query.substring(0, query.length() - 1);
		try {
			this.setUrl(new URL(this.getUrl() + query));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * execute request
	 * 
	 * @return void
	 * @see edu.gatech.cs2340.whereismystuff.helpers.Strategy#execute()
	 */
	@Override
	public JSONObject execute() {
		JSONObject result = null;
		try {
			connection = (HttpURLConnection) this.getUrl().openConnection();
			InputStream myInputStream = connection.getInputStream();
			InputStreamReader myInputStreamReader = new InputStreamReader(myInputStream);
	        String jsonText = this.readAll(myInputStreamReader);
			result = new JSONObject(jsonText);
			connection.disconnect();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
