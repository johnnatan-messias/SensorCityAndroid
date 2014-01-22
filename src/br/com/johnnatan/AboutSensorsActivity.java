package br.com.johnnatan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import br.com.johnnatan.sensores.widget.SensoresItem;

public class AboutSensorsActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	private TextView titleHeader;
	private SensoresItem sensor;
	private TextView temperature, luminosity, humidity, street, num,
			neighborhood, city, state, country;
	private TextView atmPressure, audio, latitude, longitude, nameSensor,
			timestamp, zip, ap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_about_sensors);

		titleHeader = (TextView) findViewById(R.id.tHeader);
		titleHeader.setText(R.string.title_about_sensors);

		sensor = (SensoresItem) getIntent().getSerializableExtra(
				SensoresItem.KEY);

		temperature = (TextView) findViewById(R.id.temperature);
		luminosity = (TextView) findViewById(R.id.luminosity);
		humidity = (TextView) findViewById(R.id.humidity);
		street = (TextView) findViewById(R.id.street);
		num = (TextView) findViewById(R.id.number);
		neighborhood = (TextView) findViewById(R.id.neighborhood);
		city = (TextView) findViewById(R.id.city);
		state = (TextView) findViewById(R.id.state);
		country = (TextView) findViewById(R.id.country);

		atmPressure = (TextView) findViewById(R.id.atmpressure);
		audio = (TextView) findViewById(R.id.audio);
		latitude = (TextView) findViewById(R.id.latitude);
		longitude = (TextView) findViewById(R.id.longitude);
		nameSensor = (TextView) findViewById(R.id.namesensor);
		timestamp = (TextView) findViewById(R.id.timestamp);
		zip = (TextView) findViewById(R.id.zip);
		ap = (TextView) findViewById(R.id.ap);

		temperature.setText(String.valueOf(sensor.getTemperature()));
		luminosity.setText(String.valueOf(sensor.getLuminosity()));
		humidity.setText(String.valueOf(sensor.getHumidity()));
		street.setText(sensor.getAddress().getStreet());
		num.setText(String.valueOf(sensor.getAddress().getNumber()));
		neighborhood.setText(sensor.getAddress().getState());
		city.setText(sensor.getAddress().getCity());
		state.setText(sensor.getAddress().getNeighborhood());
		country.setText(sensor.getAddress().getCountry());
		atmPressure.setText(String.valueOf(sensor.getAtmPressure()));
		audio.setText(String.valueOf(sensor.getAudio()));
		latitude.setText(String.valueOf(sensor.getLatitude()));
		longitude.setText(String.valueOf(sensor.getLongitude()));
		nameSensor.setText(sensor.getName());
		timestamp.setText(sensor.getTimestamp().toString());
		zip.setText(sensor.getAddress().getZip());
		ap.setText(sensor.getAddress().getAp());
	}
}
