package codeSkeleton.core.collect.query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;

import codeSkeleton.core.collect.IRInformationModel;
import codeSkeleton.core.config.ConfigUtility;

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

	public static void writeDownFile(JsonSourceCode code, int count) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(ConfigUtility.codeOutputPath + count + "_"
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
		List<JsonSourceCode> codeQueue = filterIdenticalFiles(result);
		int count = 0;
		for (JsonSourceCode code : codeQueue)
			writeDownFile(code, count++);
	}

	public static void getStringsFromQuery(JsonQueryResult[] result) {
		List<JsonSourceCode> queue = filterIdenticalFiles(result);
		for (JsonSourceCode item : queue) {
			JsonCode code = sendQuery(String.valueOf(item.getId()));
			item.setCode(code.getCode());
		}
		IRInformationModel.getInstance().setFileInfo(queue);
	}

	public static List<JsonSourceCode> filterIdenticalFiles(
			JsonQueryResult[] result) {
		HashSet<String> nameSet = new HashSet<String>();
		List<JsonSourceCode> itemQueue = new ArrayList<JsonSourceCode>();
		int totalLOC = 0;
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
				totalLOC += item.getLinescount();
			}
		}
		if (itemQueue.size() > 0)
			// System.out.println("  & " + result[0].getTotal() + " & "
			// + itemQueue.size() + "&" + totalLOC / itemQueue.size()
			// + "&" + WebQueryTimer.end());
			System.out
					.println("  & " + +itemQueue.size() + "&"
							+ WebQueryTimer.end() + "&" + totalLOC
							/ itemQueue.size() + " & 7 &" + totalLOC
							/ itemQueue.size() / 10 + " & 7 \\\\");
		return itemQueue;
	}
}
