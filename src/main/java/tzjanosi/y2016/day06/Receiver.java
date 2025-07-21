package tzjanosi.y2016.day06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.ToIntFunction;

public class Receiver {
    private List<Map<Character, Integer>> repeatedMessage = new ArrayList<>();

    public Receiver(List<String> input) {
        String message = input.get(0);
        for (int i = 0; i < message.length(); i++) {
            Map<Character, Integer> letterMap = new ConcurrentHashMap<>();
            for (int j = 0; j < input.size(); j++) {
                char actualLetter = input.get(j).charAt(i);
                if (!letterMap.containsKey(actualLetter)) {
                    letterMap.put(actualLetter, 0);
                }
                letterMap.put(actualLetter, letterMap.get(actualLetter) + 1);
            }
            repeatedMessage.add(letterMap);
        }
    }

    public String getMessage() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < repeatedMessage.size(); i++) {
            Map<Character, Integer> letterMap = repeatedMessage.get(i);
            String findLetter = letterMap.entrySet().stream()
                    .sorted(
                            Comparator.comparingInt((ToIntFunction<Map.Entry<Character, Integer>>) Map.Entry::getValue).reversed()
                    )
                    .map(e -> String.valueOf(e.getKey()))
                    .findFirst()
                    .get();
            output.append(findLetter);
        }
        return output.toString();
    }
}
