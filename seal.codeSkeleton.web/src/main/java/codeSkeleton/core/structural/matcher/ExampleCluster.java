package codeSkeleton.core.structural.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ExampleCluster {

	public static int isSameclusterMethods(CodeExampleModel model1,
			CodeExampleModel model2) {
		int count = 0;
		for (String fact1 : model1.getFactString()) {
			for (String fact2 : model2.getFactString()) {
//				HashSet<String> facts1 = new HashSet<String>(Arrays.asList(fact1.split(",")));facts1.remove("");
//				String[] tokens = fact2.split(",");
//				for (String token: tokens) {
//					if (token.length()>0&& facts1.contains(token))
//						count++;
//				}
				if (fact1.equals(fact2)) 
				count++;
			}
				
		}
		return count;
	}

	public static int[] setClusterNumber(ArrayList<CodeExampleModel> examples) {
		int len = examples.size();
		int clusterNum = 1;
		int[] cExamples = new int[examples.size()];
		for (int i = 0; i < len - 1; i++) {
			if (examples.get(i).getClusterNum() > 0)
				continue;
			int max = 0, maxIndex = -1;
			for (int j = 0; j < len; j++) {
				if (i == j)
					continue;
				if (examples.get(j).getClusterNum() > 0)
					continue;
				int similar = isSameclusterMethods(examples.get(i),
						examples.get(j));
				if (similar > 1) {
					max = similar;
					maxIndex = j;
					break;
				}
			}
			int maxCNum = -1;
			if (maxIndex > 0 && maxIndex < len)
				maxCNum = examples.get(maxIndex).getClusterNum();
			if (maxCNum > 0) {
				examples.get(i).setClusterNum(maxCNum);
				cExamples[i] = maxCNum;
			} else {
				examples.get(i).setClusterNum(clusterNum);
				cExamples[i] = clusterNum;
				if (maxIndex > 0) {
					examples.get(maxIndex).setClusterNum(clusterNum);
					cExamples[maxIndex] = clusterNum;
				}
				clusterNum++;
			}
		}
		return cExamples;
	}
}
