package structualSpec.collect.partial;

import java.io.BufferedReader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUnMarshaller {

	ObjectMapper mapper = new ObjectMapper();
	private static JsonUnMarshaller unmarshaller = new JsonUnMarshaller();

	private JsonUnMarshaller() {
	}

	public static JsonUnMarshaller getInstance() {
		return unmarshaller;
	}

	public JsonQueryResult readJsonQueryResult(BufferedReader reader) {
		try {
			return mapper.readValue(reader, JsonQueryResult.class);
		} catch (Exception e) {
			System.out.println("Search Result: null");
		}
		return null;
	}
	
	public JsonCode readJsonSourceCode(BufferedReader reader) {
		try {	
		return mapper.readValue(reader, JsonCode.class);
		} catch (Exception e) {
			System.out.println("Search Code: null");
		}
		return null;
	}
}
