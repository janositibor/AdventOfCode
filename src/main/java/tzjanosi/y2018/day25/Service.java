package tzjanosi.y2018.day25;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Service {
    private Set<Constellation> constellations = new HashSet<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int build() {
        boolean proceed = true;
        while (proceed) {
            proceed = false;
            Iterator<Constellation> iterator = constellations.iterator();
            while (iterator.hasNext() && !proceed) {
                Constellation basic = iterator.next();
                proceed = checkConstellation(basic);
            }
        }
        return constellations.size();
    }

    private boolean checkConstellation(Constellation basic) {
        boolean proceed = false;
        Iterator<Constellation> iterator = constellations.iterator();
        while (iterator.hasNext() && !proceed) {
            Constellation other = iterator.next();
            if (!basic.equals(other) && basic.closeTo(other)) {
                basic.merge(other);
                iterator.remove();
                proceed = true;
            }
        }
        return proceed;
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] numbers = line.split(",");
        int x = Integer.parseInt(numbers[0]);
        int y = Integer.parseInt(numbers[1]);
        int z = Integer.parseInt(numbers[2]);
        int w = Integer.parseInt(numbers[3]);
        constellations.add(new Constellation(new Coordinate(x, y, z, w)));
    }
}
