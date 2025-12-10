package tzjanosi.y2025.day10;

import java.util.*;

public class Factory {
    private List<Machine> machines = new ArrayList<>();
    private int totalNumberOfSwitches;

    public Factory(List<String> input) {
        processInput(input);
    }

    public int initAllMachine() {
        for (int i = 0; i < machines.size(); i++) {
            initMachine(machines.get(i));
        }
        return totalNumberOfSwitches;
    }

    private void initMachine(Machine machine) {
        Queue<Machine> queueToCalculate = new LinkedList<>();
        Machine actual = machine;
        queueToCalculate.add(actual);
        do {
            actual = queueToCalculate.poll();
            for (int i = 0; i < actual.numberOfRules(); i++) {
                Machine next = new Machine(actual);
                if (next.applyRule(i)) {
                    totalNumberOfSwitches += next.getNumberOfAppliedRules();
                    return;
                }
                queueToCalculate.offer(next);
            }
        } while (!queueToCalculate.isEmpty());
        throw new IllegalStateException("No rule found to init machine: " + machine);
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        List<Boolean> requiredState = createRequiredStateFromLine(line);
        List<Wiring> wiring = createWiringsFromLine(line);
        Machine machine = new Machine(requiredState, wiring);
        machines.add(machine);
    }

    private List<Wiring> createWiringsFromLine(String line) {
        String[] parts = line.split("[()]");
        List<Wiring> output = new ArrayList<>();
        for (int i = 1; i < parts.length - 1; i += 2) {
            Wiring wiring = createWiringFromPart(parts[i]);
            output.add(wiring);
        }
        return output;
    }

    private Wiring createWiringFromPart(String part) {
        String[] numbersAsString = part.split(",");
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < numbersAsString.length; i++) {
            numbers.add(Integer.parseInt(numbersAsString[i]));
        }
        return new Wiring(numbers);
    }

    private List<Boolean> createRequiredStateFromLine(String line) {
        String[] parts = line.split("\\[|\\]");
        char[] chars = parts[1].toCharArray();
        List<Boolean> output = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            output.add(chars[i] == '#');
        }
        return output;
    }
}
