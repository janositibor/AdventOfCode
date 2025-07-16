package tzjanosi_temp.y2015.day12;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class JsonHandler {
    private String input;
    private static final Pattern INT_PATTERN = Pattern.compile("^-?\\d+$");

    public JsonHandler(String input) {
        this.input = input;
    }

    public int getNumbers(Predicate<JsonNode> condition) {
        JsonNode root = getRoot();
        return getNumbersFromNode(root, condition);
    }

    private int getNumbersFromNode(JsonNode node, Predicate<JsonNode> condition) {
        int output = 0;
        if (node.isValueNode()) {
            output += valueFromValueNode(node);
        } else if (node.isObject()) {
            output += valueFromObjectNode(node, condition);

        } else if (node.isArray()) {
            output += valueFromArrayNode(node, condition);
        }
        return output;
    }

    private int valueFromArrayNode(JsonNode node, Predicate<JsonNode> condition) {
        int output = 0;
        for (JsonNode nextNode : node) {
            output += getNumbersFromNode(nextNode, condition);
        }
        return output;
    }

    private int valueFromObjectNode(JsonNode node, Predicate<JsonNode> condition) {
        int output = 0;
        if (!condition.test(node)) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                output += getNumbersFromNode(field.getValue(), condition);
            }
        }
        return output;
    }

    private int valueFromValueNode(JsonNode node) {
        int output = 0;
        if (isIntegerRegex(node.asText())) {
            output += Integer.parseInt(node.asText());
        }
        return output;
    }

    public boolean containsRed(JsonNode node) {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            if ("red".equals(field.getValue().asText())) {
                return true;
            }
        }
        return false;
    }

    private JsonNode getRoot() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode output = null;
        try {
            JsonNode root = mapper.readTree(input);
            output = root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getSumOfNumbers() {
        List<Integer> numbers = getNumbers();
        return numbers.stream()
                .mapToInt(x -> x)
                .sum();
    }

    public List<Integer> getNumbers() {
        String[] words = input.split("[\\[\\],\"{}:]");
        return Arrays.stream(words)
                .filter(this::isIntegerRegex)
                .map(Integer::valueOf)
                .toList();
    }

    public boolean isIntegerRegex(String s) {
        return s != null && INT_PATTERN.matcher(s.trim()).matches();
    }
}
