package structualSpec.collect.partial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import structualSpec.config.ConfigUtility;

public class SourceCodeCollector implements IWebContentCollector {
	private static SourceCodeCollector collector = new SourceCodeCollector();

	public static SourceCodeCollector getInstance() {
		return collector;
	}

	public Object sendQuery(String query) {
		String address = ConfigUtility.codeQueryAPI + query;

		URL url;
		try {
			url = new URL(address
					+ URLEncoder.encode(query, ConfigUtility.charset));
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream(), ConfigUtility.charset));

			return JsonUnMarshaller.getInstance().readJsonQueryResult(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
