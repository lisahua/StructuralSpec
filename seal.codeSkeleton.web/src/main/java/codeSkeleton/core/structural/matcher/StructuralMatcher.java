package codeSkeleton.core.structural.matcher;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import codeSkeleton.core.collect.IRInformationModel;
import codeSkeleton.core.collect.query.JsonSourceCode;

public class StructuralMatcher {
	private ArrayList<CodeExampleModel> examples = new ArrayList<CodeExampleModel>();

	private StructuralMatcher() {
	}

	private static StructuralMatcher matcher = new StructuralMatcher();
	private int[] cClusters = null;

	public static StructuralMatcher getInstance() {
		return matcher;
	}

	public void setExamples() {
		List<JsonSourceCode> corpus = IRInformationModel.getInstance()
				.getFileInfo();
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

	public void addExample(String code) {
		examples.add(new CodeExampleModel(code));
	}

	private void setClusterNumber() {
		cClusters = ExampleCluster.setClusterNumber(examples);
	}

	public ArrayList<CodeExampleModel> getExamples() {
		return examples;
	}

	public int[] clusterExample() {
		// int len = examples.size();
		// for (int i = 0; i < len; i++) {
		// for (TypeDeclaration type : examples.get(i).getExample().keySet())
		// System.out.println(i + " " + type.getName() + " "
		// + cClusters[i]);
		// }
		return cClusters;
	}
}
