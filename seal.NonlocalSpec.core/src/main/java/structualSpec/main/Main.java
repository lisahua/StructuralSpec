package structualSpec.main;

import structualSpec.collect.partial.JsonQueryResult;
import structualSpec.collect.partial.SourceCodeCollector;
import structualSpec.collect.partial.WebContentCollector;

public class Main {

	public static void main(String[] args) {
		// Here is an example on how it works
		String userQuery = "track+mouse+hover";
		JsonQueryResult[] result =  WebContentCollector.queryForAllResults(userQuery);
		// writeDownQueryResult() is not required, but you may want to check
		// your results
		SourceCodeCollector.writeDownQueryResult(result);
//		StructuralMatcher matcher  = StructuralMatcher.getInstance();
//		matcher.setExamples(SourceCodeCollector.getStringsFromQuery(result));

	
	}
}
