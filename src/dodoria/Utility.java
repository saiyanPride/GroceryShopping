package dodoria;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Utility {
	public static void info(String message) {
		System.out.println("[INFO] " + message);
	}

	public static void warn(String message) {
		System.out.println("[WARNING] " + message);
	}

	public static void error(String message) {
		System.out.println("[ERROR] " + message);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromJsonFile()
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> groceryOrderSpecification = null;
		// Convert JSON string from file to Object
		groceryOrderSpecification = (Map<String, Object>) mapper.readValue(
				new File(ConfigurationConstants.SHOPPING_LIST_FILE_PATH),
				Map.class);
		return groceryOrderSpecification;
	}
}
