package codeSkeleton.core.structural;

import java.util.ArrayList;
import java.util.List;

import codeSkeleton.core.structural.matcher.StructuralMatcher;
import codeSkeleton.core.structural.output.CodeExampleLineMarker;

public class StructuralMainController {
	// private static int[] cClusters = null;
	private static int[][] clusters = null;
	private static int len = 0;
	private static List<List<String>> examples = new ArrayList<List<String>>();

	public static void run() {
		StructuralMatcher matcher = StructuralMatcher.getInstance();
		matcher.setExamples();
		// ArrayList<CodeExampleModel> examples = matcher.getExamples();
		int[] cClusters = matcher.clusterExample();
		len = cClusters.length;
		getCodeClusters(cClusters);
		getCodeExample();
		// CodeExampleHighLighter.getCodeExampleWithHighlight();
	}

	private static void getCodeExample() {

		for (int topic = 0; topic < len; topic++) {
			if (clusters[topic][0] == 0)
				break;
			List<String> cluster = new ArrayList<String>();
			for (int num = 0; num < len; num++) {
				if (clusters[topic][num] == 0)
					break;
			
				cluster.add(new CodeExampleLineMarker()
						.getCodeExampleMarkerString(clusters[topic][num] - 1));
			}
			examples.add(cluster);
		}
	}

	public static List<List<String>> getSourceCode() {
		return examples;
	}

	private static void getCodeClusters(int[] cClusters) {
		clusters = new int[len][len];
		int[] idRecord = new int[len];
		// for (int i = 0; i < len; i++)
		// idRecord[i] = 0;
		for (int i = 0; i < cClusters.length; i++) {
			int key = cClusters[i] - 1;
			if (key < 0)
				continue;
			clusters[key][idRecord[key]] = i + 1;
			idRecord[key]++;
		}
	}
}
