package structualSpec.collect.featureLocation;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class CodeExampleModel {
	private HashMap<TypeDeclaration, TypeNodeModel> example = new HashMap<TypeDeclaration, TypeNodeModel>();
	private int clusterNum;

	public CodeExampleModel(String code) {
		for (TypeDeclaration type: new QueryStringParser().parseString(code))
			insertNewType(type);
	}
	
	public void insertNewType(TypeDeclaration type) {
		example.put(type, IRFeatureLocator.locateFeature( type));
	}
	
	public int getClusterNum() {
		return clusterNum;
	}

	public void setClusterNum(int clusterNum) {
		this.clusterNum = clusterNum;
	}

	public HashMap<TypeDeclaration,  TypeNodeModel> getExample() {
		return example;
	}

}
