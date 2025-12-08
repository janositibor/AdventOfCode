package tzjanosi.y2025.day08;

import java.util.HashSet;
import java.util.Set;

public class Circuit {
    private Set<Coordinate> elements = new HashSet<>();

    public Circuit(Coordinate element) {
        elements.add(element);
    }

    public Circuit(Coordinate element1, Coordinate element2) {
        elements.add(element1);
        elements.add(element2);
    }

    public Coordinate pollElement() {
        Coordinate output = elements.stream()
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Already empty ..."));
        elements.remove(output);
        return output;
    }

    public int elementCount() {
        return elements.size();
    }

    public boolean hasElement(Coordinate element) {
        return elements.stream().anyMatch(c -> c.equals(element));
    }

    public void union(Circuit other) {
        for (Coordinate element : other.elements) {
            elements.add(element);
        }
    }

    @Override
    public String toString() {
        return "Circuit{" +
                "elements=" + elements +
                '}';
    }

}
