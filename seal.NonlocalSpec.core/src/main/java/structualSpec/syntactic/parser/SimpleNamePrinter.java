package structualSpec.syntactic.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class SimpleNamePrinter {
	protected PrintWriter writer ;

	public SimpleNamePrinter(String outputPath) {
		try {
			writer = new PrintWriter(outputPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getNameInDir(String dirPath) {
		File folder = new File(dirPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].getName().endsWith(".java")) {
				if (!listOfFiles[i].isDirectory())
					getNameInFile(listOfFiles[i]);
			} else if (listOfFiles[i].isDirectory()) {
				 getNameInDir(listOfFiles[i].getAbsolutePath());
			}
		}
	}

	public void getNameInFile(File file) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		String fileString = null;
		try {
			fileString = FileUtils.readFileToString(file);
			writer.println(file.getAbsolutePath());

			parser.setSource(fileString.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

			NameASTVisitor mdVisitor = new NameASTVisitor(writer);
			cu.accept(mdVisitor);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
