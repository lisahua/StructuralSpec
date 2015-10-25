package structualSpec.syntactic.parser;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.testng.annotations.Test;

import structualSpec.config.ConfigUtility;
import structualSpec.output.view.JsonClassModel;
import structualSpec.output.view.JsonExampleCluster;
import structualSpec.output.view.JsonExampleModel;
import structualSpec.output.view.JsonMarshaller;
import structualSpec.output.view.JsonMethodModel;

public class TestASTVisitor {

	public void testJsonMarshaller() {
		JsonExampleCluster cluster = new JsonExampleCluster();

		ASTParser parser = ASTParser.newParser(AST.JLS3);
		String fileString = null;
		try {
			fileString = FileUtils.readFileToString(new File(
					ConfigUtility.codeOutputPath + "Work_TextEditor.java"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		parser.setSource(fileString.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

	}

	@Test
	public void testDummyJsonMarshaller() {
		JsonExampleCluster cluster = new JsonExampleCluster();
		JsonExampleModel textEditor = new JsonExampleModel();
		JsonClassModel textEClass = new JsonClassModel();
		JsonClassModel undoAction = new JsonClassModel();
		JsonClassModel redoAction = new JsonClassModel();
		JsonMethodModel consUndo = new JsonMethodModel();
		JsonMethodModel consRedo = new JsonMethodModel();
		JsonMethodModel actionPerformUndo = new JsonMethodModel();
		JsonMethodModel actionPerformRedo = new JsonMethodModel();
		JsonClassModel undoManager = new JsonClassModel();
		JsonMethodModel alterUndo = new JsonMethodModel();
		JsonMethodModel alterRedo = new JsonMethodModel();
		JsonMethodModel teCons = new JsonMethodModel();

		consUndo.setMethod("public UndoAction() {");
		consUndo.setAlter(new String[] { "@A.3 super(\"undo\")",
				"@B.3 setEnabled(false);" });

		actionPerformUndo
				.setMethod("public void actionPerformed(ActionEvent e) {");
		actionPerformUndo.setStataments(new String[] { "undoManager.undo();",
				"setEnabled(undoManager.canUndo());",
				"redoAction.setEnabled(undoManager.canRedo);" });

		alterUndo
				.setMethod("public void propertyChange(PropertyChangeEvent pce) {");
		alterUndo
				.setStataments(new String[] { "setEnabled(undoManager.canUndo());" });

		consRedo.setMethod("public RedoAction() {");
		consRedo.setAlter(new String[] { "@A.3 super(\"redo\")",
				"@B.3 setEnabled(false);" });
		actionPerformRedo.setStataments(new String[] { "undoManager.redo();",
				"setEnabled(undoManager.canRedo());",
				"undoAction.setEnabled(undoManager.canUndo);" });

		alterRedo
				.setMethod("public void propertyChange(PropertyChangeEvent pce) {");
		alterUndo
				.setStataments(new String[] { "setEnabled(undoManager.canUndo());" });

		undoManager
				.setClassStmt("public class TextUndoManager extends UndoManager {");

		undoAction
				.setClassStmt("private class UndoAction extends AbstractAction {");
		undoAction.setMethods(new JsonMethodModel[] { consUndo,
				actionPerformUndo });
		undoAction.setAlterMethods(new JsonMethodModel[] { alterUndo });
		undoAction.setAlter(new String[] { "@B.2 implements DocumentListener",
				"@B.4 implements PropertyChangeListener" });

		redoAction
				.setClassStmt("private class RedoAction extends AbstractAction {");
		redoAction.setMethods(new JsonMethodModel[] { consRedo,
				actionPerformRedo });
		redoAction.setAlterMethods(new JsonMethodModel[] { alterRedo });
		redoAction.setAlter(new String[] { "@B.2 implements DocumentListener",
				"@B.4 implements PropertyChangeListener" });

		teCons.setMethod("public TextEditor() {");
		teCons.setStataments(new String[] { "undoManager = new UndoManager();" });
		teCons.setAlter(new String[] {
				"@B.1 doc.addUndoableEditListener(undoManager);",
				"@B.2 doc.addDocumentListener(undoAction);",
				"@B.2 doc.addDocumentListener(redoAction);" });

		textEClass.setClassStmt("public class TextEditor extends JPane {");
		textEClass.setFields(new String[] {
				"private UndoAction undoAction = new UndoAction();",
				"private RedoAction redoAction = new RedoAction();",
				"private TextUndoManager undoManager;" });
		textEClass
				.setAlter(new String[] { "@B.1,B.2 private Document doc = textEditor.getDocument();" });
		textEClass.setMethods(new JsonMethodModel[] { teCons });
		textEClass
				.setInnerClass(new JsonClassModel[] { undoAction, redoAction });
		textEClass.setImports(new String[] {
				"import javax.swing.AbstractAction;",
				"import javax.swing.Action;", "import javax.swing.JPane;",
				"import javax.swing.text.Document;",
				"import javax.swing.undo.UndoManager;" });
		
		textEditor.setClasses(new JsonClassModel[] { textEClass, undoManager });

		cluster.setClusters(new JsonExampleModel[] { textEditor });
		cluster.setQuery("undo redo TextEditor");
		JsonMarshaller.query(cluster);
		
	}
}
