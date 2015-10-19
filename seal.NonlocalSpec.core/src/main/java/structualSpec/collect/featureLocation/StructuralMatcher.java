package structualSpec.collect.featureLocation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import structualSpec.config.ConfigUtility;

public class StructuralMatcher {
	private ArrayList<CodeExampleModel> examples = new ArrayList<CodeExampleModel>();

	 private StructuralMatcher() {}
	 private static StructuralMatcher matcher = new StructuralMatcher();
	 public static StructuralMatcher getInstance() {
	 return matcher;
	 }
	public void setExamples(String[] codes) {
		for (String example : codes) {
			examples.add(new CodeExampleModel(example));
		}
		setClusterNumber();
	}

	public void setFromDir(String dir) {
		File[] files = new File(ConfigUtility.codeOutputPath).listFiles();
		String[] fileS = new String[files.length];
		for (int i=0;i<fileS.length;i++)
			try {
				fileS[i] = FileUtils.readFileToString(files[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		setExamples(fileS);
	}
	private void setClusterNumber() {
		ExampleCluster.setClusterNumber(examples);
	}
}
