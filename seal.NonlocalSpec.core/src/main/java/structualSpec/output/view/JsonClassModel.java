package structualSpec.output.view;

public class JsonClassModel {
	private JsonMethodModel[] methods;
	private String[] fields;
	private String[] alter;

	public JsonMethodModel[] getMethods() {
		return methods;
	}

	public void setMethods(JsonMethodModel[] methods) {
		this.methods = methods;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String[] getAlter() {
		return alter;
	}

	public void setAlter(String[] alter) {
		this.alter = alter;
	}

}
