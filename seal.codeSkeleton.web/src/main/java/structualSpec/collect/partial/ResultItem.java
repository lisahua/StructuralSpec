package structualSpec.collect.partial;

import java.util.Map;

public class ResultItem {
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
