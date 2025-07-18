package tzjanosi.y2016.day03;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Triangle> triangles = new ArrayList<>();

    public Service(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line);
        }
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
