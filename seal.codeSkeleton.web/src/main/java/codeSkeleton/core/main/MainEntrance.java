package codeSkeleton.core.main;

import codeSkeleton.core.collect.IRMainController;
import codeSkeleton.core.collect.query.JsonQueryResult;
import codeSkeleton.core.collect.query.SourceCodeCollector;
import codeSkeleton.core.collect.query.WebContentCollector;

public class MainEntrance {

	public static void run(String query) {
		JsonQueryResult[] result = WebContentCollector
				.queryForAllResults(query);
		SourceCodeCollector.getStringsFromQuery(result);
		IRMainController.run();
	}

	public static String[] getIRTopics(int numWords) {
		return IRMainController.getTopics(numWords);
	}

	public static String[] getIRFiles(int topicNum, int showNum) {
		return IRMainController.getFiles(topicNum, showNum);
	}
	
	
}
