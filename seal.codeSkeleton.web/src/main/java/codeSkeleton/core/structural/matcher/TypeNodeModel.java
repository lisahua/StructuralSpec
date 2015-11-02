package codeSkeleton.core.structural.matcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import codeSkeleton.core.collect.IRInformationModel;

public class TypeNodeModel {
	private ArrayList<MethodNodeModel> methods = new ArrayList<MethodNodeModel>();
	private HashMap<String, String> nameType = new HashMap<String, String>();
	private TypeDeclaration typeNode;
	private ArrayList<FactObject> fields = new ArrayList<FactObject>();
//	private HashMap<MethodNodeModel, HashSet<FactObject>> kwFacts = new HashMap<MethodNodeModel, HashSet<FactObject>>();
private  int kwCount = 0;

	public TypeNodeModel(TypeDeclaration type) {
		typeNode = type;
	}

	public void insertFieldFacts(FactObject fact) {
		fields.add(fact);
		if (IRInformationModel.getInstance().hasKW(fact.toString())) {
			kwCount++;
			fact.setKw(true);
		}
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

	public ArrayList<MethodNodeModel> getMethods() {
		return methods;
	}

	public HashMap<String, String> getNameType() {
		return nameType;
	}

	public TypeDeclaration getTypeNode() {
		return typeNode;
	}

	public ArrayList<FactObject> getFields() {
		return fields;
	}

	public void setFields(ArrayList<FactObject> fields) {
		this.fields = fields;
	}

	public  int getKwCount() {
		return kwCount;
	}

//	public HashMap<MethodNodeModel, HashSet<FactObject>> getKwFacts() {
//		return kwFacts;
//	}

}
