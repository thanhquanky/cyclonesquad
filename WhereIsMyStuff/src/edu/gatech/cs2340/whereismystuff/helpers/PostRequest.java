package edu.gatech.cs2340.whereismystuff.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PostRequest extends HttpRequest implements Strategy {
	private List<BasicNameValuePair> parameters;
	private String postData;
	public PostRequest(URL pURL, List<BasicNameValuePair> pParameters) {
		super(pURL);
		parameters = pParameters;
		this.prepare();
	}

	/*
	 * Prepare url for REST call
	 */
	private void prepare() {
		try {
			postData = "";
			for (BasicNameValuePair parameter : parameters) {
				postData += parameter.getName().toString() + "=" + URLEncoder.encode(parameter.getValue(), "UTF-8") + "&";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject execute() {
		JSONObject result = null;
		Log.d("API","Sending data to " + this.getUrl());
		try {
			// post data to server
			connection = (HttpURLConnection) this.getUrl().openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setFixedLengthStreamingMode(postData.getBytes().length);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(postData);
			out.flush();
			out.close();    
			Log.d("API", "Sent data to " + this.getUrl() + ". Ready to get response");
			// get response
			InputStream myInputStream = connection.getInputStream();
			InputStreamReader myInputStreamReader = new InputStreamReader(myInputStream);
	        String jsonText = this.readAll(myInputStreamReader);
			result = new JSONObject(jsonText);
			connection.disconnect();
			myInputStream.close();
			myInputStreamReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}
