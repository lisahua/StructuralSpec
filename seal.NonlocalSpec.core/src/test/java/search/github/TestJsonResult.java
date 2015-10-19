package search.github;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import structualSpec.collect.featureLocation.CodeExampleModel;
import structualSpec.collect.partial.JsonQueryResult;
import structualSpec.collect.partial.JsonSourceCode;
import structualSpec.collect.partial.ResultItem;
import structualSpec.collect.partial.SourceCodeCollector;
import structualSpec.collect.partial.WebContentCollector;
import structualSpec.config.ConfigUtility;

public class TestJsonResult {
	// @Test
	public void testQuery() {
		WebContentCollector.sendQuery("undo+redo+TextEditor");
	}

	// @Test
	public void testParseSourceCode() {
		JsonQueryResult result = (JsonQueryResult) WebContentCollector
				.sendQuery("undo+redo+TextEditor");
		ResultItem[] resultTerms = result.getResults();
		JsonSourceCode code = (JsonSourceCode) SourceCodeCollector
				.sendQuery(String.valueOf(resultTerms[0].getId()));
		System.out.println(code.getCode());
	}

	// @Test
	public void testWriteCode() {
		JsonQueryResult result = (JsonQueryResult) WebContentCollector
				.sendQuery("undo+redo+TextEditor");
		SourceCodeCollector.writeDownQueryResult(result);
	}

	@Test
	public void testASTParser() {
		File[] dir = new File(ConfigUtility.codeOutputPath).listFiles();
		String code="";
		try {
			code = FileUtils.readFileToString(dir[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CodeExampleModel example = new CodeExampleModel(code);
		
	}
}
