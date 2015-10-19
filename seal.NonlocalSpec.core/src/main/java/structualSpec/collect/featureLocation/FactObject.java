package structualSpec.collect.featureLocation;

import java.util.ArrayList;

public class FactObject {
	ArrayList<String> property = new ArrayList<String>();
	FACT fact;
	int numKeyword = 0;
	String s = "";

	public FactObject(FACT fact, String... args) {
		this.fact = fact;
		for (String arg : args) {
			property.add(arg);
			s += arg + ",";
		}
	}


	public int getNumKeyword() {
		return numKeyword;
	}


	public void setNumKeyword(int numKeyword) {
		this.numKeyword = numKeyword;
	}


	public ArrayList<String> getProperty() {
		return property;
	}

	public FACT getFact() {
		return fact;
	}

	public String toString() {
		return s;
	}
}
