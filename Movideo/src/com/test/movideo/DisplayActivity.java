package com.test.movideo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends Activity {
	ImageView img;
	Bitmap bitmap;
	ProgressDialog pDialog;
	ArrayList<XmlValuesModel> arrayMovideolist = null;
	String hintText = "Search KeyWord";
	ProgressDialog progressDialog;
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_result);
			progressDialog = ProgressDialog.show(this, Constants.LOADING, Constants.PLEAS_WAIT, true);
			
			ScrollView sv = new ScrollView(this);
			LinearLayout ll = new LinearLayout(this);

			ll.setOrientation(LinearLayout.VERTICAL);
			sv.addView(ll);

			int sdk = android.os.Build.VERSION.SDK_INT;
			if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				ll.setBackgroundDrawable(getResources().getDrawable(R.drawable.sky));
			} else {
				ll.setBackground(getResources().getDrawable(R.drawable.sky));
			}

			arrayMovideolist = (ArrayList<XmlValuesModel>) getIntent().getSerializableExtra("FILES_TO_SEND");

			for (int i = 0; i < arrayMovideolist.size(); i++) {
				TextView tv = new TextView(this);
				if (i % 2 == 0) {
					tv.setTextColor(Color.BLACK);
				} else {
					tv.setTextColor(Color.MAGENTA);
				}
				String todisplay = "SL# " +( i + 1) + " : " + arrayMovideolist.get(i).getTitle();
				tv.setText(todisplay);
				tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
			
				ll.addView(tv);

				tv = new TextView(this);
				if (i % 2 == 0) {
					tv.setTextColor(Color.BLACK);
				} else {
					tv.setTextColor(Color.MAGENTA);
				}
				todisplay = "Play Time:" + arrayMovideolist.get(i).getDuration() + " Type:"
						+ arrayMovideolist.get(i).getMediaType();
				tv.setText(todisplay);
				tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
				ll.addView(tv);

				tv = new TextView(this);
				if (i % 2 == 0) {
					tv.setTextColor(Color.BLACK);
				} else {
					tv.setTextColor(Color.MAGENTA);
				}
				todisplay = "Popularity(Day/Week/Month/Total):" + arrayMovideolist.get(i).getMediaPlaysday() + "/"
						+ arrayMovideolist.get(i).getMediaPlaysweek() + "/"
						+ arrayMovideolist.get(i).getMediaPlaysmonth() + "/"
						+ arrayMovideolist.get(i).getMediaPlaystotal();

				tv.setText(todisplay);
				tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
				ll.addView(tv);

				tv = new TextView(this);
				todisplay = "------------------------------------------";
				tv.setText(todisplay);
				tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
				ll.addView(tv);

			}

			final EditText txtSearch = new EditText(this);

			txtSearch.setHint(hintText);
			txtSearch.setHintTextColor(Color.RED);
			ll.addView(txtSearch);

			Button btn = new Button(this);
			btn.setId(arrayMovideolist.size());

			btn.setText("Search");
			btn.setBackgroundColor(Color.rgb(70, 80, 90));
			ll.addView(btn);

			btn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					String searchWord = txtSearch.getText().toString();
					if (searchWord != null && searchWord.trim().length() > 0 && searchWord.equals(hintText) == false) {
						Intent intent = new Intent();
						Bundle b = new Bundle();
						b.putString("Search", searchWord);
						intent.putExtras(b);
						intent.setClass(view.getContext(), MainActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(view.getContext(), Constants.KEYWORD_SEARCH, Toast.LENGTH_SHORT)
								.show();
					}
				}
			});
			this.setContentView(sv);
			progressDialog.cancel();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		} else if (keyCode == KeyEvent.KEYCODE_HOME) {
			finish();
		}

		return true;
	}
}
