package codeSkeleton.core.structural.matcher;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import codeSkeleton.core.collect.ir.IRFeatureLocator;

public class CodeExampleModel {
	private ArrayList<TypeNodeModel> example = new ArrayList<TypeNodeModel>();
	private int clusterNum = -1;
	private ArrayList<String> factString = new ArrayList<String>();

	public CodeExampleModel(String code) {
		for (TypeDeclaration type : new QueryStringParser().parseString(code))
			insertNewType(type);
	}

	public void insertNewType(TypeDeclaration type) {
		TypeNodeModel typeModel = IRFeatureLocator.locateFeature(type);
		example.add(typeModel);
		for (MethodNodeModel method : typeModel.getMethods()) {
			if (method.getNumKwFacts() > 0) {
				for (FactObject fact : method.getFacts())
					// if (fact.getFact() == FACT.calls)
					if (fact.isKw())
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

	public ArrayList<TypeNodeModel> getExample() {
		return example;
	}

	public ArrayList<String> getFactString() {
		return factString;
	}
	// public String getCodeExampleWithHighlight() {
	// StringBuilder builder = new StringBuilder();
	// for (Map.Entry<TypeDeclaration, TypeNodeModel> entry: example.entrySet())
	// {
	// if (entry.getValue().getKwCount()>0) {
	// builder.append(entry.getKey().toString());
	// }
	// }
	// return builder.toString();
	// }
}
