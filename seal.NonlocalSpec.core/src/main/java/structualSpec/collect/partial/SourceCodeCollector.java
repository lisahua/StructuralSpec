package structualSpec.collect.partial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import structualSpec.config.ConfigUtility;

public class SourceCodeCollector implements IWebContentCollector {
	private static SourceCodeCollector collector = new SourceCodeCollector();

	public static SourceCodeCollector getInstance() {
		return collector;
	}

	public Object sendQuery(String query) {
		String address = ConfigUtility.codeQueryAPI + query + "/";
		System.out.println(address);
		URL url;
		try {
			url = new URL(address);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream(), ConfigUtility.charset));
			
			return JsonUnMarshaller.getInstance().readJsonSourceCode(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void writeDownFile(String query, String fileName) {
		JsonSourceCode code = (JsonSourceCode) sendQuery(query);
		PrintWriter writer;
		try {
			writer = new PrintWriter(ConfigUtility.codeOutputPath + fileName);
			writer.println(code.getCode());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
