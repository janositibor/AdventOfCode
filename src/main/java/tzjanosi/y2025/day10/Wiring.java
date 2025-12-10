package tzjanosi.y2025.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Wiring {
    private Set<Integer> positionsToToggle;

    public Wiring(Set<Integer> positionsToToggle) {
        this.positionsToToggle = positionsToToggle;
    }

    public List<Boolean> toggle(List<Boolean> input) {
        List<Boolean> output = new ArrayList<>(input);
        for (int index : positionsToToggle) {
            boolean nextValue = !output.get(index);
            output.set(index, nextValue);
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wiring wiring = (Wiring) o;
        return Objects.equals(positionsToToggle, wiring.positionsToToggle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(positionsToToggle);
    }

    @Override
    public String toString() {
        return "Wiring{" +
                "positionsToToggle=" + positionsToToggle +
                '}';
    }
}
