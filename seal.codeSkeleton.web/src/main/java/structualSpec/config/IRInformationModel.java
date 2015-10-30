package structualSpec.config;

import java.util.List;

import structualSpec.collect.partial.JsonSourceCode;

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

	public void setFileInfo(List<JsonSourceCode> fileNames) {
		this.fileNames = fileNames;
	}
	
	
}
