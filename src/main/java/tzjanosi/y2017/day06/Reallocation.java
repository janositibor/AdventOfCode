package tzjanosi.y2017.day06;

import java.util.ArrayList;
import java.util.List;

public class Reallocation {
    private List<Integer> state = new ArrayList<>();
    private List<List<Integer>> previousStates = new ArrayList<>();

    public Reallocation(String input) {
        String[] words = input.split("\t");
        for (int i = 0; i < words.length; i++) {
            state.add(Integer.parseInt(words[i]));
        }
    }

    public int process() {
        int counter = 0;
        while (!previousStates.contains(state)) {
            previousStates.add(new ArrayList<>(state));
            int indexOfMaxSlot = findIndexOfMaxSlot();
            reallocate(indexOfMaxSlot);
            counter++;
        }
        return counter;
    }

    public int processPart2() {
        int counter = 0;
        while (!previousStates.contains(state)) {
            previousStates.add(new ArrayList<>(state));
            int indexOfMaxSlot = findIndexOfMaxSlot();
            reallocate(indexOfMaxSlot);
            counter++;
        }
        return counter - previousStates.indexOf(state);
    }

    private int findIndexOfMaxSlot() {
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < state.size(); i++) {
            int actualValue = state.get(i);
            if (actualValue > maxValue) {
                maxValue = actualValue;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void reallocate(int index) {
        int value = state.get(index);
        int numberOfSlots = state.size();
        int forAll = value / numberOfSlots;
        int mod = value % numberOfSlots;

        for (int i = 0; i < state.size(); i++) {
            int newValue;
            if (i == index) {
                newValue = 0;
            } else {
                newValue = state.get(i);
            }
            newValue += forAll;
            if (isWinner(i, index, mod)) {
                newValue++;
            }
            state.set(i, newValue);
        }
    }

    private boolean isWinner(int indexToCheck, int startIndex, int mod) {
        int numberOfSlots = state.size();
        if (startIndex + mod < numberOfSlots) {
            return startIndex < indexToCheck && indexToCheck <= startIndex + mod;
        } else {
            return startIndex < indexToCheck || indexToCheck <= (startIndex + mod) % numberOfSlots;
        }
    }
}
