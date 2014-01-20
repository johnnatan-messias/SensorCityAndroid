package br.com.johnnatan.service;

import java.io.IOException;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import br.com.johnnatan.WebService;
import br.com.johnnatan.sensores.widget.SensoresItem;

public class LuminiService extends Service implements Runnable {
	/**
	 * @see android.app.Service#onBind(Intent)
	 */
	private static String CATEGORIA = "SensorCity";
	private boolean ativo;
	protected List<SensoresItem> sensores;
	private long time = 100000;

	@Override
	public void onCreate() {
		super.onCreate();
		ativo = true;

		new Thread(this).start();

	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i(CATEGORIA, "onStart");

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void run() {
		while (ativo) {
			try {
				sensores = WebService.getSensors();

				Log.i(CATEGORIA, "Service OK");

			} catch (IOException e) {
				Log.e(CATEGORIA, e.getMessage(), e);
			}
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				Log.e(CATEGORIA, e.getMessage(), e);
			}

		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ativo = false;
		Log.i(CATEGORIA, "onDestroy");
	}
}
