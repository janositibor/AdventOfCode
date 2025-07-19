package tzjanosi.y2016.day03;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Triangle> triangles = new ArrayList<>();

    public Service(List<String> input, boolean part2) {
        if (part2) {
            for (int i = 0; i < input.size(); i += 3) {
                String line1 = input.get(i);
                String line2 = input.get(i + 1);
                String line3 = input.get(i + 2);
                processLinePart2(line1, line2, line3);
            }
        } else {
            for (int i = 0; i < input.size(); i++) {
                String line = input.get(i);
                processLine(line);
            }
        }

    }

    private void processLinePart2(String line1, String line2, String line3) {
        Triangle triangle;
        for (int i = 0; i <= 10; i += 5) {
            triangle = createTriangle(i, line1, line2, line3);
            triangles.add(triangle);
        }
    }

    private Triangle createTriangle(int index, String line1, String line2, String line3) {
        int a = Integer.parseInt(line1.substring(index, index + 5).strip());
        int b = Integer.parseInt(line2.substring(index, index + 5).strip());
        int c = Integer.parseInt(line3.substring(index, index + 5).strip());
        return new Triangle(a, b, c);
    }

    private void processLine(String line) {
        int a = Integer.parseInt(line.substring(0, 5).strip());
        int b = Integer.parseInt(line.substring(5, 10).strip());
        int c = Integer.parseInt(line.substring(10).strip());
        Triangle triangle = new Triangle(a, b, c);
        triangles.add(triangle);
    }

    public int countValidTriangles() {
        return (int) triangles.stream().filter(Triangle::isValid).count();
    }
}
