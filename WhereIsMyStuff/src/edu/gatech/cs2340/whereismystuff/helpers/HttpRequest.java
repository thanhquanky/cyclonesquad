package edu.gatech.cs2340.whereismystuff.helpers;

import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class HttpRequest{
	private URL url;
	protected HttpURLConnection connection;
	/*
	 * HttpRequest constructor
	 * 
	 * @param URL pUrl
	 */
	public HttpRequest(URL pURL) {
		setUrl(pURL);
	}
	/*
	 * get request url
	 * 
	 * @return URL url
	 */
	public URL getUrl() {
		return url;
	}
	
	/*
	 * set request url
	 * 
	 * @param URL url
	 */
	public void setUrl(URL url) {
		this.url = url;
	}
	
	/*
	 * Read response from server
	 * 
	 * @param Reader reader
	 * 
	 * @return String response
	 */
	protected String readAll(Reader reader) {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    try {
			while ((cp = reader.read()) != -1) {
			  sb.append((char) cp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return sb.toString();
	  }
	/**
	 * @return the connection
	 */
	public HttpURLConnection getConnection() {
		return connection;
	}
	/**
	 * @param connection the connection to set
	 */
	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}
	
	public JSONObject execute() {
		return null;
	}
}
