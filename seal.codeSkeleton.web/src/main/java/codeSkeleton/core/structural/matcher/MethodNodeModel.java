package codeSkeleton.core.structural.matcher;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import codeSkeleton.core.collect.IRInformationModel;

public class MethodNodeModel {
	private MethodDeclaration methodNode;
	private ArrayList<FactObject> facts = new ArrayList<FactObject>();
	private HashMap<String, String> nameType = new HashMap<String, String>();
	private int kwFacts = 0;

	public MethodNodeModel(MethodDeclaration method) {
		methodNode = method;
	}

	public void insertMethodFacts(FactObject fact) {
		facts.add(fact);
		if (IRInformationModel.getInstance().hasKW(fact.toString())) {
			kwFacts++;
			fact.setKw(true);
		}
	}

	public void insertSymbolTable(String name, String type) {
		nameType.put(name, type);
	}

	public MethodDeclaration getMethodNode() {
		return methodNode;
	}

	public int getNumKwFacts() {
		return kwFacts;
	}

	public void setNumKwFacts(int kwFacts) {
		this.kwFacts = kwFacts;
	}

	public ArrayList<FactObject> getFacts() {
		return facts;
	}

	public HashMap<String, String> getNameType() {
		return nameType;
	}

}
