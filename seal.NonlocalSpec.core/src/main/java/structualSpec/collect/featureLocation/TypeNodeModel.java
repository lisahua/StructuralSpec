package structualSpec.collect.featureLocation;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TypeNodeModel {
	private HashSet<MethodNodeModel> methods = new HashSet<MethodNodeModel>();
	private HashMap<String, String> nameType = new HashMap<String, String>();
	private TypeDeclaration typeNode;
	private HashSet<FactObject> fields = new HashSet<FactObject>();

	public TypeNodeModel(TypeDeclaration type) {
		typeNode = type;
	}

	public void insertFieldFacts(FactObject fact) {
		fields.add(fact);
	}

	public void insertMethodFacts(MethodNodeModel mNode) {
		methods.add(mNode);
	}

	public void insertSymbolTable(String name, String type) {
		nameType.put(name, type);
	}

	public HashSet<MethodNodeModel> getMethods() {
		return methods;
	}

	public HashMap<String, String> getNameType() {
		return nameType;
	}

	public TypeDeclaration getTypeNode() {
		return typeNode;
	}

	public HashSet<FactObject> getFields() {
		return fields;
	}

	public void setFields(HashSet<FactObject> fields) {
		this.fields = fields;
	}

	
	
}
