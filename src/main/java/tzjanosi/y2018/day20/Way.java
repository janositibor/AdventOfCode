package tzjanosi.y2018.day20;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Way {
    private String route;
    private Coordinate position;

    public Way(String route) {
        this.route = route;
        findPosition();
    }

    public Way(Way original) {
        route = original.route;
        findPosition();
    }

    public int routeLength() {
        return route.length();
    }

    public void extendRoute(String furtherRoute) {
        route = route.concat(furtherRoute);
        findPosition();
    }

    private void findPosition() {
        int x = countOfChar('E') - countOfChar('W');
        int y = countOfChar('S') - countOfChar('N');
        position = new Coordinate(x, y);

    }

    private int countOfChar(char character) {
        char[] chars = route.toCharArray();
        Stream<Character> stream =
                IntStream.range(0, chars.length)
                        .mapToObj(i -> chars[i]);
        return (int) stream.filter(c -> c.equals(character)).count();
    }

    public String getRoute() {
        return route;
    }

    public Coordinate getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Way{" +
                "route='" + route + '\'' +
                '}';
    }
}
