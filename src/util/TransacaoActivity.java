package util;

import android.app.Activity;
import android.os.Bundle;
import br.com.johnnatan.R;

public class TransacaoActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void startTransacao(Transacao transacao) {
		if (AndroidUtils.isNetworkAvailable(this)) {
			TransacaoTask transacaoTask = new TransacaoTask(this, transacao,
					R.string.wait);
			transacaoTask.execute();
		} else {
			AndroidUtils.alertDialog(this, this.getString(R.string.error),
					android.R.drawable.presence_offline);
		}
	}
}
