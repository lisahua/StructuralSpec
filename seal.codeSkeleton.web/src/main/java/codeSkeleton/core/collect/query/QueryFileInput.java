package codeSkeleton.core.collect.query;

import java.io.BufferedReader;
import java.io.FileReader;

public class QueryFileInput {

	public static void readQueryFile(String path) {
		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			while ((line = reader.readLine()) != null) {
				System.out.print(line);
				WebQueryTimer.start();
				SourceCodeCollector.filterIdenticalFiles(WebContentCollector
						.queryForAllResults(line.trim().replace(" ", "+")));
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
