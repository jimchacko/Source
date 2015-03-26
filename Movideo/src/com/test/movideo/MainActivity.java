package com.test.movideo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String APIURL = "http://api.movideo.com/rest/session?applicationalias="
			+ Constants.APPLICATION_ALIAS + "&key=" + Constants.CLIENT_KEY + "&output=xml";

	String searchKey = "";
	boolean internetPresent = false;
	AlertDialog.Builder alt_bld;
	ProgressDialog progressDialog;

	// Async task to read the XML for Token
	private class CallAPI extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			String urlString = APIURL;// URL to call

			InputStream in = null;
			String result = "";

			// HTTP Get
			try {
				URL url = new URL(urlString);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}

			// Parse XML
			XmlPullParserFactory pullParserFactory;
			try {
				pullParserFactory = XmlPullParserFactory.newInstance();
				XmlPullParser parser = pullParserFactory.newPullParser();
				parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
				parser.setInput(in, null);
				result = parseXML(parser);
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return result;

		}

		private String parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
			int eventType = parser.getEventType();

			String token = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String name = null;
				switch (eventType) {
				case XmlPullParser.START_TAG:
					name = parser.getName();

					if (name.equals("token")) {
						token = parser.nextText();
						break;
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				} // end switch

				eventType = parser.next();
			} // end while
			return token;
		}

		protected void onPostExecute(String result) {

			Intent intent = new Intent(getApplicationContext(), ResultActivity.class);

			intent.putExtra(Constants.EXTRA_MESSAGE, result);
			if (searchKey != null && searchKey.trim().length() > 0) {
				Bundle b = new Bundle();
				b.putString("Search", searchKey.trim()); // Your id
				intent.putExtras(b);
			}

			startActivity(intent);
			progressDialog.cancel();
		}
	} // end CallAPI

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bundle b = getIntent().getExtras();
		alt_bld = new AlertDialog.Builder(this);
		if (b != null) {
			if (b.getString("Search").trim().length() > 0) {
				searchKey = b.getString("Search");
			}

		}
		internetPresent = isOnline();
		if (!internetPresent) {
			alt_bld.setMessage(Constants.CHECK_INTERNET).setPositiveButton(Constants.OK, null);
			AlertDialog alert = alt_bld.create();
			alert.show();

		} else {
			progressDialog = ProgressDialog.show(this, Constants.INITIALISE, Constants.LOADING, true);

		}
		new CallAPI().execute();
	}

	// Check if internet is available or not
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		} else if (keyCode == KeyEvent.KEYCODE_HOME) {
			finish();
		}

		return true;
	}
}
