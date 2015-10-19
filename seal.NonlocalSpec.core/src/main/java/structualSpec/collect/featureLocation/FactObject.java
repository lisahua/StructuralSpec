package structualSpec.collect.featureLocation;

import java.util.ArrayList;

public class FactObject {
	ArrayList<String> property = new ArrayList<String>();
	FACT fact;
	boolean hasKeyword = false;
	String s = "";

	public FactObject(FACT fact, String... args) {
		this.fact = fact;
		for (String arg : args) {
			property.add(arg);
			s += arg + ",";
		}
	}

	public boolean isHasKeyword() {
		return hasKeyword;
	}

	public void setHasKeyword(boolean hasKeyword) {
		this.hasKeyword = hasKeyword;
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
