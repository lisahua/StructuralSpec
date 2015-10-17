package search.github;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.testng.annotations.Test;

public class GenerateScript {
	PrintWriter writer;
	String wget = "/Users/UCLAPLSE/Documents/project/nonLocalSpec/githubArchive/fromGit/wget.sh";

	@Test
	public void generalScript() {
		try {
			// PrintWriter writer = new PrintWriter(
			// "/Users/UCLAPLSE/Documents/project/nonLocalSpec/githubArchive/pythonScript.sh");
			writer = new PrintWriter(wget);
			String[] starScope = { "%3E500", "250..500", "150..249",
					"100..149", "72..99", "57..72", "46..56", "38..45" };
			int wgetC = 0;
			for (String star : starScope) {
				for (int i = 1; i < 11; i++)
					printScript(i, star, 1);
				writer.println("sh gitClone_" + star + ".1");
				writer.close();
				writer = new PrintWriter(wget + "." + (++wgetC));
				for (int i = 11; i < 21; i++)
					printScript(i, star, 2);
				writer.println("sh gitClone_" + star + ".2");
				writer.close();
				writer = new PrintWriter(wget + (++wgetC));
				for (int i = 21; i < 31; i++)
					printScript(i, star, 3);
				writer.println("sh gitClone_" + star + ".3");
				writer.close();
				writer = new PrintWriter(wget + (++wgetC));
				for (int i = 31; i < 35; i++)
					printScript(i, star, 3);
				writer.close();
				writer = new PrintWriter(wget + (++wgetC));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void printScript(int i, String star, int round) {
		writer.println("wget \"https://api.github.com/search/repositories?q=language:Java+stars:"
				+ star + "&page=" + i + "\"");
		writer.println("python ../parseGithubJson.py  repositories\\?q\\=language\\:Java+stars\\:"
				+ star
				+ "\\&page\\="
				+ i
				+ "  >> gitClone_"
				+ star
				+ "."
				+ round);
	}
}
