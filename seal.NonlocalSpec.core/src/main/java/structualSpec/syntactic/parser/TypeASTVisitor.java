package structualSpec.syntactic.parser;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TypeASTVisitor extends ASTVisitor {
	private ArrayList<TypeDeclaration> classes = new ArrayList<TypeDeclaration>();

	public boolean visit(TypeDeclaration decl) {
		classes.add(decl);
		return true;
	}

	public ArrayList<TypeDeclaration> getClasses() {
		return classes;
	}
}




