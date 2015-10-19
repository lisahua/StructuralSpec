package structualSpec.collect.featureLocation;

import java.util.ArrayList;

public class ExampleCluster {

	public static int isSameclusterMethods(CodeExampleModel model1,
			CodeExampleModel model2) {

		return 0;
	}

	public static void setClusterNumber(ArrayList<CodeExampleModel> examples) {
		int clusterNum = 1, maxCount = 0, maxSimilar = 0;
		for (int i = 1; i < examples.size(); i++) {
			CodeExampleModel single = examples.get(i);
			for (int j = 0; j < examples.size(); j++) {
				if (j == i)
					continue;
				CodeExampleModel second = examples.get(j);
				int score = isSameclusterMethods(single, second);
				if (score > maxCount) {
					maxCount = score;
					maxSimilar = j;
				}
			}
			CodeExampleModel second = examples.get(maxSimilar);
			if (second.getClusterNum() == 0) {
				second.setClusterNum(clusterNum);
				single.setClusterNum(clusterNum);
			} else
				single.setClusterNum(second.getClusterNum());
		}
	}
}
