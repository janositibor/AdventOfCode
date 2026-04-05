package tzjanosi.y2018.day13;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Track {
    private Queue<Cart> carts = new PriorityQueue<>();
    private Map<Coordinate, Character> signs = new ConcurrentHashMap<>();

    public Track(List<String> input) {
        processInput(input);
    }

    public String findCrash() {
        Optional<Coordinate> foundCrash = Optional.empty();
        while (foundCrash.isEmpty()) {
            foundCrash = step();
        }
        Coordinate crashPosition = foundCrash.get();
        return crashPosition.stringRepresentation();
    }

    private Optional<Coordinate> step() {
        Queue<Cart> newCarts = new PriorityQueue<>();
        while (!carts.isEmpty()) {
            Cart cart = carts.remove();
            Coordinate newPosition = cart.move();
            if (signs.containsKey(newPosition)) {
                Character sign = signs.get(newPosition);
                cart.changeDirection(sign);
            }
            if (isCrash(newPosition, newCarts)) {
                return Optional.of(newPosition);
            }
            newCarts.add(cart);
        }
        carts = newCarts;
        return Optional.empty();
    }

    private boolean isCrash(Coordinate newPosition, Queue<Cart> newCarts) {
        return newCarts.stream().anyMatch(c -> c.getPosition().equals(newPosition)) || carts.stream().anyMatch(c -> c.getPosition().equals(newPosition));
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i), i);
        }
    }

    private void processLine(String line, int y) {
        for (int i = 0; i < line.length(); i++) {
            char actualChar = line.charAt(i);
            if (actualChar == '/' || actualChar == '\\' || actualChar == '+') {
                Coordinate coordinate = new Coordinate(i, y);
                signs.put(coordinate, actualChar);
            } else {
                if (actualChar == '^' || actualChar == 'v' || actualChar == '<' || actualChar == '>') {
                    Coordinate coordinate = new Coordinate(i, y);
                    addCart(coordinate, actualChar);
                }
            }
        }
    }

    private void addCart(Coordinate position, char directionAsChar) {
        Coordinate direction;
        switch (directionAsChar) {
            case '^':
                direction = new Coordinate(0, -1);
                break;
            case 'v':
                direction = new Coordinate(0, 1);
                break;
            case '<':
                direction = new Coordinate(-1, 0);
                break;
            case '>':
                direction = new Coordinate(1, 0);
                break;
            default:
                throw new IllegalArgumentException("Unexpected character at cart direction:" + directionAsChar);
        }
        carts.add(new Cart(position, direction));
    }
}
