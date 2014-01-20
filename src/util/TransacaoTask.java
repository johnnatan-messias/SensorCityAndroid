package util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TransacaoTask extends AsyncTask<Void, Void, Boolean> {
	private static String CATEGORIA = "TransacaoTask";
	private final Context context;
	private final Transacao transacao;
	private ProgressDialog progressDialog;
	private Throwable exceptionError;
	private int aguardeMsg;

	public TransacaoTask(Context context, Transacao transacao, int aguardeMsg) {
		this.context = context;
		this.transacao = transacao;
		this.aguardeMsg = aguardeMsg;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		abrirProgressDialog();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			transacao.executar();
		} catch (Throwable e) {
			Log.e(CATEGORIA, e.getMessage(), e);
			this.exceptionError = e;
			return false;
		} finally {
			try {
				fecharProgressDialog();
			} catch (Exception e) {
				Log.e(CATEGORIA, e.getMessage(), e);
			}
		}

		return true;
	}

	@Override
	protected void onPostExecute(Boolean ok) {
		if (ok) {
			transacao.atualizarView();
		} else {
			AndroidUtils.alertDialog(context,
					"Erro: " + exceptionError.getMessage(),
					android.R.drawable.alert_dark_frame);
		}
	}

	public void abrirProgressDialog() {
		try {
			progressDialog = ProgressDialog.show(context, "",
					context.getString(aguardeMsg));

		} catch (Throwable e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
	}

	public void fecharProgressDialog() {
		try {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
		} catch (Throwable e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
	}
}
