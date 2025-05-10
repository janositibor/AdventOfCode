package TZJanosi.y2024.day21;

import java.util.*;
import java.util.stream.Collectors;

public class DirectionalPad {
    private static Map<DirectionButtonPairs, Set<String>> memory = new HashMap<>();
    private String message = "";
    private ButtonForDirectionalPad button;
    private Set<String> ways = new HashSet<>();
    private final Coordinate forbiddenPlace = new Coordinate(0, 0);

    public DirectionalPad(String key) {
        this.button = findButtonForValue(key);
    }

    public DirectionalPad(ButtonForDirectionalPad button) {
        this.button = button;
    }

    private void addWay(String way) {
        ways.add(way);
    }

    private void learn(ButtonForDirectionalPad from, ButtonForDirectionalPad to, Set<String> ways) {
        memory.put(new DirectionButtonPairs(from, to), ways);
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


    public DirectionalPad next(String input) {
        ButtonForDirectionalPad nextButton = findButtonForValue(input);
        DirectionalPad next = new DirectionalPad(nextButton);
        Set<String> validWaysWithClosingA;
        DirectionButtonPairs directionButtonPairsForMemory = new DirectionButtonPairs(this.button, nextButton);
        if (!memory.containsKey(directionButtonPairsForMemory)) {
            String way = getWay(nextButton);
            Set<String> validWays = getPermutations(way);
            validWaysWithClosingA = extendVvűalidWaysWithClosingA(validWays);
            learn(this.button, nextButton, validWaysWithClosingA);
        } else {
            validWaysWithClosingA = memory.get(directionButtonPairsForMemory);
        }
        for (String oldWay : ways) {
            for (String newWay : validWaysWithClosingA) {
                next.addWay(oldWay + newWay);
            }
        }
        return next;
    }

    private Set<String> extendVvűalidWaysWithClosingA(Set<String> input) {
        return input.stream().map(s -> s + "A").collect(Collectors.toSet());
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

    private String getWay(ButtonForDirectionalPad nextButton) {
        String output = "";
        String rowAdjustment = getRowAdjustment(nextButton);
        String columnAdjustment = getColumnAdjustment(nextButton);
//        if(button.getCoordinate().getY()==0){
//            output+=(rowAdjustment+columnAdjustment);
//        }
//        else{
//            output+=(columnAdjustment+rowAdjustment);
//        }
        if (button.getCoordinate().getX() == 0) {
            output += (columnAdjustment + rowAdjustment);
        } else {
            output += (rowAdjustment + columnAdjustment);
        }
        return output;
    }

    private String getColumnAdjustment(ButtonForDirectionalPad nextButton) {
        String character = ">";
        int value = nextButton.getCoordinate().getX() - button.getCoordinate().getX();
        if (value < 0) {
            character = "<";
        }
        return character.repeat(Math.abs(value));
    }

    private String getRowAdjustment(ButtonForDirectionalPad nextButton) {
        String character = "v";
        int value = nextButton.getCoordinate().getY() - button.getCoordinate().getY();
        if (value < 0) {
            character = "^";
        }
        return character.repeat(Math.abs(value));
    }

    private ButtonForDirectionalPad findButtonForValue(String input) {
        return Arrays.stream(ButtonForDirectionalPad.values())
                .filter(b -> b.getValue().equals(input))
                .findFirst()
                .get();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ButtonForDirectionalPad findButtonForCoordinate(Coordinate input) {
        return Arrays.stream(ButtonForDirectionalPad.values())
                .filter(b -> b.getCoordinate().equals(input))
                .findFirst()
                .get();
    }

    public ButtonForDirectionalPad getButton() {
        return button;
    }

    public Set<String> getWays() {
        return ways;
    }

    public void setWays(Set<String> ways) {
        this.ways = ways;
    }
}
