package codeSkeleton.core.structural.parser;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import codeSkeleton.core.structural.matcher.FACT;
import codeSkeleton.core.structural.matcher.FactObject;
import codeSkeleton.core.structural.matcher.MethodNodeModel;
import codeSkeleton.core.structural.matcher.TypeNodeModel;

public class MethodASTVisitor extends ASTVisitor {
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
		mtd.accept(new VariableASTVisitor(typeModel, methodModel));
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