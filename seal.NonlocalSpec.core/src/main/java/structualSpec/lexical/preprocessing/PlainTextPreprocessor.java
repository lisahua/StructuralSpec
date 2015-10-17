package structualSpec.lexical.preprocessing;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import structualSpec.syntactic.parser.SimpleNamePrinter;

public class PlainTextPreprocessor extends SimpleNamePrinter {
CamelCaseSplitter splitter = CamelCaseSplitter.getInstance();
	public PlainTextPreprocessor(String outputPath) {
		super(outputPath);
	}

	public void getNameInFile(File file) {
		String fileString = null;
		String filePath = file.getAbsolutePath();
		try {
			fileString = FileUtils.readFileToString(file);
			fileString = fileString.substring(fileString
					.indexOf("public class ") + 12);
			String[] tokens = fileString.split("\\W");
			for (String token : tokens) {
				if (token.trim().length() > 1) {
					String[] splitted = splitter.executeSingleName(token);
					for (String t: splitted)
						writer.print(t+" ");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
