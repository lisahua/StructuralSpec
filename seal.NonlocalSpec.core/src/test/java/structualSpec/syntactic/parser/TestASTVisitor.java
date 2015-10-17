package structualSpec.syntactic.parser;

import org.testng.annotations.Test;

import structualSpec.lexical.preprocessing.PlainTextPreprocessor;

public class TestASTVisitor {
	protected String dirPath = "/Users/UCLAPLSE/Documents/project/nonLocalSpec/dataset/jena/";
	protected String outputDir = "src/test/java/source.output/";
	//@Test
	public void testVisitor() {
		SimpleNamePrinter printer = new SimpleNamePrinter(
				outputDir+"jena.txt");
		printer.getNameInDir(dirPath);

	}
	@Test
	public void testPlainTextProcessor() {
		PlainTextPreprocessor processor = new PlainTextPreprocessor(outputDir+"jena-plain.txt") ;
		processor.getNameInDir(dirPath);
	}
	
}
