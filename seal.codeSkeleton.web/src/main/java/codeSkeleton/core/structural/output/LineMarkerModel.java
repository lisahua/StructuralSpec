package codeSkeleton.core.structural.output;

public class LineMarkerModel {
	private int lineNo;
	private LineType type;
	private boolean hasKW;
	private String line;

	public LineMarkerModel(int lNo, LineType type, String l, boolean kw) {
		lineNo = lNo;
		this.type = type;
		hasKW = kw;
		line = l;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public LineType getType() {
		return type;
	}

	public void setType(LineType type) {
		this.type = type;
	}

	public boolean isHasKW() {
		return hasKW;
	}

	public void setHasKW(boolean hasKW) {
		this.hasKW = hasKW;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String toString() {
		String lineS = " <li value=\"" + lineNo + "\">  <pre>";
		if (hasKW)
			lineS += "<strong>" + line + "</strong>";
		else
			lineS += line;
		return lineS + "</pre></li>";
	}
}
