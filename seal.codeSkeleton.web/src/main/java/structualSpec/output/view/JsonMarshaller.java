package structualSpec.output.view;

import java.io.File;

import structualSpec.config.ConfigUtility;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMarshaller {
	private static ObjectMapper mapper = new ObjectMapper();

	public static  void query(String query) {
		JsonExampleCluster cluster = new JsonExampleCluster();
		try {
			mapper.writeValue(new File(ConfigUtility.codeOutputPath + query
					+ ".json"), cluster);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static  void query(JsonExampleCluster cluster) {
		try {
			mapper.writeValue(new File(ConfigUtility.codeOutputPath + cluster.getQuery()
					+ ".json"), cluster);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
