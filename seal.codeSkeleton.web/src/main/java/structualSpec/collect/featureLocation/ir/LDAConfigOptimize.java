package structualSpec.collect.featureLocation.ir;

import java.io.IOException;

import structualSpec.config.ConfigUtility;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.types.InstanceList;

public class LDAConfigOptimize {

	public static ParallelTopicModel optimizeConfig(InstanceList instances)
			throws Exception {
		ParallelTopicModel model = optimizeConfigGA(instances, 1, 0.01);
		System.out.println("Topic " + model.getNumTopics() + " "
				+ model.modelLogLikelihood());
		return model;

	}

	private static ParallelTopicModel optimizeConfigGA(InstanceList instances,
			double alphaSum, double beta) throws Exception {
		int numTopics = ConfigUtility.max_topics;
		int iteration = ConfigUtility.max_iteration;
		int thread = ConfigUtility.lda_train_thread;
		ParallelTopicModel opModel = new ParallelTopicModel(2, alphaSum, beta);
		opModel.addInstances(instances);
		opModel.setNumIterations(50);
		opModel.setNumThreads(thread);
		opModel.estimate();
		double minProbability = Math.abs(opModel
				.modelLogLikelihood() / opModel.totalTokens);
		for (int i = 2; i < numTopics; i++) {
			for (int j = 100; j < iteration; j += 100) {
				ParallelTopicModel model = new ParallelTopicModel(i, alphaSum,
						beta);
				model.addInstances(instances);
				model.setNumIterations(j);
				model.setNumThreads(thread);
				model.estimate();
				if (Math.abs(minProbability) > Math.abs(model
						.modelLogLikelihood() / model.totalTokens)) {
					opModel = model;
					minProbability = Math.abs(model
							.modelLogLikelihood() / model.totalTokens);
				}
			}
		}
		return opModel;
	}
	public static ParallelTopicModel dummyOptimizeConfig(InstanceList instances) throws IOException {
		ParallelTopicModel opModel = new ParallelTopicModel(7, 1,0.1);
		opModel.addInstances(instances);
		opModel.setNumIterations(50);
		opModel.setNumThreads(2);
		opModel.estimate();
		return opModel;
	}
}
