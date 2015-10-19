package structualSpec.syntactic.parser;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import structualSpec.config.ConfigUtility;
import structualSpec.output.view.JsonExampleCluster;

public class TestASTVisitor {

	public void testJsonMarshaller() {
		JsonExampleCluster cluster = new JsonExampleCluster();

		ASTParser parser = ASTParser.newParser(AST.JLS3);
		String fileString = null;
		try {
			fileString = FileUtils.readFileToString(new File(ConfigUtility.codeOutputPath+"Work_TextEditor.java"));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		parser.setSource(fileString.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		

	}

}
