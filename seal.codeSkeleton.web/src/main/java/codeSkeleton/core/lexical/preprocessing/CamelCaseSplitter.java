package codeSkeleton.core.lexical.preprocessing;

public class CamelCaseSplitter {
	private static CamelCaseSplitter splitter = new CamelCaseSplitter();

	public static CamelCaseSplitter getInstance() {
		return splitter;
	}

	private CamelCaseSplitter() {

	}

	private boolean allUpperCase(String name) {
		char[] charArray = name.toCharArray();
		for (char c : charArray) {
			if (c >= 'a')
				return false;
		}
		return true;
	}

	public String[] executeSingleName(String name) {
		String[] tokens;
		if (allUpperCase(name)) {
			tokens = name.split("_");
		} else {
			tokens = name.split("(?=[A-Z][^A-Z])|_");
		}

		return tokens;
	}

	public String splitSingleName(String name) {
		String[] tokens = executeSingleName(name);
		String splitted = "";
		for (String token : tokens)
			splitted += token + " ";
		return splitted.trim();
	}
	
	public String executeSingleLine(String line) {
		String[] tokens = line.split(" ");
		String output = "";
		for (String token: tokens) {
			output += splitSingleName(token)+" "; 
		}
		return output;
	}
}
