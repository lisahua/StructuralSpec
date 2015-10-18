package structualSpec.collect.featureLocation;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

 class TypeASTVisitor extends ASTVisitor {
	private ArrayList<TypeDeclaration> classes = new ArrayList<TypeDeclaration>();

	public boolean visit(TypeDeclaration decl) {
		classes.add(decl);
		return true;
	}
}
 class MethodASTVisitor extends ASTVisitor {
	 private ArrayList<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
	 private ArrayList<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();

	public boolean visit(MethodDeclaration mtd) {
		methods.add(mtd);
		return true;
	}
	public boolean visit(FieldDeclaration field) {
		fields.add(field);
		return true;
	}
 }
 
 class ExpressionVisitor extends ASTVisitor {
	 
	public boolean visit(MethodInvocation invoke) {
		String arg = "";
		for (Object a : invoke.arguments())
			arg += parseExpression((Expression) a) + ",";
		return true;
	}

	public boolean visit(FieldDeclaration field) {
		String arg = "";
		for (Object o : field.fragments()) {
			arg += o.toString().split("=")[0];
		}
		return true;
	}

	private String parseExpression(Expression e) {

		if (e == null)
			return "";
		switch (e.getNodeType()) {
		case ASTNode.CLASS_INSTANCE_CREATION:
			return ((ClassInstanceCreation) e).getType().toString();
		case ASTNode.CAST_EXPRESSION:
			return ((CastExpression) e).getType().toString();
		case ASTNode.ARRAY_CREATION:
			return ((ArrayCreation) e).getType().toString();
		case ASTNode.ASSIGNMENT:
			return parseExpression(((Assignment) e).getRightHandSide());
		case ASTNode.VARIABLE_DECLARATION_EXPRESSION:
			return ((VariableDeclarationExpression) e).getType().toString();
		case ASTNode.PARAMETERIZED_TYPE:
			return parseExpression(((ParenthesizedExpression) e)
					.getExpression());

		}
		return e.toString();
	}
}
