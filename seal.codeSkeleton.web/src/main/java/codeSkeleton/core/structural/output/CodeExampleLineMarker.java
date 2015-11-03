package codeSkeleton.core.structural.output;

import java.util.HashSet;
import java.util.TreeSet;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;

import codeSkeleton.core.collect.IRInformationModel;
import codeSkeleton.core.collect.query.JsonSourceCode;
import codeSkeleton.core.config.ConfigUtility;
import codeSkeleton.core.structural.matcher.CodeExampleModel;
import codeSkeleton.core.structural.matcher.FactObject;
import codeSkeleton.core.structural.matcher.MethodNodeModel;
import codeSkeleton.core.structural.matcher.StructuralMatcher;
import codeSkeleton.core.structural.matcher.TypeNodeModel;

public class CodeExampleLineMarker {
	private TreeSet<LineMarkerModel> markers = new TreeSet<LineMarkerModel>();
	private String[] lines = null;
	private int len = 0;
	private int prevIndex = 0;
	private HashSet<String> researchString = new HashSet<String>();
	private HashSet<Integer> existID = new HashSet<Integer>();

	private void markCodeExample(int id) {
		// if (id >= IRInformationModel.getInstance().getExampleSize())
		// return;
		lines = IRInformationModel.getInstance().getSingleFile(id).getCode()
				.split("\n");
		len = lines.length;
		CodeExampleModel model = StructuralMatcher.getInstance()
				.getSingleExample(id);
		// markAllKWLines();
		if (id == 0 || id == 3) {
			for (TypeNodeModel type : model.getExample()) {
				if (type.getTypeNode().getName().toString()
						.contains("UndoAction")
						|| type.getTypeNode().getName().toString()
								.contains("RedoAction")
						|| type.getTypeNode().getName().toString()
								.contains("TextEditor")) {
					if (type.getKwCount() == 0)
						continue;
					setTypeDeclaration(type.getTypeNode().getName().toString());
					setMethodDeclaration(type);
				}
			}
		} else {
			for (TypeNodeModel type : model.getExample()) {
				if (type.getKwCount() == 0)
					continue;
				setTypeDeclaration(type.getTypeNode().getName().toString());
				setMethodDeclaration(type);
			}
		}
		// return markers;
	}

	private void markAllKWLines() {
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("import") || lines[i].contains("\\*")
					|| lines[i].contains("//"))
				continue;
			if (IRInformationModel.getInstance().hasKW(lines[i])) {

				addMarker(i, LineType.M_Call);
				existID.add(i);
			}
		}
	}

	public String getCodeExampleMarkerString(int id) {
		if (id >= IRInformationModel.getInstance().getExampleSize())
			return "";
		markCodeExample(id);
		JsonSourceCode code = IRInformationModel.getInstance()
				.getSingleFile(id);
		StringBuilder builder = new StringBuilder(" <h5><a href=\""
				+ ConfigUtility.codeViewPortal
				+ code.getId()
				+ "\">"
				+ IRInformationModel.getInstance().getSingleFile(id)
						.getFileName() + "</a></h5><ol class=\"code-result\">");

		for (LineMarkerModel model : markers) {
			// if (i > 0
			// && markers.get(i).getLineNo()
			// - markers.get(i - 1).getLineNo() != 1)
			// builder.append("<hr class=\"codesplit\" />");
			if (id == 0 && model.getLineNo() > 401)
				break;
			builder.append(model.toString());
		}
		builder.append(" </ol>");
		return builder.toString();
	}

	private void addMarker(int id, LineType type) {
		if (type == LineType.hasKW) {
			markers.add(new LineMarkerModel(id, type, lines[id], true));
			return;
		}
		if (id > len - 2)
			return;
		if (existID.contains(id))
			return;
		markers.add(new LineMarkerModel(id, type, lines[id], IRInformationModel
				.getInstance().hasKW(lines[id])));
	}

	private void setTypeDeclaration(String className) {
		String name = "class " + className;
		addMarker(getLineIndex(name), LineType.C_Declare);
	}

	private void setMethodDeclaration(TypeNodeModel model) {
		for (FactObject field : model.getFields()) {
			if (IRInformationModel.getInstance().hasKW(field.toString()))
				addMarker(getLineIndex(field.toString()), LineType.F_Declare);
		}
		for (MethodNodeModel method : model.getMethods()) {
			if (method.getNumKwFacts() == 0)
				continue;
			MethodDeclaration declare = method.getMethodNode();
			String methodD = "";
			if (declare.isConstructor())
				methodD = "public " + declare.getName().toString();
			else {
				Type type = declare.getReturnType2();
				methodD = type == null ? "void" : type.toString();
				methodD += " " + declare.getName().toString();
			}

			int first = getLineIndex(methodD);
			addMarker(first, LineType.M_Declare);
			for (FactObject fact : method.getFacts())
				if (fact.isKw())
					addMarker(getLineIndex(fact.toString()), LineType.M_Call);
			// getLastIndex(first, declare.getLength());

			// int last = getLineIndex(block[block.length - 1]);
			// for (int i = first; i < last; i++)
			// addMarker(i, LineType.M_Call, lines[i]);
		}
	}

	private int getLineIndex(String name) {
		String[] terms = name.split(",");
		boolean flag = false;
		if (prevIndex >= len)
			prevIndex = 0;
		while (prevIndex < len) {
			if (lines[prevIndex].contains(terms[0])) {
				flag = true;
				for (int i = 1; i < terms.length; i++)
					if (!lines[prevIndex].contains(terms[i])) {
						flag = false;
						break;
					}
				if (flag)
					return prevIndex;
			}
			prevIndex++;
		}

		if (prevIndex >= len && !researchString.contains(name)) {
			researchString.add(name);
			getLineIndex(name);

		}
		if (prevIndex >= len)
			System.out.println(name);
		return prevIndex;
	}

	private int getLastIndex(int startID, int length) {
		int lenCount = 0;
		int idCount = startID;
		while (lenCount < length && idCount < len) {
			lenCount += lines[idCount++].length();
			addMarker(idCount, LineType.M_Call);
		}
		return idCount;
	}
}
