package codeSkeleton.core.collect.ir;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import codeSkeleton.core.collect.IRInformationModel;
import codeSkeleton.core.collect.query.JsonSourceCode;
import codeSkeleton.core.config.ConfigUtility;

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

	public StringBuilder[] retrieveAllIRInfo() {
		List<JsonSourceCode> corpus = IRInformationModel.getInstance()
				.getFileInfo();
		int len = corpus.size();
		StringBuilder[] sbList = new StringBuilder[len];
		for (int i = 0; i < len; i++)
			sbList[i] = setFileString(corpus.get(i).getCode());
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

	public StringBuilder setFileString(String code) {
		return setFileString(retrieveCU(code));
	}
}
