package structualSpec.collect.featureLocation.ir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import structualSpec.config.ConfigUtility;

public class IRExtractor {

//	public StringBuilder setFileString(String code) {
//		ASTParser parser = ASTParser.newParser(AST.JLS3);
//		parser.setSource(code.toCharArray());
//		parser.setKind(ASTParser.K_COMPILATION_UNIT);
//		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
//
//		IRASTVisitor extractor = new IRASTVisitor();
//		cu.accept(extractor);
//		return extractor.getString();
//	}
//	
//	public StringBuilder setMethodString(String code) {
//		ASTParser parser = ASTParser.newParser(AST.JLS3);
//		parser.setSource(code.toCharArray());
//		parser.setKind(ASTParser.K_COMPILATION_UNIT);
//		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
//
//		IRMethodVisitor extractor = new IRMethodVisitor();
//		cu.accept(extractor);
//		return extractor.getString();
//	}
//
//	public StringBuilder setFileString(File code) {
//		String fileString = "";
//		try {
//			fileString = FileUtils.readFileToString(code);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return setFileString(fileString);
//	}
//
//	public void parseToIRInput() {
//		File codeDir = new File(ConfigUtility.codeOutputPath);
//		if (!codeDir.isDirectory())
//			return;
//		File[] fileList = codeDir.listFiles();
//		PrintWriter writer = null;
//
//		for (File file : fileList) {
//			try {
//				writer = new PrintWriter(ConfigUtility.irOutputPath
//						+ file.getName());
//				writer.println(setFileString(file));
//				writer.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public void parseToIRInputMethod() {
//		File codeDir = new File(ConfigUtility.codeOutputPath);
//		if (!codeDir.isDirectory())
//			return;
//		File[] fileList = codeDir.listFiles();
//		PrintWriter writer = null;
//
//		for (File file : fileList) {
//			try {
//				writer = new PrintWriter(ConfigUtility.irOutputPath
//						+ file.getName());
//				writer.println(setFileString(file));
//				writer.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	public void setStrategy(IRExtractorStrategy strategy) {
		strategy.traverseToIRFile(strategy.retrieveInputFiles());
	}
}
