package codeSkeleton.core.lexical.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import codeSkeleton.core.config.ConfigUtility;

public class PlainTextPreprocessor {
	static CamelCaseSplitter splitter = CamelCaseSplitter.getInstance();

	public static void parseToIRInput() throws Exception {
		// String codeDir = ConfigUtility.codeOutputPath;
		// String irDir = ConfigUtility.irOutputPath;

		File codeDir = new File(ConfigUtility.codeOutputPath);
		if (!codeDir.isDirectory())
			return;
		File[] fileList = codeDir.listFiles();
		PrintWriter writer = null;
		BufferedReader reader = null;
		String line = "";
		for (File file : fileList) {
			writer = new PrintWriter(ConfigUtility.irOutputPath
					+ file.getName());
			reader = new BufferedReader(new FileReader(file));
			while ((line= reader.readLine())!=null) {
				writer.println(splitter.executeSingleLine(line));
			}
		}
	}

}
