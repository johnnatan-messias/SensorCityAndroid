package br.com.johnnatan;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import br.com.johnnatan.places.PlacesMapOsmDroidActivity;
import br.com.johnnatan.sensores.SensorListActivity;
import br.com.johnnatan.service.LuminiServiceConexao;
import br.com.johnnatan.service.LuminiServiceConexao.LuminiBinder;
import br.com.johnnatan.service.widget.LuminiServiceInterface;

public class SensoresActivity extends Activity implements ServiceConnection {

	private static String CATEGORIA = "Main";
	private LuminiServiceInterface sensores;
	final ServiceConnection conexao = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here
		setContentView(R.layout.main);

		Log.i(CATEGORIA, "Service Started - bindService");
		bindService(new Intent(this, LuminiServiceConexao.class), conexao,
				Context.BIND_AUTO_CREATE);

	}

	public void onClickMapSensors(View v) {
		startActivity(new Intent(this, PlacesMapOsmDroidActivity.class));

	}

	public void onClickSensorsList(View v) {
		startActivity(new Intent(this, SensorListActivity.class));

		/*
		 * Toast.makeText( this,
		 * String.valueOf(sensores.getSensores().get(0).getLongitude()) + " " +
		 * sensores.getSensores().get(1).getLongitude(),
		 * Toast.LENGTH_LONG).show();
		 */
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(CATEGORIA, "onStart()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(conexao);

		Log.i(CATEGORIA, "onDestroy()");
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		// TODO Auto-generated method stub
		LuminiBinder binder = (LuminiBinder) service;
		sensores = binder.getMySensores();

	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		sensores = null;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ui_main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case R.id.main_menu_about:
			startActivity(new Intent(this, AboutActivity.class));
			break;

		case R.id.main_menu_exit:
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
