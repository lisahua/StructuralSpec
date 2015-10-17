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

class ResultItem {
	private String repo;
	private String language;
	private int linescount;
	private String location;
	private String name;
	private String url;
	private String md5hash;
	private Map<Integer, String> lines;
	private long id;
	private String filename;

	public String getRepo() {
		return repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getLinescount() {
		return linescount;
	}

	public void setLinescount(int linescount) {
		this.linescount = linescount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5hash() {
		return md5hash;
	}

	public void setMd5hash(String md5hash) {
		this.md5hash = md5hash;
	}


	public Map<Integer, String> getLines() {
		return lines;
	}

	public void setLines(Map<Integer, String> lines) {
		this.lines = lines;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
