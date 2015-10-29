package structualSpec.collect.featureLocation.ir;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import structualSpec.collect.featureLocation.QueryTermSubscriber;
import structualSpec.config.ConfigUtility;

public class LSIExtractor {
	private HashMap<String, Integer> totalTerms;
	private List<HashMap<String, Integer>> matrix;

	public StringBuilder getSimilarMtds(StringBuilder allMethods) {
		String[] methods = allMethods.toString().split("\\$\\$");
		double[] scores = buildTermCorpus(methods);
		StringBuilder extracted = new StringBuilder();
		double threshold = ConfigUtility.lsiSimilarity;
		if (scores == null)
			return extracted;
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] > threshold)
				extracted.append(methods[i] + "$$");
		}
		return extracted;
	}

	private double[] buildTermCorpus(String[] methods) {
		totalTerms = new HashMap<String, Integer>();
		matrix = new ArrayList<HashMap<String, Integer>>();
		for (String method : methods) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			String[] terms = method.trim().split(" ");
			for (String term : terms)
				addTerm(map, term);
			totalTerms.putAll(map);
			matrix.add(map);
		}
		return matchQuery();
	}

	private double[] matchQuery() {
		String[] terms = QueryTermSubscriber.getInstance().getTerms();
		HashSet<String> included = new HashSet<String>();
		for (String term : terms) {
			term = term.toLowerCase().trim();
			if (totalTerms.containsKey(term))
				included.add(term);
		}
		if (included.size() == 0)
			return null;
		int size = matrix.size();
		double[] scoreVector = new double[size];
		for (int i = 0; i < size; i++) {
			HashMap<String, Integer> vector = matrix.get(i);
			double lsiScore = 0;
			for (String term : included) {
				term = term.toLowerCase().trim();
				if (!totalTerms.containsKey(term))
					continue;
				lsiScore += vector.containsKey(term) ? vector.get(term) * 1.00
						/ totalTerms.get(term) : 0;
			}
			scoreVector[i] = lsiScore;
		}
		return scoreVector;
	}

	private void addTerm(HashMap<String, Integer> map, String term) {
		if (term.length() == 0)
			return;
		int count = map.containsKey(term) ? map.get(term) : 0;
		map.put(term, count + 1);
	}

	
}
