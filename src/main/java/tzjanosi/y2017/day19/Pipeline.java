package tzjanosi.y2017.day19;

import java.util.*;

public class Pipeline {
    private Set<Sign> signs = new HashSet<>();

    public Pipeline(List<String> input) {
        processInput(input);
    }

    public String read() {
        StringBuilder output = new StringBuilder();
        Sign actual = findStart();
//        System.out.println(String.format("Start: %s",actual.toString()));
        Optional<Coordinate> optionalNextDirection = Optional.of(new Coordinate(0, 1));
        while (optionalNextDirection.isPresent()) {
            Coordinate direction = optionalNextDirection.get();
//            System.out.println(String.format("direction: %s",direction));
            Sign next = findNextSign(actual, direction);
//            System.out.println(String.format("next: %s",next));
            String text = findLettersBetween(actual, next);
            if (!text.isEmpty()) {
                output.append(text);
            }
            Node node = new Node(next.getCoordinate(), direction);
            optionalNextDirection = node.findOutGoingDirection(signs);
            actual = next;
        }
        return output.toString();
    }

    private String findLettersBetween(Sign actual, Sign next) {
        return signs.stream()
                .filter(Sign::isLetter)
                .filter(s -> s.getCoordinate().between(actual.getCoordinate(), next.getCoordinate()))
                .sorted(Comparator.comparingDouble(s -> actual.getCoordinate().distance(s.getCoordinate())))
                .map(s -> String.valueOf(s.getValue()))
                .reduce("", (s1, s2) -> s1 + s2);
    }

    private Optional<Sign> findNextSignByChar(Sign actual, Coordinate direction, char charToFind) {
        return signs.stream()
                .filter(s -> s.getValue() == charToFind)
                .filter(s -> inLine(s, actual, direction))
                .sorted(Comparator.comparingDouble(s -> actual.getCoordinate().distance(s.getCoordinate())))
                .findFirst()
                ;
    }

    private Sign findNextSign(Sign actual, Coordinate direction) {
        Optional<Sign> optionalNextPlus = findNextSignByChar(actual, direction, '+');
        Optional<Sign> optionalNextSpace = findNextSignByChar(actual, direction, ' ');
        if (optionalNextSpace.isEmpty()) {
            return optionalNextPlus.get();
        }
        if (optionalNextPlus.isEmpty()) {
            return optionalNextSpace.get();
        }
        Coordinate spaceCoordinate = optionalNextSpace.get().getCoordinate();
        Coordinate plusCoordinate = optionalNextPlus.get().getCoordinate();
        Coordinate actualCoordinate = actual.getCoordinate();
        if (actualCoordinate.distance(plusCoordinate) < actualCoordinate.distance(spaceCoordinate)) {
            return optionalNextPlus.get();
        }
        return optionalNextSpace.get();
    }

    private boolean inLine(Sign signToCheck, Sign actual, Coordinate direction) {
        Coordinate difference = signToCheck.getCoordinate().minus(actual.getCoordinate());
        return difference.isSameDirection(direction);
    }

    private Sign findStart() {
        return signs.stream()
                .filter(s -> s.getValue() == '|')
                .filter(s -> s.getCoordinate().getY() == 0)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No start found!"));
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i), i);
        }
    }

    private void processLine(String line, int y) {
        char[] lineAsChars = line.toCharArray();
        for (int i = 0; i < lineAsChars.length; i++) {
            char letter = lineAsChars[i];
            Sign sign = createSignFrom(letter, i, y);
            signs.add(sign);
        }
    }

    private Sign createSignFrom(char value, int x, int y) {
        boolean isletter = false;
        if (Character.isAlphabetic(value)) {
            isletter = true;
        }
        return new Sign(value, isletter, new Coordinate(x, y));
    }

    public Set<Sign> getSigns() {
        return signs;
    }
}