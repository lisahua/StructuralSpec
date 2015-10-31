package codeSkeleton.core.structural;

import java.util.ArrayList;

import codeSkeleton.core.structural.matcher.CodeExampleModel;
import codeSkeleton.core.structural.matcher.StructuralMatcher;

public class StructuralMainController {
	// private static int[] cClusters = null;
	private static ArrayList<CodeExampleModel> examples;
	private static int[][] clusters = null;
private static int len = 0;
	public static void run() {
		StructuralMatcher matcher = StructuralMatcher.getInstance();
		matcher.setExamples();
		examples = matcher.getExamples();
		int[] cClusters = matcher.clusterExample();
		getCodeClusters(cClusters);
		len = cClusters.length;
		getCodeExample();
	}

	public static String[] getTopics() {
		return null;
	}

	public static void getCodeExample() {
		String[][] codeExample = new String[len][len];
		for (int topic=0;topic<len;topic++) {
			if (clusters[topic][0]==0) break;
			for (int num=0;num<len;num++) {
				if (clusters[topic][num]==0) break;
				codeExample[topic][num] = examples.get(clusters[topic][num]-1).getCodeExampleWithHighlight();
			}
		}
	}

	private static void getCodeClusters(int[] cClusters) {
		clusters = new int[len][len];
		int[] idRecord = new int[len];
		for (int i = 0; i < cClusters.length; i++) {
			int key = cClusters[i]-1;
			clusters[key][idRecord[key]] = i+1;
			idRecord[key]++;
		}
	}
}
