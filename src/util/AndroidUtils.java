package util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import br.com.johnnatan.R;

public class AndroidUtils {
	private static String CATEGORIA = "AndroidUtils";

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}

		}
		return false;
	}

	public static void toastMessage(final Context context, final int message) {
		Toast.makeText(context, context.getString(message), Toast.LENGTH_SHORT)
				.show();
	}

	public static void alertDialog(final Context context, final String message,
			final int resId) {
		try {
			AlertDialog dialog = new AlertDialog.Builder(context)
					.setTitle(context.getString(R.string.app_name))
					.setMessage(message).create();
			dialog.setIcon(resId);
			dialog.setButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					return;
				}
			});

			dialog.show();
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
	}

	public static void alertDialog(final Context context, final String message) {
		try {
			AlertDialog dialog = new AlertDialog.Builder(context)
					.setTitle(context.getString(R.string.app_name))
					.setMessage(message).create();
			dialog.setButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					return;
				}
			});

			dialog.show();
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
	}

}
