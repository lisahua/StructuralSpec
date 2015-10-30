package codeSkeleton.core.collect.query;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import codeSkeleton.core.config.ConfigUtility;
import codeSkeleton.core.lexical.preprocessing.QueryStemmer;

public class WebContentCollector {
	// private static WebContentCollector controller = new
	// WebContentCollector();
	//
	// public static WebContentCollector getInstance() {
	// return controller;
	// }

	private static JsonQueryResult sendQuery(String query) {
		URL url;
		try {
			url = new URL(query);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream(), ConfigUtility.charset));
		
			return JsonUnMarshaller.getInstance().readJsonQueryResult(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static JsonQueryResult sendQuery(String... query) {
		String address = ConfigUtility.queryAPI ;
		for (String s : query)
			address += s;
		return sendQuery(address);

	}

	public static JsonQueryResult[] queryForAllResults(String query) {
		query = QueryStemmer.stemming(query);
		JsonQueryResult[] results = null;
		JsonQueryResult first = sendQuery(query,ConfigUtility.language);
		int totalPage = first.getTotal() / ConfigUtility.pageSize;
		results = new JsonQueryResult[totalPage + 1];
		results[0]=first;
		for (int i = 1; i < totalPage; i++)
			results[i] = sendQuery(query,ConfigUtility.language, "&p=" + i);
		return results;
	}
}
