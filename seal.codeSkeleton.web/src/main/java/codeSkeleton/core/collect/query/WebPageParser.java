package codeSkeleton.core.collect.query;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebPageParser {
	private static WebPageParser pageParser = new WebPageParser();

	public static WebPageParser getInstance() {
		return pageParser;
	}

	private WebPageParser() {

	}

	public BufferedReader getContent(String urlName) {
		BufferedReader br = null;
		try {
			// get URL content
			URL url = new URL(urlName);
			URLConnection conn = url.openConnection();
			// open the stream and put it into BufferedReader
			br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (Exception e) {

		}
		return br;
	}
}
