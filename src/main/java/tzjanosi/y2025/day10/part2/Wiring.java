package tzjanosi.y2025.day10.part2;

import java.util.*;

public class Wiring {
    private Set<Integer> positionsToIncrease;
    private int applied;

    public Wiring(Set<Integer> positionsToToggle) {
        this.positionsToIncrease = positionsToToggle;
    }

    public Wiring(Wiring original) {
        positionsToIncrease = new HashSet<>(original.positionsToIncrease);
        applied = original.applied;
    }

    public int numberOfElements() {
        return positionsToIncrease.size();
    }

    public boolean contains(int elementToCheck) {
        return positionsToIncrease.contains(elementToCheck);
    }

    public List<Integer> increase(List<Integer> input) {
        List<Integer> output = new ArrayList<>(input);
        for (int index : positionsToIncrease) {
            int nextValue = output.get(index) + 1;
            output.set(index, nextValue);
        }
        return output;
    }

    public List<Boolean> toggle(List<Boolean> input) {
        List<Boolean> output = new ArrayList<>(input);
        for (int index : positionsToIncrease) {
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
        return Objects.equals(positionsToIncrease, wiring.positionsToIncrease);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(positionsToIncrease);
    }

    @Override
    public String toString() {
        return "Wiring{" +
                "positionsToToggle=" + positionsToIncrease +
                '}';
    }

    public Set<Integer> getPositionsToIncrease() {
        return positionsToIncrease;
    }

    public void removeElement(Integer numberToRemove) {
        positionsToIncrease.remove(numberToRemove);
    }
}
