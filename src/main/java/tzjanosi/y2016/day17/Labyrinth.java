package tzjanosi.y2016.day17;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Labyrinth {
    private String secretKey;
    private int limit = 3;
    private Coordinate start = new Coordinate(0, 0);
    private Coordinate end = new Coordinate(limit, limit);
    private Coordinate location = start;
    private String path = "";

    public Labyrinth(String secretKey) {
        this.secretKey = secretKey;
    }

    public Labyrinth(Labyrinth original) {
        secretKey = original.secretKey;
        limit = original.limit;
        start = original.start;
        end = original.end;
        path = original.path;
        location = new Coordinate(original.location);
    }

    public boolean isSuccess() {
        return location.equals(end);
    }

    public boolean move(Direction direction) {
        path += direction.name();
        location.add(direction.getDirection());
        return isValid();
    }

    private boolean isValid() {
        return insideArea();
    }

    private boolean insideArea() {
        return insideOneDimension(location.getX()) && insideOneDimension(location.getY());
    }

    private boolean insideOneDimension(int input) {
        return (0 <= input && input <= limit);
    }

    public List<Direction> validDirections() {
        Direction[] directions = Direction.values();
        String key = generateMd5HashForPath().substring(0, 4);
        List<Direction> output = new ArrayList<>();
        char[] chars = key.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (isOpen(chars[i])) {
                output.add(directions[i]);
            }
        }
        return output;
    }

    private boolean isOpen(char charToCheck) {
        return ('b' <= charToCheck && charToCheck <= 'f');
    }

    private String generateMd5HashForPath() {
        String password = secretKey + path;
        return DigestUtils.md5Hex(password).toLowerCase(Locale.US);
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Labyrinth{" +
                "path='" + path + '\'' +
                ", location=" + location +
                '}';
    }
}
