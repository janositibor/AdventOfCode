package tzjanosi.y2025.day03;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String value;
    private List<Integer> numbers = new ArrayList<>();

    public Bank(String value) {
        this.value = value;
        buildNumbers();
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
