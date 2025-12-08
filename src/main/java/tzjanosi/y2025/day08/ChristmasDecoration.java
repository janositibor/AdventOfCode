package tzjanosi.y2025.day08;

import java.util.*;

public class ChristmasDecoration {
    private List<Coordinate> coordinates = new ArrayList<>();
    @SuppressWarnings("PMD.UseConcurrentHashMap")
    private Map<Double, Circuit> minDistancePairs = new TreeMap<>();
    private Set<Circuit> circuits = new HashSet<>();
    private int numberOfSteps;

    public ChristmasDecoration(int numberOfSteps, List<String> input) {
        this.numberOfSteps = numberOfSteps;
        processInput(input);
        buildMinDistancePairs();
    }

    public long decorateAll() {
        long output = 0;
        while (circuits.size() > 1) {
            Map.Entry<Double, Circuit> pair = ((TreeMap) minDistancePairs).pollFirstEntry();
            Circuit c = pair.getValue();
            Coordinate c1 = c.pollElement();
            Coordinate c2 = c.pollElement();
            processPair(c1, c2);
            output = c1.getX() * c2.getX();
        }
        return output;
    }

    public long decorate() {
        for (int i = 0; i < numberOfSteps; i++) {
            Map.Entry<Double, Circuit> pair = ((TreeMap) minDistancePairs).pollFirstEntry();
            Circuit c = pair.getValue();
            Coordinate c1 = c.pollElement();
            Coordinate c2 = c.pollElement();
            processPair(c1, c2);
        }
        return calculateResult();
    }

    private long calculateResult() {
        return circuits.stream()
                .mapToInt(Circuit::elementCount)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, (d1, d2) -> d1 * d2);
    }

    private void processPair(Coordinate c1, Coordinate c2) {
        Circuit circuit1 = findCircuit(c1);
        Circuit circuit2 = findCircuit(c2);
        if (!circuit1.equals(circuit2)) {
            circuit1.union(circuit2);
            circuits.remove(circuit2);
        }
    }

    private Circuit findCircuit(Coordinate coordinate) {
        return circuits.stream()
                .filter(c -> c.hasElement(coordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No circuit found! " + coordinate));
    }

    private void buildMinDistancePairs() {
        for (int i = 0; i < coordinates.size() - 1; i++) {
            Coordinate coordinateFrom = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                Coordinate coordinateTo = coordinates.get(j);
                double distance = coordinateFrom.distanceFromCoordinate(coordinateTo);
                Circuit tempCircuit = new Circuit(coordinateFrom, coordinateTo);
                minDistancePairs.put(distance, tempCircuit);
            }
        }
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] numbersAsString = line.split(",");
        Coordinate coordinate = new Coordinate(Integer.parseInt(numbersAsString[0]), Integer.parseInt(numbersAsString[1]), Integer.parseInt(numbersAsString[2]));
        coordinates.add(coordinate);
        circuits.add(new Circuit(coordinate));
    }
}
