package structualSpec.lexical.query;

public class InputParser {

	public void parseInput(String input) {
		
	}
}

enum InputType {
	NONE(0),  Action(1), Scope(2), Expect(4),FULL(7);
	 private int type;
	InputType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
}