package structualSpec.collect.partial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import structualSpec.collect.featureLocation.QueryTermSubscriber;
import structualSpec.config.ConfigUtility;

public class WebContentCollector {
//	private static WebContentCollector controller = new WebContentCollector();
//
//	public static WebContentCollector getInstance() {
//		return controller;
//	}

	public static Object sendQuery(String query) {

		// String query = "undo JHotdraw";
		String address = ConfigUtility.queryAPI + query + ConfigUtility.language;
		System.out.println(address);
		URL url;
		try {
			url = new URL(address);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream(), ConfigUtility.charset));
			QueryTermSubscriber.getInstance().setQueryTerms(query);
		return	JsonUnMarshaller.getInstance().readJsonQueryResult(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// }
}
