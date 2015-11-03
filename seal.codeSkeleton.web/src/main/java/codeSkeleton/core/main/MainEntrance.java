package codeSkeleton.core.main;

import java.util.List;

import codeSkeleton.core.collect.IRMainController;
import codeSkeleton.core.collect.query.JsonQueryResult;
import codeSkeleton.core.collect.query.SourceCodeCollector;
import codeSkeleton.core.collect.query.WebContentCollector;
import codeSkeleton.core.demo.Demo1;
import codeSkeleton.core.structural.StructuralMainController;

public class MainEntrance {

	public static void run(String query) {
		JsonQueryResult[] result = WebContentCollector
				.queryForAllResults(query);
		SourceCodeCollector.getStringsFromQuery(result);
		// IRMainController.run();
		StructuralMainController.run();
		
	}

	public static String[] getIRTopics(int numWords) {
		return IRMainController.getTopics(numWords);
//		return new Demo1().printString();
	}

	public static String[] getIRFiles(int topicNum, int showNum) {
		return IRMainController.getFiles(topicNum, showNum);
	}

	public static String[][] getStructuralCode() {
		List<List<String>> examples= StructuralMainController.getSourceCode();
		String[][] display= new String[2][examples.size()];
		for (int i=0;i< examples.size();i++) {
			List<String> example = examples.get(i);
			if (example.size()>1) {
				display[0][i] =example.get(0);
				display[1][i] = example.get(1);
			}
		}
		return display;
	}
}
