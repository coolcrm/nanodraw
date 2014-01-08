package com.vladimir.karagioz.nanodraw;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class SettingsActivity extends Activity {

	EditText mEditRed; //Access to field with values
	EditText mEditGreen;
	EditText mEditBlue;
	int colorCode; //Resulting Color Code
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void selfDestruct(View view) {
	     // Finish the activity
		finish();
	 }
	public void saveColor(View view) {
		Intent resultIntent = new Intent();
		// Access values
		mEditRed   = (EditText)findViewById(R.id.editRed);
		mEditGreen   = (EditText)findViewById(R.id.editGreen);
		mEditBlue   = (EditText)findViewById(R.id.editBlues);
		int r, g, b;
		r = Integer.valueOf(mEditRed.getText().toString());
		g = Integer.valueOf(mEditGreen.getText().toString());
		b = Integer.valueOf(mEditBlue.getText().toString());
		colorCode = Color.rgb(r, g, b);
		resultIntent.putExtra("drawColor", colorCode); 
		setResult(Activity.RESULT_OK, resultIntent);
		Log.v("Color", "Stun for OK Button Pressed in color dialog");
	     // Finish the activity
		finish();
	 }
}
