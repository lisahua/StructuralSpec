package structualSpec.main;

import java.util.ArrayList;
import java.util.List;

import structualSpec.collect.featureLocation.StructuralMatcher;
import structualSpec.collect.featureLocation.ir.IRExtractor;
import structualSpec.collect.featureLocation.ir.LDAExtractor;
import structualSpec.collect.featureLocation.ir.LSIExtractor;
import structualSpec.collect.partial.JsonQueryResult;
import structualSpec.collect.partial.JsonSourceCode;
import structualSpec.collect.partial.SourceCodeCollector;
import structualSpec.collect.partial.WebContentCollector;
import structualSpec.config.IRInformationModel;

public class MainEntrance {

	public static void run(String query) {
		JsonQueryResult[] result = WebContentCollector
				.queryForAllResults(query);
		SourceCodeCollector.getStringsFromQuery(result);
		StringBuilder[] irCorpus = IRExtractor.retrieveAllExamples();
		LSIExtractor lsiExtractor = new LSIExtractor();
		int len = irCorpus.length;
		StringBuilder[] extracted = new StringBuilder[len];
		for (int i = 0; i < len; i++) {
			extracted[i] = lsiExtractor.getSimilarMtds(irCorpus[i]);
		}
		try {
			LDAExtractor.extractTopics(extracted);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[] getTopics(int numWords) {
		return LDAExtractor.returnTopicWords(numWords);
	}

	public static String[] getFiles(int topicNum, int showNum) {
		int[] files = LDAExtractor.getTopicFiles(topicNum);
		List<JsonSourceCode> corpus = IRInformationModel.getInstance()
				.getFileInfo();
		// StructuralMatcher matcher = StructuralMatcher.getInstance();
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < showNum && i < files.length; i++) {
			// System.out.println(corpus.get(i).getCode());
			// matcher.addExample(corpus.get(i).getCode());
			result.add(corpus.get(files[i]).getCode());
		}
		// matcher.setClusterNumber();
		return result.toArray(new String[result.size()]);
	}
}
