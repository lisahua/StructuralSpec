package codeSkeleton.core.structural.matcher;

import java.util.ArrayList;

import codeSkeleton.core.config.ConfigUtility;

public class FactObject {
	ArrayList<String> property = new ArrayList<String>();
	FACT fact;
	boolean kw = false;
	String s = "";

	public FactObject(FACT fact, String... args) {
		this.fact = fact;
		if (fact == FACT.calls && args.length > 2)
			s = args[0] + "," + args[2];
		else {
			for (String arg : args) {
				if (arg.length() > 1 && !ConfigUtility.winnowKW.contains(arg)) {
					property.add(arg);
					s += arg + ",";
				}
			}
		}
	}

	public boolean isKw() {
		return kw;
	}

	public void setKw(boolean kw) {
		this.kw = kw;
	}

	public ArrayList<String> getProperty() {
		return property;
	}

	public FACT getFact() {
		return fact;
	}

	public String toString() {
		// switch (fact) {
		// case calls:
		// if (property.size() == 1)
		// return property.get(0);
		// if (property.size() == 2)
		// return property.get(1);
		//
		// }
		return s;
	}
}
