package core;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomFormatParser {

    public static void main(String[] args) {
        String filePath = "D:\\projectAlpha\\abc_2.txt"; // Change to the path of your file
        try {
            Map<String, Object> parsedData = parseFile(filePath);
            System.out.println(parsedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> parseFile(String filePath) throws IOException {
        Map<String, Object> result = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentKey = null;
            Map<String, Object> currentMap = result;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                // Check for new map start
                if (line.endsWith("{")) {
                    currentKey = line.substring(0, line.length() - 1).trim();
                    Map<String, Object> newMap = new HashMap<>();
                    currentMap.put(currentKey, newMap);
                    currentMap = newMap; // Move deeper into the nested map
                }
                // Check for nested key-value pairs
                else if (line.endsWith("}")) {
                    currentMap = getParentMap(result, currentMap); // Move back up
                }
                // Check for key-value pairs
                else if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String key = parts[0].trim();
                    String value = parts[1].trim().replaceAll("[\"{}]", ""); // Clean value

                    // Attempt to parse as a number
                    Object parsedValue = parseValue(value);
                    currentMap.put(key, parsedValue);
                }
            }
        }
        return result;
    }

    private static Object parseValue(String value) {
        // Try to parse as a number first
        if (value.matches("\\d+\\.\\d+")) {
            return Double.parseDouble(value);
        } else if (value.matches("\\d+")) {
            return Integer.parseInt(value);
        } else {
            return value; // Return as a string if not a number
        }
    }

    private static Map<String, Object> getParentMap(Map<String, Object> rootMap, Map<String, Object> currentMap) {
        // This method can be enhanced to keep track of the parent
        // For this implementation, please note we won't be able to track levels precisely.
        Map<String, Object> parentMap = rootMap;  // Fallback
        for (Map.Entry<String, Object> entry : rootMap.entrySet()) {
            if (entry.getValue() == currentMap) {
                parentMap = entry.getValue() instanceof Map ? (Map<String, Object>) entry.getValue() : rootMap;
            }
            // Other logic can be implemented as needed
        }
        return parentMap;
    }
}
