package br.com.johnnatan.service;

import java.util.List;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import br.com.johnnatan.sensores.widget.SensoresItem;
import br.com.johnnatan.service.widget.LuminiServiceInterface;

public class LuminiServiceConexao extends LuminiService implements
		LuminiServiceInterface {
	/**
	 * @see android.app.Service#onBind(Intent)
	 */
	private final IBinder conexao = new LuminiBinder();

	public class LuminiBinder extends Binder {
		public LuminiServiceInterface getMySensores() {
			return LuminiServiceConexao.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return conexao;
	}

	@Override
	public List<SensoresItem> getSensores() {
		return sensores;
	}
}
