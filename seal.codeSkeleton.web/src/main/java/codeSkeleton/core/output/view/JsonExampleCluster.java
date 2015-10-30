package codeSkeleton.core.output.view;


public class JsonExampleCluster {
	private String query;
	private JsonExampleModel[] clusters;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public JsonExampleModel[] getClusters() {
		return clusters;
	}

	public void setClusters(JsonExampleModel[] clusters) {
		this.clusters = clusters;
	}

}
