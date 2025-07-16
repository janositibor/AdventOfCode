package tzjanosi.y2024.day21;

import java.util.*;
import java.util.stream.Collectors;

public class NumericalPad {
    private String message = "";
    private ButtonForNumericalPad button;
    private Set<String> ways = new HashSet<>();
    private final Coordinate forbiddenPlace = new Coordinate(0, 3);

    public NumericalPad(String key) {
        this.button = findButtonForValue(key);
    }

    public NumericalPad(ButtonForNumericalPad button) {
        this.button = button;
    }

    private void addWay(String way) {
        ways.add(way);
    }

    private boolean isValidRoute(String route) {
        Map<String, Coordinate> directions = Map.of(">", new Coordinate(1, 0),
                "<", new Coordinate(-1, 0),
                "^", new Coordinate(0, -1),
                "v", new Coordinate(0, 1)
        );

        Coordinate actualCoordinate = button.getCoordinate();
        for (int i = 0; i < route.length() - 1; i++) {
            Coordinate shift = directions.get(String.valueOf(route.charAt(i)));
            actualCoordinate.shift(shift);
            if (actualCoordinate.equals(forbiddenPlace)) {
                return false;
            }
        }
        return true;
    }

    public NumericalPad next(String input) {
        ButtonForNumericalPad nextButton = findButtonForValue(input);
        String way = getWay(nextButton);
        Set<String> validWays = getPermutations(way);
        NumericalPad next = new NumericalPad(nextButton);
//        next.setParent(this);
        for (String oldWay : ways) {
            for (String newWay : validWays) {
                next.addWay(oldWay + newWay + "A");
            }
        }
//        next.setWays(validWays);
//        next.setMessage(message+shuffle(way));
//        next.setMessage(message+way);
        return next;
    }

    private Set<String> getPermutations(String str) {
        Set<String> result = new HashSet<>();
        permute("", str, result);
        if (result.size() == 1) {
            return result;
        } else {
            return result.stream().filter(this::isValidRoute).collect(Collectors.toSet());
        }
    }

    private void permute(String prefix, String remaining, Set<String> result) {
        int n = remaining.length();
        if (n == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permute(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1, n),
                        result);
            }
        }
    }

    private String getWay(ButtonForNumericalPad nextButton) {
        StringBuffer output = new StringBuffer("");
        String rowAdjustment = getRowAdjustment(nextButton);
        String columnAdjustment = getColumnAdjustment(nextButton);
        if (button.getCoordinate().getY() == 3) {
            output.append(rowAdjustment).append(columnAdjustment);
        } else {
            output.append(columnAdjustment).append(rowAdjustment);
        }
        return output.toString();
    }

    private String getColumnAdjustment(ButtonForNumericalPad nextButton) {
        String character = ">";
        int value = nextButton.getCoordinate().getX() - button.getCoordinate().getX();
        if (value < 0) {
            character = "<";
        }
        return character.repeat(Math.abs(value));
    }

    private String getRowAdjustment(ButtonForNumericalPad nextButton) {
        String character = "v";
        int value = nextButton.getCoordinate().getY() - button.getCoordinate().getY();
        if (value < 0) {
            character = "^";
        }
        return character.repeat(Math.abs(value));
    }

    private ButtonForNumericalPad findButtonForValue(String input) {
        return Arrays.stream(ButtonForNumericalPad.values())
                .filter(b -> b.getValue().equals(input))
                .findFirst()
                .get();
    }

    public ButtonForNumericalPad findButtonForCoordinate(Coordinate input) {
        return Arrays.stream(ButtonForNumericalPad.values())
                .filter((ButtonForNumericalPad b) -> (b.getCoordinate().equals(input)))
                .findFirst()
                .get();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setWays(Set<String> ways) {
        this.ways = ways;
    }

    public Set<String> getWays() {
        return ways;
    }
}
