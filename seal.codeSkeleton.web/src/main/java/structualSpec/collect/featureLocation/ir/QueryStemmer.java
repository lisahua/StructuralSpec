package structualSpec.collect.featureLocation.ir;

import structualSpec.config.IRInformationModel;
import structualSpec.lexical.preprocessing.CamelCaseSplitter;

public class QueryStemmer {
	private static CamelCaseSplitter splitter = CamelCaseSplitter.getInstance();

	// TODO more works
	public static String stemming(String query) {
		String line = splitter.executeSingleLine(query);
		IRInformationModel.getInstance().setQueryTerms(line.split(" "));
		return query.replace(" ", "+");
	}
}
