package structualSpec.collect.featureLocation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class QueryStringParser {
	
	
//	private QueryStringParser() {
//	}
//
//	private static QueryStringParser matcher = new QueryStringParser();
//
//	public static QueryStringParser getInstance() {
//		return matcher;
//	}

	public void parseDirectory(String dir) {
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.getName().endsWith(".java")) {
				try {
					parseString(FileUtils.readFileToString(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void parseQueryResultItems(String[] items) {
		for (String item : items) {
			parseString(item);
		}
	}
	
	public ArrayList<TypeDeclaration> parseString(String fileString) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(fileString.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		TypeASTVisitor tVisitor = new TypeASTVisitor();
		cu.accept(tVisitor);
		return tVisitor.getClasses();
	}

}
