package util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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

	public static String parser(String s) {
		String delims = "[-T:'.''+']+";
		String[] tokens = s.split(delims);

		// Find the Time Zone
		String gmtServer;
		if (s.indexOf('-') == -1)
			gmtServer = new StringBuffer().append("GMT-").append(tokens[7])
					.append(":").append(tokens[8]).toString();
		else
			gmtServer = new StringBuffer().append("GMT+").append(tokens[7])
					.append(":").append(tokens[8]).toString();

		// Store in the class GregorianCalendar to manager the timezone
		Calendar serverTime = new GregorianCalendar(
				TimeZone.getTimeZone(gmtServer));
		serverTime.set(Calendar.HOUR, Integer.parseInt(tokens[3]));
		serverTime.set(Calendar.MINUTE, Integer.parseInt(tokens[4]));
		serverTime.set(Calendar.SECOND, Integer.parseInt(tokens[5]));
		serverTime.set(Calendar.DATE, Integer.parseInt(tokens[2]));
		serverTime.set(Calendar.MONTH, Integer.parseInt(tokens[1]));
		serverTime.set(Calendar.YEAR, Integer.parseInt(tokens[0]));

		// Set AM or PM
		if (serverTime.get(Calendar.HOUR) < 12)
			serverTime.set(Calendar.AM_PM, Calendar.AM);
		else
			serverTime.set(Calendar.AM_PM, Calendar.PM);

		// Create a calendar object for representing a local time zone. Then we
		// wet the time of the calendar with the value of the local time

		Calendar localTime = Calendar.getInstance();
		localTime.setTimeInMillis(serverTime.getTimeInMillis());

		/*
		 * Legend int hour = localTime.get(Calendar.HOUR); int minute =
		 * localTime.get(Calendar.MINUTE); int second =
		 * localTime.get(Calendar.SECOND); int am_pm =
		 * localTime.get(Calendar.AM_PM); int day =
		 * localTime.get(Calendar.DATE); int month =
		 * localTime.get(Calendar.MONTH); int year =
		 * localTime.get(Calendar.YEAR);
		 */

		// Decimal format
		DecimalFormat nft = new DecimalFormat("#00.###");
		nft.setDecimalSeparatorAlwaysShown(false);
		// Use nft.format(var_int)

		// dd/mm/yyyy - HH:mm:ss
		String result = new StringBuffer()
				.append(nft.format(serverTime.get(Calendar.DATE))).append("/")
				.append(nft.format(serverTime.get(Calendar.MONTH))).append("/")
				.append(nft.format(serverTime.get(Calendar.YEAR)))
				.append(" - ")
				.append(nft.format(serverTime.get(Calendar.HOUR))).append(":")
				.append(nft.format(serverTime.get(Calendar.MINUTE)))
				.append(":")
				.append(nft.format(serverTime.get(Calendar.SECOND))).toString();

		return result;
	}

}
