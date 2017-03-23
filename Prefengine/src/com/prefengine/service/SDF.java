package com.prefengine.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class SDF {
	/**
	 * Shortcut to using System.out.println()
	 * Prints a new empty line.
	 */
	public static void printline(){
		System.out.println();
	}
	/**
	 * Shortcut to using System.out.println()
	 * Outputs a String x in console and closes.
	 * @param x
	 */
	public static void printline(String x){
		System.out.println(x);
	}
	/**
	 * Shortcut to using System.out.println()
	 * Outputs an object's toString() in console and closes.
	 * @param x
	 */
	public static void printline(Object x){
		System.out.println(x);
	}
	/**
	 * Shortcut to using System.out.print()
	 * Outputs a String x on a line in the console without closing
	 * @param x
	 */
	public static void print(String x){
		System.out.print(x);
	}
	/**
	 * Shortcut to using System.out.print()
	 * Outputs an object's toString() in console.
	 * @param x
	 */
	public static void print(Object x){
		System.out.println(x);
	}
	
	/**
	 * This sends a RESTful get request and returns JSON data
	 * @param urlString
	 * @return
	 * @throws ParseException
	 * @throws JSONException
	 */
	public static JSONObject getRequestRESTful(String urlString) throws JSONException{
		JSONObject jsonObject = null;
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			JSONTokener tokener = new JSONTokener(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			jsonObject = new JSONObject(tokener);
			
				     // new InputStreamReader(conn.getInputStream(), "UTF-8");
			//jsonObject = new JSONObject(simpleJsonObject);
			//System.out.println("Output from Server .... \n");
			/**String output;
			while ((output = br.readLine()) != null) {
				result += output;
				SDF.printline(output);
			}*/
			conn.disconnect();
		  } catch (MalformedURLException | UnknownHostException e) {

			e.printStackTrace();

		  } catch (IOException e) {
			  e.printStackTrace();
		}
		return jsonObject;
	}
}
