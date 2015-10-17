package search.github;

import org.testng.annotations.Test;

import structualSpec.collect.partial.JsonQueryResult;
import structualSpec.collect.partial.WebContentCollector;

public class TestJsonResult {
	//@Test
	public  void testQuery() {
		WebContentCollector.getInstance().sendQuery("undo+redo+TextEditor");
	}
	@Test
	public void testSourceCode() {
		JsonQueryResult result = (JsonQueryResult) WebContentCollector.getInstance().sendQuery("undo+redo+TextEditor");
		
	}
}
