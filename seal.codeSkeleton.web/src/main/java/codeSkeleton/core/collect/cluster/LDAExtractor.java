package codeSkeleton.core.collect.cluster;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;

import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.CharSequenceLowercase;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.iterator.CsvIterator;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.types.Alphabet;
import cc.mallet.types.IDSorter;
import cc.mallet.types.InstanceList;
import codeSkeleton.core.collect.IRInformationModel;
import codeSkeleton.core.collect.query.JsonSourceCode;

public class LDAExtractor {
	private static int numTopics = 5;
	private static ParallelTopicModel model;
	private static Alphabet dataAlphabet;
	private static List<JsonSourceCode> files = null;
	private static int len = 0;
	private static String ldaInput = "lda.csv";
	private static int[][] topicDoc = null;
	private static double[][] ldaMatrix = null;
private static	String[] topicWord =null;
	public static int[][] extractTopics(StringBuilder[] corpus)
			throws Exception {
		InstanceList instances = initInstances(corpus);
		// model = LDAConfigOptimize.optimizeConfig(instances);
		model = LDAConfigOptimize.dummyOptimizeConfig(instances);
		numTopics = model.numTopics;
		// model.addInstances(instances);
		// optimizeLDAModel();
		dataAlphabet = instances.getDataAlphabet();
		len = model.getData().size();
//		returnTopicWords(7);
		return getTopics(getLDAMatrix());
	}

	private static void prepareCSVInput(StringBuilder[] corpus) {

		try {
			// files = new File(ConfigUtility.codeOutputPath).listFiles();
			files = IRInformationModel.getInstance().getFileInfo();

			PrintWriter writer = new PrintWriter(ldaInput);
			for (int i = 0; i < corpus.length; i++) {
				StringBuilder builder = corpus[i];
				writer.println(builder.toString() + ",,"
						+ files.get(i).getFileName() + "," + builder.toString());
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double[][] getLDAMatrix() {
		if (ldaMatrix != null)
			return ldaMatrix;

		double[][] ldaMatrix = new double[len][numTopics];
		for (int i = 0; i < len; i++) {
			double[] topicDistribution = model.getTopicProbabilities(i);
			ldaMatrix[i] = topicDistribution;
		}
		// fancyPrint(ldaMatrix);
		// int[] docPerTopic = getTopicDocuments(ldaMatrix);
		int[][] topicForDoc = getTopics(ldaMatrix);

		for (int t = 0; t < numTopics; t++) {
			String allFiles = "";
			int count = 0;
			for (int i = 0; i < len - 1; i++) {
				if (topicForDoc[t][i] == 0 && topicForDoc[t][i + 1] == 0)
					break;
				allFiles += files.get(i).getFileName() + " ";
				count++;
			}
			
		}
		return ldaMatrix;
	}

	private static void fancyPrint(double[][] matrix) {
		// Formatter out = new Formatter(new StringBuilder(), Locale.US);
		int len = model.getData().size();

		for (int i = 0; i < len; i++) {
			double max = 0, max2 = 0, maxIndex = 0, max2Index = 0;
			for (int j = 0; j < numTopics; j++) {
				if (matrix[i][j] > max) {
					max2 = max;
					max = matrix[i][j];
					maxIndex = j;
				} else {
					if (matrix[i][j] > max2) {
						max2 = matrix[i][j];
						max2Index = j;
					}
				}
			}
			System.out.println(files.get(i).getFileName() + ":" + maxIndex
					+ "(" + max + ") " + max2Index + "(" + max2 + ")");
		}
	}

	public static String[] returnTopicWords(int numWords) {
		if (topicWord!=null) return topicWord;
		ArrayList<TreeSet<IDSorter>> topicSortedWords = model.getSortedWords();
		 topicWord = new String[numTopics];
		for (int topic = 0; topic < numTopics; topic++) {
			Iterator<IDSorter> iterator = topicSortedWords.get(topic)
					.iterator();
			int rank = 0;
			String topics = "";
			while (iterator.hasNext() && rank++ < numWords) {
				IDSorter idCountPair = iterator.next();
				topics += dataAlphabet.lookupObject(idCountPair.getID()) + " ";
			}
			topicWord[topic] = topics;
		}
		return topicWord;
	}

	private static InstanceList initInstances(StringBuilder[] corpus)
			throws Exception {
		prepareCSVInput(corpus);
		// Begin by importing documents from text to feature sequences
		ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
		// Pipes: lowercase, tokenize, remove stopwords, map to features
		pipeList.add(new CharSequenceLowercase());
		pipeList.add(new CharSequence2TokenSequence(Pattern
				.compile("\\p{L}[\\p{L}\\p{P}]+\\p{L}")));
		pipeList.add(new TokenSequence2FeatureSequence());
		InstanceList instances = new InstanceList(new SerialPipes(pipeList));
		Reader fileReader = new InputStreamReader(new FileInputStream(new File(
				ldaInput)), "UTF-8");
		instances.addThruPipe(new CsvIterator(fileReader, Pattern
				.compile("^(\\S*)[\\s,]*(\\S*)[\\s,]*(.*)$"), 3, 2, 1));
		return instances;
	}

	public static int[] getTopicDocuments(double[][] matrix) {
		int[] fileTopics = new int[len];
		for (int i = 0; i < len; i++) {
			int maxIndex = 0;
			for (int t = 0; t < numTopics; t++) {
				if (matrix[i][t] > matrix[i][maxIndex])
					maxIndex = t;
			}
			fileTopics[i] = maxIndex;
		}
		return fileTopics;
	}

	public static int[][] getTopics(double[][] matrix) {
		if (topicDoc != null)
			return topicDoc;
		topicDoc = new int[numTopics][len];
		int[] fileTopics = getTopicDocuments(matrix);
		int[] lastId = new int[numTopics];
		for (int i = 0; i < fileTopics.length; i++) {
			int topic = fileTopics[i];
			topicDoc[topic][lastId[topic]] = i;
			lastId[topic] = lastId[topic] + 1;
		}
		for (int i=0;i<numTopics;i++) {
			System.out.print("Topic "+i+": ");
			for (int j=0;j<lastId[i];j++) {
				System.out.print(files.get(topicDoc[i][j]).getFileName());
			}
			System.out.println();
		}
		return topicDoc;
	}

	public static  int[] getTopicFiles(int topicNum) {
		return topicDoc[topicNum];

	}

}
