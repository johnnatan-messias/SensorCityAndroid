package br.com.johnnatan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	
	private TextView titleHeader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_about);
		titleHeader = (TextView) findViewById(R.id.tHeader);
		titleHeader.setText(R.string.title_about_sensors);
	}
}
