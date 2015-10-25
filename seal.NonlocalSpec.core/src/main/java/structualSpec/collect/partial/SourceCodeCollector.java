package structualSpec.collect.partial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.io.FileUtils;

import structualSpec.config.ConfigUtility;

public class SourceCodeCollector {
	// private static SourceCodeCollector collector = new SourceCodeCollector();
	//
	// public static SourceCodeCollector getInstance() {
	// return collector;
	// }

	public static JsonCode sendQuery(String query) {
		String address = ConfigUtility.codeQueryAPI + query + "/";
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

	public static void writeDownFile(JsonSourceCode code) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(ConfigUtility.codeOutputPath
					+ code.getFileName());
			JsonCode codeQResult = sendQuery(String.valueOf(code.getId()));
			if (codeQResult != null)
				writer.println(codeQResult.getCode());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeDownQueryResult(JsonQueryResult[] result) {
		try {
			FileUtils.cleanDirectory(new File(ConfigUtility.codeOutputPath));
		} catch (IOException e) {
		}
		Queue<JsonSourceCode> codeQueue = filterIdenticalFiles(result);
		for (JsonSourceCode code : codeQueue)
			writeDownFile(code);
	}

	public static Queue<String> getStringsFromQuery(JsonQueryResult[] result) {
		Queue<JsonSourceCode> queue = filterIdenticalFiles(result);
		Queue<String> resultString = new LinkedList<String>();
		for (JsonSourceCode item : queue) {
			JsonCode code = sendQuery(String.valueOf(item.getId()));
			resultString.add(code.getCode());
		}
		return resultString;
	}

	public static Queue<JsonSourceCode> filterIdenticalFiles(
			JsonQueryResult[] result) {
		HashSet<String> nameSet = new HashSet<String>();
		Queue<JsonSourceCode> itemQueue = new LinkedList<JsonSourceCode>();
		for (JsonQueryResult qRes : result) {
			if (qRes == null)
				continue;
			for (ResultItem item : qRes.getResults()) {
				if (nameSet.contains(item.getFilename()))
					continue;
				if (nameSet.size() >= ConfigUtility.NUM_RESULT)
					break;
				nameSet.add(item.getFilename());
				itemQueue.add(new JsonSourceCode(item.getId(), item
						.getFilename()));
			}
		}

		System.out.println("  & " + result[0].getTotal() + " & "
				+ itemQueue.size() + "&" + WebQueryTimer.end());
		return itemQueue;
	}

}
