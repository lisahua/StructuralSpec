package search.github;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.testng.annotations.Test;

import structualSpec.collect.partial.QueryFileInput;

public class TestCodeSearch {
	@Test
	public void testQueryExpand() {
		QueryFileInput.readQueryFile("test-output/resource/query.txt");
//		QueryFileInput.readQueryFile("test-output/resource/reformedQuery.txt");
	}

	// @Test
	public void combineFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"test-output/resource/output1.txt"));
			ArrayList<String> line1 = getStrings(reader);
			reader.close();
			reader = new BufferedReader(new FileReader(
					"test-output/resource/output2.txt"));
			ArrayList<String> line2 = getStrings(reader);
			PrintWriter writer = new PrintWriter(
					"test-output/resource/output3.txt");
			int min = Math.min(line1.size(), line2.size());
			for (int i = 0; i < min; i++) {
				writer.println(line1.get(i) + "&" + line2.get(i));
			}
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ArrayList<String> getStrings(BufferedReader reader)
			throws Exception {
		ArrayList<String> lines = new ArrayList<String>();
		String line = "";
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		return lines;
	}
}
