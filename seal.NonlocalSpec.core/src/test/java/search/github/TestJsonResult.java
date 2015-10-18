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
		JsonQueryResult result = (JsonQueryResult) WebContentCollector
				.getInstance().sendQuery("undo+redo+TextEditor");
		SourceCodeCollector.getInstance().writeDownQueryResult(result);
	}
}
