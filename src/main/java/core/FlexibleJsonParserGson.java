package core;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlexibleJsonParserGson {
    public static void main(String[] args) {
        // Example JSON input (can modify to test various structures)
        String json = "save_version=15\n" +
                "cosmetic_tag=\"USA_50\"\n" +
                "dlcs=1023\n" +
                "session=175004\n" +
                "speed=0";

        // Parse JSON with Gson
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);

        // Convert to Collection
        Object result = parseJsonElement(jsonElement);
        System.out.println(result);
    }

    private static Object parseJsonElement(JsonElement element) {
        if (element.isJsonObject()) {
            Map<String, Object> result = new HashMap<>();
            JsonObject jsonObject = element.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                result.put(entry.getKey(), parseJsonElement(entry.getValue()));
            }
            return result;
        } else if (element.isJsonArray()) {
            List<Object> resultList = new ArrayList<>();
            JsonArray jsonArray = element.getAsJsonArray();
            for (JsonElement arrayElement : jsonArray) {
                resultList.add(parseJsonElement(arrayElement));
            }
            return resultList;
        } else if (element.isJsonPrimitive()) {
            return element.getAsString(); // returning as text for simplicity; modify as needed for numbers or booleans
        }
        return null; // This should not happen
    }
}