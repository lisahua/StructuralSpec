package structualSpec.collect.partial;

public class JsonSourceCode {
	private String fileName;
	private long id = 0;

	public JsonSourceCode (long id,String fileName) {
		this.id = id;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
