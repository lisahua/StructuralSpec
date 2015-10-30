package codeSkeleton.core.collect.ir;

import java.util.Queue;


public class IRExtractor {

	public static void setStrategy(IRExtractorStrategy strategy) {
		strategy.traverseToIRFile(strategy.retrieveInputFiles());
	}
	
	
	public static StringBuilder[] retrieveAllExamples() {
		return new IRExtractorMethodStrategy().retrieveAllIRInfo();
	}
}
