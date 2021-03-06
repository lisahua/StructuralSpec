package codeSkeleton.core.collect.ir;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import codeSkeleton.core.collect.IRInformationModel;
import codeSkeleton.core.structural.matcher.FactObject;
import codeSkeleton.core.structural.matcher.MethodNodeModel;
import codeSkeleton.core.structural.matcher.TypeNodeModel;
import codeSkeleton.core.structural.parser.MethodASTVisitor;

public class IRFeatureLocator {
	private static String[] queryTerms = IRInformationModel.getInstance()
			.getTerms();

	public static TypeNodeModel locateFeature(TypeDeclaration type) {
		MethodASTVisitor mtdVisitor = new MethodASTVisitor(type);
		type.accept(mtdVisitor);

		return markKWFacts(mtdVisitor.getTypeNodeModel());
	}

	private static TypeNodeModel markKWFacts(TypeNodeModel typeModel) {
		for (MethodNodeModel model : typeModel.getMethods()) {
			ArrayList<FactObject> facts = model.getFacts();
			for (FactObject fact : facts) {
				setScore(fact);
			}
		}
		for (FactObject fact : typeModel.getFields())
			setScore(fact);
		return typeModel;
	}

	private static void setScore(FactObject fact) {
		String fS = fact.toString().toLowerCase();
		int size = fS.length();
		int count = 0;
		for (String term : queryTerms) {
			fS = fS.replace(term, "");
			count += (size - fS.length()) / term.length();
		}
		if (count > 0)
			fact.setKw(true);
	}
}
