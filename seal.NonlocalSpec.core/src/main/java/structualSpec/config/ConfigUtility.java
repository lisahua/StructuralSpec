package structualSpec.config;

public class ConfigUtility {

	public static final int NUM_RESULT=1000;
	public static final String codeSearchPortal = "https://searchcode.com/api/";
	public static final String queryAPI = codeSearchPortal+"codesearch_I/?q=";
	public static final String codeQueryAPI = codeSearchPortal+"result/";
	public static final String charset = "UTF-8";
	public static final String language = "&lan=23";
	public static final int resultNumber = 10;
	public static final String codeOutputPath = "test-output/resource/tmp/";
	public static final String irOutputPath = "test-output/resource/ir/";
	public static final String ldaInputFile = "test-output/resource/lda/lsi.csv";
	public static final String ldaStateFile = "test-output/resource/lda/tempState.csv";
	public static final int pageSize = 20;
	
	public static final String[] winnowKW = new String[] { "this", "null", "true", "false", "(", ")",
		"+","void","int","boolean","string","float" };
	
	public static final int lsiSimilarity = 0;
	public static final int max_topics = 9;
	public static final int lda_train_thread=2;
	public static final int max_iteration = 500;
}
