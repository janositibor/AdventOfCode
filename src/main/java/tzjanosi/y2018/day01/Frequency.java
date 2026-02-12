package tzjanosi.y2018.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Frequency {
    private List<Integer> shifts = new ArrayList<>();

    public Frequency(List<String> input) {
        processLines(input);
    }

    public int sumOfShifts() {
        return shifts.stream()
                .mapToInt(d -> d)
                .sum();
    }

    public int firstDuplicateFast() {
        List<Integer> resultOfFirstRun = calculateResultOfFirstRun();
        int period = resultOfFirstRun.getLast();
        return findFirstDuplicate(resultOfFirstRun, period);
    }

    private int findFirstDuplicate(List<Integer> list, int period) {
        int numberOfNecessaryPeriods = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int freq1 = list.get(i);
                int freq2 = list.get(j);
                int difference = Math.abs(freq1 - freq2);
                if (difference % period == 0) {
                    int n = difference / period;
                    if (n < numberOfNecessaryPeriods) {
                        numberOfNecessaryPeriods = n;
                        result = Math.max(freq1, freq2);
                    }
                }
            }
        }
        return result;
    }

    public int firstDuplicate() {
        List<Integer> resultOfFirstRun = calculateResultOfFirstRun();
        List<Integer> resultOfNextRun = List.copyOf(resultOfFirstRun);
        List<Integer> allFrequencies = new ArrayList<>(resultOfFirstRun);
        while (true) {
            int found = firstDuplicateInList(allFrequencies);
            if (found > Integer.MIN_VALUE) {
                return found;
            }
            resultOfNextRun = incrementFrequencies(resultOfNextRun, resultOfFirstRun.getLast());
            allFrequencies.addAll(resultOfNextRun);
        }
    }

    private List<Integer> incrementFrequencies(List<Integer> input, Integer shift) {
        return input.stream().map(d -> d + shift).toList();
    }

    private int firstDuplicateInList(List<Integer> listToCheck) {
        for (int i = 0; i < listToCheck.size(); i++) {
            int value = listToCheck.get(i);
            if (listToCheck.indexOf(value) != listToCheck.lastIndexOf(value)) {
                return value;
            }
        }
        return Integer.MIN_VALUE;
    }

    private List<Integer> calculateResultOfFirstRun() {
        AtomicInteger sum = new AtomicInteger(0);
        return shifts.stream()
                .map(sum::addAndGet)
                .collect(Collectors.toList());
    }

    private void processLines(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        shifts.add(Integer.parseInt(line));
    }
}
