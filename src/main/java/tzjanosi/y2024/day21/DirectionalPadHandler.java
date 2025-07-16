package tzjanosi.y2024.day21;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectionalPadHandler {
    private static Map<StringIntegerPair, Long> deepMemory = new ConcurrentHashMap<>();
    private DirectionalPad directionalPad = new DirectionalPad("A");
    private NumericalPadHandler numericalPadHandler;


    public DirectionalPadHandler() {
    }

    public DirectionalPadHandler(String input) {
        numericalPadHandler = new NumericalPadHandler(input);
    }

    public Set<String> code(String message) {
        String[] messageasArray = message.split("");
        DirectionalPad actual = directionalPad;
        actual.setWays(Set.of(""));
        for (int i = 0; i < messageasArray.length; i++) {
            DirectionalPad next = actual.next(messageasArray[i]);
            actual = next;
        }
        return actual.getWays();
    }

    public Set<String> codeSet(Set<String> set) {
        Set<String> output = new HashSet<>();
        for (String message : set) {
            output.addAll(code(message));
        }
        return output;
    }

    public Set<String> codeTwice(Set<String> set) {
        Set<String> tempCodeSet = codeSet(set);
        return codeSet(tempCodeSet);
    }

    public Set<String> codeRepeated(Set<String> set, int repetition) {
        Set<String> from = set;
        Set<String> to = null;
        int i = 0;
        while (i < repetition) {
            to = codeSet(from);
            from = to;
            i++;
        }
        return to;
    }


    public Set<String> codeTotal() {
        return codeTwice(numericalPadHandler.code());
    }

    public long complexity(String input) {
        String numberAsString = input.substring(0, input.length() - 1);
        int numberAsint = Integer.parseInt(numberAsString, 10);
        numericalPadHandler = new NumericalPadHandler(input);
        Set<String> codeFromNumericalPad = numericalPadHandler.code();
        Set<String> codeTwice = codeTwice(codeFromNumericalPad);
        int length = codeTwice.stream().mapToInt(String::length).min().getAsInt();
        return numberAsint * length;
    }

    public long complexityPart2(String input, int numberOfDirectionalPads) {
        String numberAsString = input.substring(0, input.length() - 1);
        long numberAsLong = Long.parseLong(numberAsString, 10);
        numericalPadHandler = new NumericalPadHandler(input);
        Set<String> codeFromNumericalPad = numericalPadHandler.code();
        long length = calculateMinLengthFromSet(codeFromNumericalPad, numberOfDirectionalPads);
        return numberAsLong * length;
    }

    private long calculateMinLengthFromSet(Set<String> inputSet, int level) {
        return inputSet.stream().mapToLong(s -> calculateMinLength(s, level)).min().getAsLong();
    }

    private List<String> extractASeparatedSteps(String input) {
        List<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile(".*?A");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            output.add(matcher.group());
        }

        return output;
    }

    private long calculateMinLength(String s, int level) {
        long output = 0;
        if (level == 1) {
            output = s.length();
        } else {
            List<String> steps = extractASeparatedSteps(s);
            for (int i = 0; i < steps.size(); i++) {
                String step = steps.get(i);
                StringIntegerPair key = new StringIntegerPair(step, level);
                if (deepMemory.containsKey(key)) {
                    output += deepMemory.get(key);
                } else {
                    Set<String> codes = code(step);
                    long actualValue = calculateMinLengthFromSet(codes, level - 1);
                    addToDeepMemory(step, level, actualValue);
                    output += actualValue;
                }
            }
        }
        return output;
    }

    private void addToDeepMemory(String letter, int level, long actualValue) {
        StringIntegerPair key = new StringIntegerPair(letter, level);
        deepMemory.put(key, actualValue);
    }

    public long totalComplexity(List<String> input) {
        return input.stream().mapToLong(this::complexity).sum();
    }

    public long totalComplexityPart2(List<String> input, int numberOfDirectionalPads) {
        return input.stream().mapToLong(s -> complexityPart2(s, numberOfDirectionalPads)).sum();
    }

    public String deCode(String message) {
        StringBuffer output = new StringBuffer("");
        Map<String, Coordinate> buttons = Map.of(">", new Coordinate(1, 0),
                "<", new Coordinate(-1, 0),
                "^", new Coordinate(0, -1),
                "v", new Coordinate(0, 1)
        );

        String[] messageasArray = message.split("");
        Coordinate actualCoordinate = ButtonForDirectionalPad.BUTTON_A.getCoordinate();
        for (int i = 0; i < messageasArray.length; i++) {
            String actualKey = messageasArray[i];
            if ("A".equals(actualKey)) {
                ButtonForDirectionalPad actual = directionalPad.findButtonForCoordinate(actualCoordinate);
                output.append(actual.getValue());
            } else {
                actualCoordinate.shift(buttons.get(actualKey));
            }
        }
        return output.toString();
    }

    public String deCodeTwice(String message) {
        String tempCode = deCode(message);
        return deCode(tempCode);
    }

    public String deCodeTotal(String message) {
        String tempCode = deCodeTwice(message);
        numericalPadHandler = new NumericalPadHandler(tempCode);
        return numericalPadHandler.deCode();
    }


}
