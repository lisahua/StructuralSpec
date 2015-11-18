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
		long start = System.currentTimeMillis();
		JsonQueryResult[] result = WebContentCollector
				.queryForAllResults(query);
		System.out.println((System.currentTimeMillis() - start)*1.0 / 1000);
		start = System.currentTimeMillis();
		SourceCodeCollector.getStringsFromQuery(result);
		System.out.println((System.currentTimeMillis() - start)*1.0 / 1000);
//		start = System.currentTimeMillis();
//		IRMainController.run();
//		System.out.println((System.currentTimeMillis() - start)*1.0 / 1000);
		start = System.currentTimeMillis();
		StructuralMainController.run();
		System.out.println((System.currentTimeMillis() - start)*1.0 / 1000);
	}

	public static String[] getIRTopics(int numWords) {
		return IRMainController.getTopics(numWords);
		// return new Demo1().printString();
	}

	public static String[] getIRFiles(int topicNum, int showNum) {
		return IRMainController.getFiles(topicNum, showNum);
	}

	public static String[][] getStructuralCode() {
		List<List<String>> examples = StructuralMainController.getSourceCode();
		String[][] display = new String[2][examples.size()];
		int count = 0;
		for (List<String> example : examples) {
			if (example.size() > 1) {
				display[0][count] = example.get(0);
				display[1][count] = example.get(1);
				count++;
			}
		}
		return display;
	}
}
