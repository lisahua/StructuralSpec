package structualSpec.collect.featureLocation;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodNodeModel {
	private MethodDeclaration methodNode;
	private HashSet<FactObject> facts = new HashSet<FactObject>();
	private HashMap<String, String> nameType = new HashMap<String, String>();
	private int kwFacts = 0;

	public MethodNodeModel(MethodDeclaration method) {
		methodNode = method;
	}

	public void insertMethodFacts(FactObject fact) {
		facts.add(fact);
		System.out.println(fact.getFact()+"("+fact+")");
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

	public HashSet<FactObject> getFacts() {
		return facts;
	}

	public HashMap<String, String> getNameType() {
		return nameType;
	}

}
