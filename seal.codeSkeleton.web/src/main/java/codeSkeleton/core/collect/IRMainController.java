package codeSkeleton.core.collect;

import java.util.ArrayList;
import java.util.List;

import codeSkeleton.core.collect.cluster.LDAExtractor;
import codeSkeleton.core.collect.cluster.LSIExtractor;
import codeSkeleton.core.collect.ir.IRExtractor;
import codeSkeleton.core.collect.query.JsonSourceCode;


public class IRMainController {
	public static void run() {
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
