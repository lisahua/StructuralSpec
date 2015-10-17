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
		
			JsonQueryResult result = mapper.readValue(reader, JsonQueryResult.class);
			for (ResultItem item:result.getResults()) {
				System.out.println(item.getId()+","+item.getFilename());
			}
			return result;
		} catch (Exception e) {
			System.out.println("Search Result: null");
		}
		return null;
	}
	
	public JsonSourceCode readJsonSourceCode(BufferedReader reader) {
		try {
			
			JsonSourceCode code = mapper.readValue(reader, JsonSourceCode.class);
			return code;
		} catch (Exception e) {
			System.out.println("Search Code: null");
		}
		return null;
	}
}
