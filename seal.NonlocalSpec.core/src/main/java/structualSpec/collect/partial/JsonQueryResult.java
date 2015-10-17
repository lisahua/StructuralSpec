package structualSpec.collect.partial;

import java.util.Map;

public class JsonQueryResult {
	private String matchterm;
	private int previouspage;
	private String searchterm;
	private String query;
	private Object[] language_filters;
	private int total;
	private ResultItem[] results;
	private int page;
	private int nextpage;
	private Object[] source_filters;

	public String getMatchterm() {
		return matchterm;
	}

	public void setMatchterm(String matchterm) {
		this.matchterm = matchterm;
	}

	public int getPreviouspage() {
		return previouspage;
	}

	public void setPreviouspage(int previouspage) {
		this.previouspage = previouspage;
	}

	public String getSearchterm() {
		return searchterm;
	}

	public void setSearchterm(String searchterm) {
		this.searchterm = searchterm;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Object[] getLanguage_filters() {
		return language_filters;
	}

	public void setLanguage_filters(Object[] language_filters) {
		this.language_filters = language_filters;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ResultItem[] getResults() {
		return results;
	}

	public void setResults(ResultItem[] results) {
		this.results = results;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public Object[] getSource_filters() {
		return source_filters;
	}

	public void setSource_filters(Object[] source_filters) {
		this.source_filters = source_filters;
	}

}

