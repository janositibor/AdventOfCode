package tzjanosi.y2025.day10.part2;

import java.util.*;

public class Factory {
    private List<Machine> machines = new ArrayList<>();
    private int totalNumberOfSwitches;

    public Factory(List<String> input) {
        processInput(input);
    }

    public int findVoltageForAllMachine() {
        for (int i = 0; i < machines.size(); i++) {
            Machine machine = machines.get(i);
//            System.out.println("["+i+"] "+machine);
            machine.simplify();
//            System.out.println("["+i+" simple] "+machine);
            int result = findVoltageForAMachine(machine);
            if (result < 0) {
                throw new IllegalStateException("No valid combination of rules found");
            }
            totalNumberOfSwitches += result;
        }
        return totalNumberOfSwitches;
    }

    private int findVoltageForAMachine(Machine machine) {
        if (machine.numberOfRules() > 0 && machine.positiveValuesInRequiredVoltage() > 0) {
            int output = -1;
            Map<Wiring, Integer> nextRuleWithMaxNumber = machine.getNextRule();
            Wiring nextRule = nextRuleWithMaxNumber.keySet().stream().findFirst().get();
            int maxValue = nextRuleWithMaxNumber.values().stream().findFirst().get();
            int result = -1;
            for (int i = maxValue; i >= 0; i--) {
                Machine nextMachine = new Machine(machine);
                nextMachine.applyRule(nextRule, i);
                if (nextMachine.simplify()) {
                    result = findVoltageForAMachine(nextMachine);
                }
                output = findBestOutput(result, output);
            }
            return output;
        }
        return machine.numberOfRules() == 0 ? machine.getNumberOfAppliedRules() : -1;
    }

    private int findBestOutput(int result, int actualWinner) {
        int output = actualWinner;
        if (result > -1) {
            if (actualWinner == -1) {
                return result;
            }
            output = Math.min(result, actualWinner);
        }
        return output;
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        List<Wiring> wiring = createWiringsFromLine(line);
        List<Integer> requiredVoltage = createRequiredVoltageFromLine(line);
        Machine machine = new Machine(wiring, requiredVoltage);
        machines.add(machine);
    }


    private List<Integer> createRequiredVoltageFromLine(String line) {
        String[] parts = line.split("[{}]");
        String[] numbersAsString = parts[1].split(",");
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < numbersAsString.length; i++) {
            output.add(Integer.parseInt(numbersAsString[i]));
        }
        return output;
    }

    private List<Wiring> createWiringsFromLine(String line) {
        String[] parts = line.split("[()]");
        List<Wiring> temp = new ArrayList<>();
        for (int i = 1; i < parts.length - 1; i += 2) {
            Wiring wiring = createWiringFromPart(parts[i]);
            temp.add(wiring);
        }
        temp.sort(Comparator.comparing(Wiring::numberOfElements, Comparator.reverseOrder()));
        return temp;
    }

    private Wiring createWiringFromPart(String part) {
        String[] numbersAsString = part.split(",");
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < numbersAsString.length; i++) {
            numbers.add(Integer.parseInt(numbersAsString[i]));
        }
        return new Wiring(numbers);
    }
}
