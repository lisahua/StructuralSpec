package codeSkeleton.core.collect.ir;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;

import codeSkeleton.core.config.ConfigUtility;
import codeSkeleton.core.lexical.preprocessing.CamelCaseSplitter;

public class IRASTVisitor extends ASTVisitor {
	StringBuilder builder = new StringBuilder();
	HashSet<String> winnow = ConfigUtility.winnowKW;
	CamelCaseSplitter splitter = CamelCaseSplitter.getInstance();

	public boolean visit(TypeDeclaration type) {
		builder.append(type.getName().toString() + " ");
		for (Object o : type.typeParameters())
			builder.append(o.toString() + " ");
		for (Object o : type.superInterfaceTypes())
			builder.append(o.toString() + " ");
		builder.append(type.getSuperclassType() == null ? "" : type
				.getSuperclassType() + " ");
		return true;
	}

	public boolean visit(MethodDeclaration method) {
		builder.append(method.getName().toString() + " ");
		for (Object o : method.parameters())
			builder.append(o.toString() + " ");
		builder.append(method.getReturnType2() == null ? "" : method
				.getReturnType2() + " ");
		return true;
	}

	public boolean visit(FieldDeclaration field) {
		for (Object o : field.fragments()) {
			if (o.equals("="))
				break;
			builder.append(o.toString().split("=")[0] + " ");
		}
		return true;
	}

	public boolean visit(MethodInvocation invoke) {
		for (Object o : invoke.arguments()) {
			if (o.toString().contains("\n"))
				continue;
			builder.append(o.toString() + " ");
			// System.out.println(o);
		}
		if (invoke.getExpression() != null)
			builder.append(invoke.getExpression().toString()
					.replace("\\.", " ").replace("(", " ").replace(")", " "));
		return true;
	}

	public boolean visit(VariableDeclaration v) {
		builder.append(v.getName().toString() + " ");
		return true;
	}

	public StringBuilder getString() {
		String file = builder.toString();
		for (String s : winnow)
			file = file.replace(s, "");
	file=	file.replaceAll("\\.", " ");
		builder = new StringBuilder();
		for (String token: file.split(" ")) {
			builder.append(splitter.splitSingleName(token)+" ");
		}
		return builder;
	}
}
