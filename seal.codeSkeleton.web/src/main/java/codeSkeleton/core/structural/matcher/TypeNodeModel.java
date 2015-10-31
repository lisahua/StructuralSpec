package codeSkeleton.core.structural.matcher;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TypeNodeModel {
	private HashSet<MethodNodeModel> methods = new HashSet<MethodNodeModel>();
	private HashMap<String, String> nameType = new HashMap<String, String>();
	private TypeDeclaration typeNode;
	private HashSet<FactObject> fields = new HashSet<FactObject>();
//	private HashMap<MethodNodeModel, HashSet<FactObject>> kwFacts = new HashMap<MethodNodeModel, HashSet<FactObject>>();
private  int kwCount = 0;

	public TypeNodeModel(TypeDeclaration type) {
		typeNode = type;
	}

	public void insertFieldFacts(FactObject fact) {
		fields.add(fact);
		kwCount += fact.numKeyword;
//		System.out.println(fact.getFact() + "(" + fact + ")");
	}

	public void insertMethodFacts(MethodNodeModel mNode) {
		methods.add(mNode);
		kwCount += mNode.getNumKwFacts();
//		for (FactObject fact : mNode.getFacts()) {
//			HashSet<FactObject> set = new HashSet<FactObject>();
//			if (fact.getNumKeyword() > 0) {
//				set.add(fact);
//			}
//			if (set.size() > 0)
//				kwFacts.put(mNode, set);
//			
//		}
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

	public  int getKwCount() {
		return kwCount;
	}

//	public HashMap<MethodNodeModel, HashSet<FactObject>> getKwFacts() {
//		return kwFacts;
//	}

}
