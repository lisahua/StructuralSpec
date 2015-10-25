package structualSpec.collect.featureLocation.ir;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class IRExtractor {

	public StringBuilder setString(String code) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(code.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		IRASTVisitor extractor = new IRASTVisitor();
		cu.accept(extractor);
		return extractor.getString();
	}
	public StringBuilder setString(File code) {
		String fileString = "";
		try {
			fileString = FileUtils.readFileToString(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return setString(fileString);
	}
}
