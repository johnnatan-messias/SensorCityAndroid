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
	private static String CATEGORIA = "LuminiService";
	private boolean ativo;
	protected List<SensoresItem> sensores;
	private long time = 10000;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ativo = true;

		new Thread(this).start();

	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i(CATEGORIA, "onStart");

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Put your code here
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (ativo) {
			try {
				sensores = WebService.getSensors();

				Log.i(CATEGORIA, "Serviço OK");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(CATEGORIA, e.getMessage(), e);
			}
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Log.e(CATEGORIA, e.getMessage(), e);
			}

		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ativo = false;
		Log.i(CATEGORIA, "onDestroy");
	}
}
