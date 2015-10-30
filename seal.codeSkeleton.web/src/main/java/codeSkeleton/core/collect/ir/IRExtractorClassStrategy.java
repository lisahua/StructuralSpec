package codeSkeleton.core.collect.ir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;

import codeSkeleton.core.config.ConfigUtility;

public class IRExtractorClassStrategy extends IRExtractorStrategy {

	@Override
	public StringBuilder setFileString(CompilationUnit cu) {
		IRASTVisitor extractor = new IRASTVisitor();
		cu.accept(extractor);
		return extractor.getString();
	}

	@Override
	public void traverseToIRFile(File[] inputs) {
		try {
			FileUtils.cleanDirectory(new File(ConfigUtility.irOutputPath));
		} catch (IOException e) {
		}
		PrintWriter writer ;
		String path = ConfigUtility.irOutputPath;
		for (File file: inputs) {
			try {
				writer = new PrintWriter(path+file.getName());
				writer.println(setFileString(file));
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public StringBuilder[] retrieveAllIRInfo(File[] inputs) {
		// TODO Auto-generated method stub
		return null;
	}

}
