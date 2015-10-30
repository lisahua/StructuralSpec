package codeSkeleton.core.output.view;

public class JsonClassModel {
	private String[] imports;
	private String classStmt;
	private String[] alter;
	private String[] fields;
	private JsonMethodModel[] methods;
	private JsonMethodModel[] alterMethods;
	private JsonClassModel[] innerClass;
	
	public String getClassStmt() {
		return classStmt;
	}

	public void setClassStmt(String classStmt) {
		this.classStmt = classStmt;
	}

	public String[] getImports() {
		return imports;
	}

	public void setImports(String[] imports) {
		this.imports = imports;
	}

	public JsonMethodModel[] getMethods() {
		return methods;
	}

	public JsonMethodModel[] getAlterMethods() {
		return alterMethods;
	}

	public void setAlterMethods(JsonMethodModel[] alterMethods) {
		this.alterMethods = alterMethods;
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

	public JsonClassModel[] getInnerClass() {
		return innerClass;
	}

	public void setInnerClass(JsonClassModel[] innerClass) {
		this.innerClass = innerClass;
	}

}
