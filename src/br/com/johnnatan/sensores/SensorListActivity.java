package br.com.johnnatan.sensores;

import java.util.List;

import util.Transacao;
import util.TransacaoActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import br.com.johnnatan.AboutSensorsActivity;
import br.com.johnnatan.R;
import br.com.johnnatan.WebService;
import br.com.johnnatan.sensores.util.SensorsAdapter;
import br.com.johnnatan.sensores.widget.SensoresItem;

public class SensorListActivity extends TransacaoActivity implements
		OnItemClickListener, Transacao {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */

	private ListView listView;
	private List<SensoresItem> sensores;
	private TextView titleHeader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_main_sensor_list);

		titleHeader = (TextView) findViewById(R.id.tHeader);
		titleHeader.setText(R.string.title_sensors_list);

		listView = (ListView) findViewById(R.id.sensor_listview);
		listView.setOnItemClickListener(this);
		startTransacao(this);
	}

	@Override
	public void executar() throws Exception {
		this.sensores = WebService.getSensors();

	}

	@Override
	public void atualizarView() {
		if (sensores != null) {
			listView.setAdapter(new SensorsAdapter(this, sensores));
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int posicao,
			long id) {
		SensoresItem sensor = (SensoresItem) parent.getAdapter().getItem(
				posicao);
		Intent intent = new Intent(this, AboutSensorsActivity.class);
		intent.putExtra(SensoresItem.KEY, sensor);
		startActivity(intent);
	}
}
