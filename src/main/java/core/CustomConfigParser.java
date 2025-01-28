package core;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomConfigParser {
//    static String fileName = "USA_1943.hoi4";
    static String fileName = "abc_2.txt";
    //    static Path filePath = Paths.get("C:\\Users\\user\\Documents\\Paradox Interactive\\Hearts of Iron IV\\save games\\" + fileName); // Change this to your file's path
    static Path filePath = Paths.get("D:\\projectAlpha\\" + fileName);

    public static void main(String[] args) {

        try {
            List<String> lines = Files.lines(filePath).collect(Collectors.toList());
            String data = String.join(System.lineSeparator(), lines);

            Map<String, Object> parsedData = parseData(data);
            String jsonOutput = convertToJson(parsedData);
            System.out.println(jsonOutput);
        } catch (IOException e) {
            System.err.println("Error while parsing data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Map<String, Object> parseData(String inputData) {
        Map<String, Object> result = new LinkedHashMap<>();
        String[] lines = inputData.split("\\n");
        Deque<Map<String, Object>> stack = new ArrayDeque<>();
        Map<String, Object> currentMap = result;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue; // Skip empty lines

            if (line.endsWith("{")) {
                String key = line.substring(0, line.length() - 1).trim();
                Map<String, Object> newMap = new LinkedHashMap<>();
                currentMap.put(key, newMap);
                stack.push(currentMap);
                currentMap = newMap; // Move into the new map
            } else if (line.equals("}")) {
                if (!stack.isEmpty()) {
                    currentMap = stack.pop(); // Return to previous map
                } else {
                    System.err.println("Warning: Unmatched closing brace in line: " + line);
                }
            } else if (line.contains("=")) {
                String[] parts = line.split("=", 2);
                if (parts.length != 2) {
                    System.err.println("Warning: Invalid line format (missing '=' or invalid entry): " + line);
                    continue; // Skip invalid lines
                }
                String key = parts[0].trim();
                String value = parts[1].trim();

                // Check if value is a new nested structure or a simple value
                if (value.startsWith("{") || value.startsWith("[")) {
                    value = value.replaceAll("^\\{|\\}\\s*$", "").trim();
                    currentMap.put(key, parseValue(value));
                } else if (value.endsWith("}")) {
                    currentMap.put(key, parseValue(value.substring(0, value.length() - 1)));
                } else if (value.contains("\t")) { // For fired_event_names with tab separation
                    currentMap.put(key, parseMultipleValues(value));
                } else {
                    value = value.replaceAll("^\"|\"$", "");
                    currentMap.put(key, parseValue(value));
                }
            } else {
                System.err.println("Warning: Unrecognized line format: " + line);
            }
        }

        // Check for unmatched braces at the end of parsing
        if (!stack.isEmpty()) {
            System.err.println("Warning: Unmatched opening brace(s) detected.");
        }

        return result;
    }

    private static Object parseValue(String value) {
        if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no")) {
            return value.equalsIgnoreCase("yes");
        } else if (value.matches("\\d+")) {
            return Integer.parseInt(value);
        } else if (value.matches("\\d+\\.\\d+")) {
            return Double.parseDouble(value);
        } else if (value.matches("-?\\d+")) {
            return Long.parseLong(value);
        } else {
            return value;
        }
    }

    private static List<String> parseMultipleValues(String value) {
        List<String> valuesList = new ArrayList<>();
        String[] elements = value.split("\\s+");
        for (String element : elements) {
            if (element.trim().startsWith("id=")) {
                valuesList.add(element.trim().substring(3));
            }
        }
        return valuesList;
    }

    private static String convertToJson(Map<String, Object> data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }
}