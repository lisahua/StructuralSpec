package codeSkeleton.core.collect;

import java.util.List;

import codeSkeleton.core.collect.query.JsonSourceCode;

public class IRInformationModel {
	private String[] terms;
	private List<JsonSourceCode> fileNames;
	private static IRInformationModel subscriber = new IRInformationModel();

	private IRInformationModel() {
	}

	public static IRInformationModel getInstance() {
		return subscriber;
	}

	public void setQueryTerms(String[] term) {
		terms = term;
	}

	public String[] getTerms() {
		return terms;
	}

	public List<JsonSourceCode> getFileInfo() {
		return fileNames;
	}

	public JsonSourceCode getSingleFile(int i) {
		if (i<fileNames.size())
		return fileNames.get(i);
		return null;
	}

	public int getExampleSize() {
		return fileNames.size();
	}

	public void setFileInfo(List<JsonSourceCode> fileNames) {
		this.fileNames = fileNames;
	}

	public boolean hasKW(String line) {
		for (String term : terms) {
			if (term.length() < 2)
				continue;
			if (line.toLowerCase().trim().contains(term.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
