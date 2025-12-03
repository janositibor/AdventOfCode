package tzjanosi.y2025.day03;

import java.util.*;

public class Bank {
    private String value;
    private List<Integer> numbers = new ArrayList<>();

    public Bank(String value) {
        this.value = value;
        buildNumbers();
    }

    public long calculateMaxValueByDigits(int digits) {
        int[] winner = new int[digits];
        Queue<Integer> remains = new LinkedList<>(numbers);
        initWinner(winner, remains);
        while (!remains.isEmpty()) {
            improve(winner, remains);
        }
        return valueOfArray(winner);

    }

    private long valueOfArray(int[] array) {
        long output = 0;
        for (int i = 0; i < array.length; i++) {
            output = (10 * output) + array[i];
        }
        return output;
    }

    private void improve(int[] to, Queue<Integer> from) {
        int valueToAdd = from.remove();
        for (int i = 0; i < to.length - 1; i++) {
            if (to[i] < to[i + 1]) {
                insertElement(to, i, valueToAdd);
                return;
            }
        }
        to[to.length - 1] = Math.max(to[to.length - 1], valueToAdd);
    }

    private void insertElement(int[] array, int indexToRemove, int elementToAdd) {
        for (int i = indexToRemove; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = elementToAdd;
    }

    private void initWinner(int[] to, Queue<Integer> from) {
        for (int i = 0; i < to.length; i++) {
            to[i] = from.remove();
        }
    }

    public int calculateValue() {
        int length = numbers.size();
        int firstIndex = length - 2;
        int secondIndex = length - 1;
        for (int i = firstIndex - 1; i >= 0; i--) {
            int newFirstIndex = getMaxFrom(i, firstIndex);
            if (newFirstIndex != firstIndex) {
                secondIndex = getMaxFrom(firstIndex, secondIndex);
                firstIndex = newFirstIndex;
            }
        }
        return 10 * numbers.get(firstIndex) + numbers.get(secondIndex);
    }

    private int getMaxFrom(int index1, int index2) {
        return numbers.get(index1) >= numbers.get(index2) ? index1 : index2;
    }

    private void buildNumbers() {
        String[] numbersAsString = this.value.split("");
        for (int i = 0; i < numbersAsString.length; i++) {
            numbers.add(Integer.parseInt(numbersAsString[i]));
        }
    }
}
