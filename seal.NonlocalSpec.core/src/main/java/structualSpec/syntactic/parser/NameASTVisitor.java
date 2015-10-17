package structualSpec.syntactic.parser;

import java.io.PrintWriter;

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
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

public class NameASTVisitor extends ASTVisitor {

	PrintWriter writer;

	public NameASTVisitor(PrintWriter writer) {
		this.writer = writer;
	}

	public boolean visit(TypeDeclaration decl) {

		String itf = "";
		for (Object d : decl.superInterfaceTypes()) {
			itf += d + ",";
		}
		// System.out.println(decl.getSuperclassType());
		writer.println("t," + decl.getName() + "," + decl.getSuperclassType()
				+ "," + itf);
		return true;
	}

	public boolean visit(MethodDeclaration mtd) {
		String param = "";
		for (Object d : mtd.parameters())
			param += d + ",";
		Type ret = mtd.getReturnType2();
		String retur = (ret == null) ? "" : ret.toString();
		writer.println(" m," + mtd.getName() + "," + param + "," + retur);
		return true;
	}

	public boolean visit(MethodInvocation invoke) {
		String arg = "";
		for (Object a : invoke.arguments())
			arg += parseExpression((Expression) a) + ",";
		writer.println("  k," + parseExpression(invoke.getExpression()) + ","
				+ invoke.getName() + "," + arg);
		return true;
	}

	public boolean visit(FieldDeclaration field) {
		String arg = "";
		for (Object o : field.fragments()) {
			arg += o.toString().split("=")[0] ;
		}
		writer.println(" f," + arg);
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
