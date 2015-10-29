package structualSpec.collect.featureLocation.ir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;

import structualSpec.config.ConfigUtility;

public class IRExtractorMethodStrategy extends IRExtractorStrategy {

	@Override
	public StringBuilder setFileString(CompilationUnit cu) {
		IRMethodVisitor extractor = new IRMethodVisitor();
		cu.accept(extractor);
		return extractor.getString();
	}
	
	

	@Override
	public void traverseToIRFile(File[] inputs) {
		try {
			FileUtils.cleanDirectory(new File(ConfigUtility.irOutputPath));
		} catch (IOException e) {
		}
		PrintWriter writer;
		String path = ConfigUtility.irOutputPath;
		for (File file : inputs) {
			try {
				int count = 0;
				String[] tokens = setFileString(file).toString().split("\\$\\$");
				
				for (String s : tokens) {
					writer = new PrintWriter(path + file.getName() + count++);
					writer.print(s);
					writer.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	

}
