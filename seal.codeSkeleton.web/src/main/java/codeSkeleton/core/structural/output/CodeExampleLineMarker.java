package codeSkeleton.core.structural.output;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import codeSkeleton.core.collect.IRInformationModel;
import codeSkeleton.core.collect.query.JsonSourceCode;
import codeSkeleton.core.config.ConfigUtility;
import codeSkeleton.core.structural.matcher.CodeExampleModel;
import codeSkeleton.core.structural.matcher.FactObject;
import codeSkeleton.core.structural.matcher.MethodNodeModel;
import codeSkeleton.core.structural.matcher.StructuralMatcher;
import codeSkeleton.core.structural.matcher.TypeNodeModel;

public class CodeExampleLineMarker {
	private ArrayList<LineMarkerModel> markers = new ArrayList<LineMarkerModel>();
	private String[] lines = null;
	private int len = 0;
	private int index = 0;

	private void markCodeExample(int id) {
		// if (id >= IRInformationModel.getInstance().getExampleSize())
		// return;
		lines = IRInformationModel.getInstance().getSingleFile(id).getCode()
				.split("\n");
		len = lines.length;
		CodeExampleModel model = StructuralMatcher.getInstance()
				.getSingleExample(id);
		for (TypeNodeModel type : model
				.getExample()) {
			if (type.getKwCount() == 0)
				continue;
			setTypeDeclaration(type.getTypeNode().getName().toString());
			setMethodDeclaration(type);
		}
		// return markers;
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
		for (int i = 0; i < markers.size(); i++) {
			if (i > 0
					&& markers.get(i).getLineNo()
							- markers.get(i - 1).getLineNo() != 1)
				builder.append("<hr class=\"codesplit\" />");
			builder.append(markers.get(i).toString());
		}
		builder.append(" </ol>");
		return builder.toString();
	}

	private void addMarker(int id, LineType type) {
		if (id < len - 1)
			markers.add(new LineMarkerModel(id, type, lines[id],
					IRInformationModel.getInstance().hasKW(lines[id])));
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
			getLastIndex(first, declare.getLength());

			// int last = getLineIndex(block[block.length - 1]);
			// for (int i = first; i < last; i++)
			// addMarker(i, LineType.M_Call, lines[i]);
		}
	}

	private int getLineIndex(String name) {
		String[] terms = name.split(" ");
		boolean flag = false;
		while (index < len) {
			if (lines[index].contains(terms[0])) {
				flag = true;
				for (int i = 1; i < terms.length; i++)
					if (!lines[index].contains(terms[i])) {
						flag = false;
						break;
					}
				if (flag)
					return index;
			}
			index++;
		}
		return index;
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
