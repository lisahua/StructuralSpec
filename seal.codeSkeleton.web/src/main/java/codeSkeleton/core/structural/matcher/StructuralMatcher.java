package codeSkeleton.core.structural.matcher;

import java.util.ArrayList;
import java.util.List;

import codeSkeleton.core.collect.IRInformationModel;
import codeSkeleton.core.collect.query.JsonSourceCode;

public class StructuralMatcher {
	private ArrayList<CodeExampleModel> examples = new ArrayList<CodeExampleModel>();

	 private StructuralMatcher() {}
	 private static StructuralMatcher matcher = new StructuralMatcher();
	 public static StructuralMatcher getInstance() {
	 return matcher;
	 }
	public void setExamples() {
		List<JsonSourceCode> corpus = IRInformationModel.getInstance().getFileInfo();
		for (JsonSourceCode example : corpus) {
			examples.add(new CodeExampleModel(example.getCode()));
		}
		setClusterNumber();
	}
	public void setExamples(String[] codes) {
		for (String example : codes) {
			examples.add(new CodeExampleModel(example));
		}
		setClusterNumber();
	}
	
	public void addExample (String code) {
		examples.add(new CodeExampleModel(code));
	}
	
	public void setClusterNumber() {
		ExampleCluster.setClusterNumber(examples);
	}
	public void clusterExample() {
		
	}
}
