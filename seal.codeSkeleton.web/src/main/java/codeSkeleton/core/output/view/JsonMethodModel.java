package codeSkeleton.core.output.view;

public class JsonMethodModel {
	private String method;
	private String[] variables;
	private String[] stataments;
	private String[] alter;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String[] getVariables() {
		return variables;
	}
	public void setVariables(String[] variables) {
		this.variables = variables;
	}
	public String[] getStataments() {
		return stataments;
	}
	public void setStataments(String[] stataments) {
		this.stataments = stataments;
	}
	public String[] getAlter() {
		return alter;
	}
	public void setAlter(String[] alter) {
		this.alter = alter;
	}
	
	
}
