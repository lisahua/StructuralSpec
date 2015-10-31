package codeSkeleton.core.structural.matcher;

import java.util.ArrayList;

import codeSkeleton.core.config.ConfigUtility;

public class FactObject {
	ArrayList<String> property = new ArrayList<String>();
	FACT fact;
	int numKeyword = 0;
	String s = "";

	public FactObject(FACT fact, String... args) {
		this.fact = fact;
		for (String arg : args) {
			if (arg.length() > 1 && !ConfigUtility.winnowKW.contains(arg)) {
				property.add(arg);
				s += arg + ",";
			}
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
//		switch (fact) {
//		case calls:
//			if (property.size() == 1)
//				return property.get(0);
//			if (property.size() == 2)
//				return property.get(1);
//
//		}
		return s;
	}
}
