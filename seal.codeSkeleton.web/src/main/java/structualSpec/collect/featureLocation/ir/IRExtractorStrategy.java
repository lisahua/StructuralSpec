package structualSpec.collect.featureLocation.ir;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import structualSpec.config.ConfigUtility;

public abstract class IRExtractorStrategy {

	public CompilationUnit retrieveCU(String code) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(code.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		return (CompilationUnit) parser.createAST(null);
	}

	public File[] retrieveInputFiles() {
		File codeDir = new File(ConfigUtility.codeOutputPath);
		if (!codeDir.isDirectory())
			return null;
		return codeDir.listFiles();

	}

	public abstract StringBuilder setFileString(CompilationUnit cu);

	public abstract void traverseToIRFile(File[] inputs);

	public StringBuilder[] retrieveAllIRInfo(File[] inputs) {
		int len = inputs.length;
		StringBuilder[] sbList = new StringBuilder[len];
		for (int i = 0; i < len; i++)
			sbList[i] = setFileString(inputs[i]);
		return sbList;
	}

	public StringBuilder setFileString(File code) {
		String fileString = "";
		try {
			fileString = FileUtils.readFileToString(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return setFileString(retrieveCU(fileString));
	}

}
