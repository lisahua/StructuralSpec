package search.github;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.testng.annotations.Test;

import structualSpec.collect.featureLocation.CodeExampleModel;
import structualSpec.collect.featureLocation.QueryTermSubscriber;
import structualSpec.collect.featureLocation.ir.IRASTVisitor;
import structualSpec.collect.featureLocation.ir.IRExtractor;
import structualSpec.collect.featureLocation.ir.IRExtractorMethodStrategy;
import structualSpec.collect.partial.JsonQueryResult;
import structualSpec.collect.partial.SourceCodeCollector;
import structualSpec.collect.partial.WebContentCollector;
import structualSpec.config.ConfigUtility;

public class TestJsonResult {

	// @Test
	public void testWriteCode() {
		JsonQueryResult[] result = WebContentCollector
				.queryForAllResults("track+mouse+hover");
		SourceCodeCollector.writeDownQueryResult(result);
	}

	// @Test
	public void testASTParser() {
		QueryTermSubscriber.getInstance().setQueryTerms("undo redo TextEditor");
		File[] dir = new File(ConfigUtility.codeOutputPath).listFiles();
		String code = "";
		try {
			code = FileUtils.readFileToString(dir[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CodeExampleModel example = new CodeExampleModel(code);

	}

	// @Test
	public void testIR() {

		ASTParser parser = ASTParser.newParser(AST.JLS3);
		String fileString = null;
		try {
			fileString = FileUtils.readFileToString(new File(
					ConfigUtility.codeOutputPath + "MisoScenePanel.java"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		parser.setSource(fileString.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		IRASTVisitor extractor = new IRASTVisitor();
		cu.accept(extractor);
		System.out.println(extractor.getString());
	}

	@Test
	public void testIRConvertor() {
		IRExtractor extractor = new IRExtractor();
		extractor.setStrategy(new IRExtractorMethodStrategy());
	}

}
