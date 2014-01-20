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
	private TextView temperatura, luminosidade, umidade, rua, num, bairro,
			cidade, estado, pais, info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_about_sensors);

		titleHeader = (TextView) findViewById(R.id.tHeader);
		titleHeader.setText(R.string.title_about_sensors);

		sensor = (SensoresItem) getIntent().getSerializableExtra(
				SensoresItem.KEY);

		temperatura = (TextView) findViewById(R.id.temperatura);
		luminosidade = (TextView) findViewById(R.id.luminosidade);
		umidade = (TextView) findViewById(R.id.umidade);
		rua = (TextView) findViewById(R.id.street);
		num = (TextView) findViewById(R.id.number);
		bairro = (TextView) findViewById(R.id.neighborhood);
		cidade = (TextView) findViewById(R.id.city);
		estado = (TextView) findViewById(R.id.state);
		pais = (TextView) findViewById(R.id.country);
		info = (TextView) findViewById(R.id.information);

		temperatura.setText(String.valueOf(sensor.getTemperature()));
		luminosidade.setText(String.valueOf(sensor.getLuminosity()));
		umidade.setText(String.valueOf(sensor.getHumidity()));
		rua.setText(sensor.getAddress().getStreet());
		num.setText(String.valueOf(sensor.getAddress().getNumber()));
		bairro.setText(sensor.getAddress().getState());
		cidade.setText(sensor.getAddress().getCity());
		estado.setText(sensor.getAddress().getNeighborhood());
		pais.setText(sensor.getAddress().getCountry());
		info.setText(sensor.getInformation());

	}
}
