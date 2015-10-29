package structualSpec.syntactic.parser;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import structualSpec.collect.featureLocation.FACT;
import structualSpec.collect.featureLocation.FactObject;
import structualSpec.collect.featureLocation.MethodNodeModel;
import structualSpec.collect.featureLocation.TypeNodeModel;

public class VariableASTVisitor extends ASTVisitor {
	// private ArrayList<FactObject> facts = new ArrayList<FactObject>();
	// private HashMap<String, String> nameType;
	private MethodNodeModel methodModel;
	private TypeNodeModel typeModel;

	public VariableASTVisitor(final TypeNodeModel type, MethodNodeModel mtd) {
		methodModel = mtd;
		typeModel = type;
	}

	public boolean visit(MethodInvocation invoke) {
		String arg = "";
		methodModel.insertMethodFacts(new FactObject(FACT.calls, invoke
				.getName().toString(), parseExpression(invoke.getExpression()),
				invoke.getExpression() == null ? "" : invoke.getExpression()
						.toString()));
		return true;
	}

	

	public boolean visit(Assignment assign) {
		if (assign.getLeftHandSide().getNodeType() == ASTNode.SIMPLE_NAME
				&& assign.getRightHandSide().getNodeType() == ASTNode.SIMPLE_NAME)
			return true;
		String left = parseExpression(assign.getLeftHandSide()).toString();
		String right = parseExpression(assign.getRightHandSide()).toString();
		methodModel
				.insertMethodFacts(new FactObject(FACT.accesses, left, right));
		return true;
	}

	// public boolean visit(VariableDeclaration var) {
	//
	// methodModel.insertMethodFacts(new FactObject(FACT.variable,
	// var.getName().toString(), var
	// .getName().toString()));
	// return true;
	// }

	private String parseExpression(Expression e) {

		if (e == null)
			return "";
		switch (e.getNodeType()) {
		case ASTNode.SIMPLE_NAME:
			for (String name : methodModel.getNameType().keySet())
				if (e.toString().equals(name))
					return methodModel.getNameType().get(name);
			for (String name : typeModel.getNameType().keySet())
				if (e.toString().equals(name))
					return typeModel.getNameType().get(name);
			break;
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
			// case ASTNode.PARAMETERIZED_TYPE:
			// return parseExpression(((ParenthesizedType) e)
			// .getExpression());
		case ASTNode.METHOD_INVOCATION:
			return parseExpression(((MethodInvocation) e).getExpression());
		case ASTNode.PARENTHESIZED_EXPRESSION:
			return parseExpression(((ParenthesizedExpression) e)
					.getExpression());
		}
		return e.toString();
	}

	public boolean visit(SingleVariableDeclaration var) {
		methodModel.insertMethodFacts(new FactObject(FACT.variable, var.getName().toString(),var.getType().toString()));
		methodModel.insertSymbolTable(var.getName().toString(), var.getType().toString());
		return true;
	}
	public boolean visit(VariableDeclarationStatement var) {
		//FIXME ugly code
		String[] names = var.fragments().toString().split("=")[0].split(" ");
		methodModel.insertMethodFacts(new FactObject(FACT.variable, names[names.length-1].replace("[",""), var.getType().toString()));
//		methodModel.insertSymbolTable(var.getName().toString(), var.getType().toString());
		return true;
	}
}