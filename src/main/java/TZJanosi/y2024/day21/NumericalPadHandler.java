package TZJanosi.y2024.day21;

import java.util.Map;
import java.util.Set;

public class NumericalPadHandler {
    private NumericalPad numericalPad = new NumericalPad("A");
    private String message;

    public NumericalPadHandler(String message) {
        this.message = message;
    }

    public Set<String> code() {
        String[] messageasArray = message.split("");
        NumericalPad actual = numericalPad;
        actual.setWays(Set.of(""));
        for (int i = 0; i < messageasArray.length; i++) {
            NumericalPad next = actual.next(messageasArray[i]);
            actual = next;
        }
        return actual.getWays();
    }

    public String deCode() {
        String output = "";
        Map<String, Coordinate> buttons = Map.of(">", new Coordinate(1, 0),
                "<", new Coordinate(-1, 0),
                "^", new Coordinate(0, -1),
                "v", new Coordinate(0, 1)
        );

        String[] messageasArray = message.split("");
        Coordinate actualCoordinate = ButtonForNumericalPad.BUTTON_A.getCoordinate();
        for (int i = 0; i < messageasArray.length; i++) {
            String actualKey = messageasArray[i];
            if (actualKey.equals("A")) {
                ButtonForNumericalPad actual = numericalPad.findButtonForCoordinate(actualCoordinate);
                output += actual.getValue();
            } else {
                actualCoordinate.shift(buttons.get(actualKey));
            }
        }
        return output;
    }
}
