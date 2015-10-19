package structualSpec.collect.featureLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

class TypeASTVisitor extends ASTVisitor {
	private ArrayList<TypeDeclaration> classes = new ArrayList<TypeDeclaration>();

	public boolean visit(TypeDeclaration decl) {
		classes.add(decl);
		return true;
	}

	public ArrayList<TypeDeclaration> getClasses() {
		return classes;
	}
}

class MethodASTVisitor extends ASTVisitor {
	// private HashSet<FactObject> facts = new HashSet<FactObject>();
	// private HashMap<String, String> nameType = new HashMap<String, String>();
	// private ArrayList<FieldDeclaration> fields = new
	// ArrayList<FieldDeclaration>();
	//TODO symbol table not use
	TypeNodeModel typeModel;

	public MethodASTVisitor(TypeDeclaration type) {
		typeModel = new TypeNodeModel(type);
	}

	public boolean visit(MethodDeclaration mtd) {
		MethodNodeModel methodModel = new MethodNodeModel(mtd);
		List<?> params = mtd.parameters();
		String parameters = "";
		for (Object param : params) {
			String[] tokens = param.toString().split(" ");
			methodModel.insertMethodFacts(new FactObject(FACT.param, mtd
					.getName().toString(), tokens[1], tokens[0]));
			parameters += "_" + tokens[0];
			methodModel.insertSymbolTable(tokens[1], tokens[0]);
		}
		methodModel.insertMethodFacts(new FactObject(FACT.method, mtd.getName()
				.toString(), mtd.getName().toString() + parameters, mtd
				.getReturnType2().toString()));
		typeModel.insertSymbolTable(mtd.getName().toString(), mtd
				.getReturnType2().toString());
		new VariableASTVisitor(methodModel);
		typeModel.insertMethodFacts(methodModel);
		return true;
	}

	public boolean visit(FieldDeclaration field) {
		String name = "";
		Type type = field.getType();
		for (Object o : field.fragments()) {
			String[] tokens = o.toString().split("=")[0].split(" ");
			name = tokens[tokens.length - 1].replace(";", "");
		}
		typeModel.insertFieldFacts(new FactObject(FACT.field, name, type
				.toString()));
		typeModel.insertSymbolTable(name, type.toString());
		return true;
	}

	public TypeNodeModel getTypeNodeModel() {
		return typeModel;
	}
}

class VariableASTVisitor extends ASTVisitor {
//	private ArrayList<FactObject> facts = new ArrayList<FactObject>();
	// private HashMap<String, String> nameType;
	private MethodNodeModel methodModel;

	public VariableASTVisitor(MethodNodeModel mtd) {
		methodModel = mtd;
	}

	public boolean visit(MethodInvocation invoke) {
		String arg = "";
		for (Object o : invoke.arguments()) {

		}
		methodModel.insertMethodFacts(new FactObject(FACT.calls, arg, arg));
		return true;
	}

	public boolean visit(Assignment assign) {
		String left = parseExpression(assign.getLeftHandSide()).toString();
		String right = parseExpression(assign.getRightHandSide()).toString();
		methodModel.insertMethodFacts(new FactObject(FACT.accesses, left, right));
		return true;
	}

//	public boolean visit(VariableDeclaration var) {
//	
//		methodModel.insertMethodFacts(new FactObject(FACT.variable, var.getName().toString(), var
//				.getName().toString()));
//		return true;
//	}

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
