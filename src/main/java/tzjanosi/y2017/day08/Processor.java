package tzjanosi.y2017.day08;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;

public class Processor {
    private Set<Register> registers = new HashSet<>();
    private List<String> orders;
    private int maxValue;

    public Processor(List<String> orders) {
        this.orders = orders;
    }

    public int run() {
        for (int i = 0; i < orders.size(); i++) {
            runOrder(orders.get(i));
        }
        return findMaxValue();
    }

    public int runPart2() {
        for (int i = 0; i < orders.size(); i++) {
            runOrder(orders.get(i));
            calculateMaxValue();
        }
        return maxValue;
    }

    private void calculateMaxValue() {
        int currentMax = findMaxValue();
        maxValue = Math.max(maxValue, currentMax);
    }

    private void runOrder(String line) {
        String[] words = line.split(" ");
        int shift = calculateShift(words[1], Integer.parseInt(words[2]));
        int parameter = Integer.parseInt(words[6]);
        Register operatorRegister = getRegister(words[0]);
        Register conditionRegister = getRegister(words[4]);
        BiPredicate<Integer, Integer> condition = createCondition(words[5]);
        if (condition.test(conditionRegister.getValue(), parameter)) {
            operatorRegister.increment(shift);
        }
    }

    private BiPredicate<Integer, Integer> createCondition(String input) {
        BiPredicate<Integer, Integer> output;
        switch (input) {
            case "<":
                output = (i, j) -> i < j;
                break;
            case "<=":
                output = (i, j) -> i <= j;
                break;
            case "==":
                output = Integer::equals;
                break;
            case ">=":
                output = (i, j) -> i >= j;
                break;
            case ">":
                output = (i, j) -> i > j;
                break;
            case "!=":
                output = (i, j) -> !i.equals(j);
                break;
            default:
                throw new IllegalArgumentException(String.format("No operators found with this sign: %s", input));
        }
        return output;
    }

    private int calculateShift(String direction, int value) {
        int output = value;
        if ("dec".equals(direction)) {
            output *= -1;
        }
        return output;
    }

    private Register getRegister(String name) {
        Register output;
        Optional<Register> optionalRegister = findRegisterByName(name);
        if (optionalRegister.isPresent()) {
            output = optionalRegister.get();
        } else {
            output = new Register(name);
            registers.add(output);
        }
        return output;
    }

    private Optional<Register> findRegisterByName(String name) {
        return registers.stream()
                .filter(r -> name.equals(r.getName()))
                .findFirst();
    }

    private int findMaxValue() {
        return registers.stream()
                .mapToInt(Register::getValue)
                .max()
                .orElseThrow(() -> new IllegalStateException("No registers found"));
    }
}
