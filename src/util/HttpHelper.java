package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {
	private static final String TAG = "Http";
	public static boolean LOG_ON = false;

	public static String doGet(String url, String charset) throws IOException {

		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setRequestMethod("GET");
		//conn.setDoOutput(true);
		//conn.setDoInput(true);
		conn.connect();

		InputStream in = conn.getInputStream();

		String s = IOUtils.toString(in, charset);

		in.close();
		conn.disconnect();
		return s;
	}

}
