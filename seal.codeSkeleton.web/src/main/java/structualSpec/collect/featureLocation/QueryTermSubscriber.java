package structualSpec.collect.featureLocation;

import java.util.Arrays;
import java.util.HashSet;

public class QueryTermSubscriber {
	public String[] terms;

	private static QueryTermSubscriber  subscriber = new QueryTermSubscriber ();
	private QueryTermSubscriber(){}
	public static QueryTermSubscriber  getInstance() {
		return subscriber;
	}
	
	public void setQueryTerms(String query) {
		HashSet<String> set = new HashSet<String>(Arrays.asList(query.trim()
				.split(" ")));
		set.remove("");
		terms = set.toArray(new String[set.size()]);
	}

	public String[] getTerms() {
		return terms;
	}
}
