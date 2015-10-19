package structualSpec.collect.partial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import structualSpec.config.ConfigUtility;

public class SourceCodeCollector  {
//	private static SourceCodeCollector collector = new SourceCodeCollector();
//
//	public static SourceCodeCollector getInstance() {
//		return collector;
//	}

	public static Object sendQuery(String query) {
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

	public static void writeDownFile(String query, String fileName) {
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

	public static void writeDownQueryResult(JsonQueryResult result) {
		try {
			FileUtils.cleanDirectory(new File(ConfigUtility.codeOutputPath));
		} catch (IOException e) {
		}
		ResultItem[] resultTerms = result.getResults();
		HashMap<String, ResultItem> map = new HashMap<String, ResultItem>();

		for (int i = 0; i < resultTerms.length; i++) {
			map.put(resultTerms[i].getFilename(), resultTerms[i]);
		}
		int count = 0;
		for (ResultItem item : map.values()) {
			if (count > ConfigUtility.NUM_RESULT)
				break;
			writeDownFile(String.valueOf(item.getId()), item.getFilename());
			count++;
		}
	}

	public static String[] getStringsFromQuery(JsonQueryResult result) {
		ResultItem[] resultTerms = result.getResults();
		HashMap<String, ResultItem> map = new HashMap<String, ResultItem>();
		String[] codes = new String[ConfigUtility.NUM_RESULT];
		for (int i = 0; i < resultTerms.length; i++) {
			map.put(resultTerms[i].getFilename(), resultTerms[i]);
		}
		int count = 0;
		for (ResultItem item : map.values()) {
			if (count > ConfigUtility.NUM_RESULT)
				break;
			
			JsonSourceCode code = (JsonSourceCode) sendQuery(String
					.valueOf(item.getId()));
			codes[count]=code.getCode();
			count++;
		}
		return codes;
	}
}
