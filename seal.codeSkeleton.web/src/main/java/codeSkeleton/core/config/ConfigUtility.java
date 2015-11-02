package codeSkeleton.core.config;

import java.util.Arrays;
import java.util.HashSet;

public class ConfigUtility {

	public static final int NUM_RESULT = 100;
	public static final String codeSearchPortal = "https://searchcode.com/api/";
	public static final String codeViewPortal = "https://searchcode.com/codesearch/view/";
	public static final String queryAPI = codeSearchPortal + "codesearch_I/?q=";
	public static final String codeQueryAPI = codeSearchPortal + "result/";
	public static final String charset = "UTF-8";
	public static final String language = "&lan=23";
	public static final int resultNumber = 10;
	public static final String testOutput = "src/test/java/resource/";
	public static final String codeOutputPath = testOutput + "tmp/";
	public static final String irOutputPath = testOutput + "ir/";
	public static final String ldaInputFile = testOutput + "lsi.csv";
	public static final String ldaStateFile = testOutput + "tempState.csv";
	public static final int pageSize = 20;

	public static final HashSet<String> winnowKW = new HashSet<String>(
			Arrays.asList("this", "null", "true", "false", "(", ")", "+",
					"void", "int", "boolean", "string", "float"));
	public static final int lsiSimilarity = 0;
	public static final int max_topics = 9;
	public static final int lda_train_thread = 2;
	public static final int max_iteration = 500;
	public static final int matchFacts = 3;
}
