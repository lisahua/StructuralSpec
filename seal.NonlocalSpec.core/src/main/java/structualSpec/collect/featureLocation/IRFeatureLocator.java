package structualSpec.collect.featureLocation;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import structualSpec.syntactic.parser.MethodASTVisitor;

public class IRFeatureLocator {
	private static String[] queryTerms = QueryTermSubscriber.getInstance()
			.getTerms();

	public static TypeNodeModel locateFeature(TypeDeclaration type) {
		MethodASTVisitor mtdVisitor = new MethodASTVisitor(type);
		type.accept(mtdVisitor);
		
		return markKWFacts(mtdVisitor.getTypeNodeModel());
	}

	private static TypeNodeModel markKWFacts(TypeNodeModel typeModel) {
		for (MethodNodeModel model : typeModel.getMethods()) {
			HashSet<FactObject> facts = model.getFacts();
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
		fact.setNumKeyword(count);
	}
}
