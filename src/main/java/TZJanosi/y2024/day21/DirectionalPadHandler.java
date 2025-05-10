package TZJanosi.y2024.day21;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirectionalPadHandler {
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


    public Set<String> codeTotal() {
        return codeTwice(numericalPadHandler.code());
    }

    public long complexity(String input) {
        String numberAsString = input.substring(0, input.length() - 1);
        int numberAsint = Integer.parseInt(numberAsString, 10);
        numericalPadHandler = new NumericalPadHandler(input);
        Set<String> codeFromNumericalPad = numericalPadHandler.code();
        Set<String> codeTwice = codeTwice(codeFromNumericalPad);
        int length = codeTwice.stream().mapToInt(s -> s.length()).min().getAsInt();
        return numberAsint * length;
    }

    public long totalComplexity(List<String> input) {
        return input.stream().mapToLong(this::complexity).sum();
    }

    public String deCode(String message) {
        String output = "";
        Map<String, Coordinate> buttons = Map.of(">", new Coordinate(1, 0),
                "<", new Coordinate(-1, 0),
                "^", new Coordinate(0, -1),
                "v", new Coordinate(0, 1)
        );

        String[] messageasArray = message.split("");
        Coordinate actualCoordinate = ButtonForDirectionalPad.BUTTON_A.getCoordinate();
        for (int i = 0; i < messageasArray.length; i++) {
            String actualKey = messageasArray[i];
            if (actualKey.equals("A")) {
                ButtonForDirectionalPad actual = directionalPad.findButtonForCoordinate(actualCoordinate);
                output += actual.getValue();
            } else {
                actualCoordinate.shift(buttons.get(actualKey));
            }
        }
        return output;
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
