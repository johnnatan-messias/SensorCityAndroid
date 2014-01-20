package br.com.johnnatan.sensores.util;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.johnnatan.R;
import br.com.johnnatan.sensores.widget.SensoresItem;

public class SensorsAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private final List<SensoresItem> sensores;
	private final Activity context;

	public SensorsAdapter(Activity context, List<SensoresItem> sensores) {
		this.context = context;
		this.sensores = sensores;
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sensores != null ? sensores.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return sensores != null ? sensores.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			int layout = R.layout.sensor_item;
			view = layoutInflater.inflate(layout, null);
			view.setTag(holder);
			holder.luminosity = (TextView) view
					.findViewById(R.id.luminosidade);
			holder.temperature = (TextView) view.findViewById(R.id.temperatura);
			holder.humidity = (TextView) view.findViewById(R.id.umidade);
			holder.city = (TextView) view.findViewById(R.id.city);

			/* Case eu inclua mais, modificar aqui */

		} else {
			holder = (ViewHolder) view.getTag();
		}
		SensoresItem sensor = sensores.get(position);
		holder.luminosity.setText(String.valueOf(sensor.getLuminosity()));
		holder.temperature.setText(String.valueOf(sensor.getTemperature()));
		holder.humidity.setText(String.valueOf(sensor.getHumidity()));
		holder.city.setText(sensor.getAddress().getCity());

		return view;
	}

	static class ViewHolder {
		TextView luminosity;
		TextView temperature;
		TextView humidity;
		TextView city;
	}

}
