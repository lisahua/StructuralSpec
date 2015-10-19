package structualSpec.syntactic.parser;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class JsonViewASTVisitor extends ASTVisitor  {

	public boolean visit(TypeDeclaration type) {
		return true;
	}
	public boolean visit(MethodDeclaration mtd) {
		return true;
	}
	
}
