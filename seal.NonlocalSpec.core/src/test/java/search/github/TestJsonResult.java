package search.github;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import structualSpec.collect.partial.JsonQueryResult;
import structualSpec.collect.partial.JsonSourceCode;
import structualSpec.collect.partial.ResultItem;
import structualSpec.collect.partial.SourceCodeCollector;
import structualSpec.collect.partial.WebContentCollector;
import structualSpec.config.ConfigUtility;

public class TestJsonResult {
	// @Test
	public void testQuery() {
		WebContentCollector.getInstance().sendQuery("undo+redo+TextEditor");
	}

	// @Test
	public void testParseSourceCode() {
		JsonQueryResult result = (JsonQueryResult) WebContentCollector
				.getInstance().sendQuery("undo+redo+TextEditor");
		ResultItem[] resultTerms = result.getResults();
		JsonSourceCode code = (JsonSourceCode) SourceCodeCollector
				.getInstance()
				.sendQuery(String.valueOf(resultTerms[0].getId()));
		System.out.println(code.getCode());
	}

	@Test
	public void testWriteCode() {
		try {
			FileUtils.cleanDirectory(new File(ConfigUtility.codeOutputPath));
		} catch (IOException e) {
		}
		JsonQueryResult result = (JsonQueryResult) WebContentCollector
				.getInstance().sendQuery("undo+redo+TextEditor");
		ResultItem[] resultTerms = result.getResults();
		HashMap<String, ResultItem> map = new HashMap<String, ResultItem>();

		for (int i = 0; i < resultTerms.length; i++) {

			map.put(resultTerms[i].getFilename(), resultTerms[i]);
		}
		int count = 0;
		for (ResultItem item : map.values()) {
			if (count > ConfigUtility.NUM_RESULT)
				break;
			SourceCodeCollector.getInstance().writeDownFile(
					String.valueOf(item.getId()), item.getFilename());
			count++;
		}
	}
}
