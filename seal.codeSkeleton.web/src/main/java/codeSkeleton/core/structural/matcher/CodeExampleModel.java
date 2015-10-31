package codeSkeleton.core.structural.matcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import codeSkeleton.core.collect.ir.IRFeatureLocator;

public class CodeExampleModel {
	private HashMap<TypeDeclaration, TypeNodeModel> example = new HashMap<TypeDeclaration, TypeNodeModel>();
	private int clusterNum = -1;
	private HashSet<String> factString = new HashSet<String>();

	public CodeExampleModel(String code) {
		for (TypeDeclaration type : new QueryStringParser().parseString(code))
			insertNewType(type);
	}

	public void insertNewType(TypeDeclaration type) {
		TypeNodeModel typeModel = IRFeatureLocator.locateFeature(type);
		example.put(type, typeModel);
		for (MethodNodeModel method : typeModel.getMethods()) {
			if (method.getNumKwFacts() > 0) {
				for (FactObject fact : method.getFacts()) 
//					if (fact.getFact() == FACT.calls)
					if (fact.getNumKeyword()>0)
						factString.add(fact.toString());
			}
		}
	}

	public int getClusterNum() {
		return clusterNum;
	}

	public void setClusterNum(int clusterNum) {
		this.clusterNum = clusterNum;
	}

	public HashMap<TypeDeclaration, TypeNodeModel> getExample() {
		return example;
	}

	public HashSet<String> getFactString() {
		return factString;
	}
public String getCodeExampleWithHighlight() {
	StringBuilder builder = new StringBuilder();
	for (Map.Entry<TypeDeclaration, TypeNodeModel> entry: example.entrySet()) {
		if (entry.getValue().getKwCount()>0) {
			entry.getKey().toString();
		}
	}
	return builder.toString();
}
}
