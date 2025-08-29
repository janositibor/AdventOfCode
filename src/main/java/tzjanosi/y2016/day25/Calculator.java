package tzjanosi.y2016.day25;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calculator {
    private Set<Register> registers = new HashSet<>();
    private List<String> instructions;
    private int index;
    private List<Integer> output;

    public Calculator(List<String> input) {
        createRegisters();
        instructions = new ArrayList<>(input);
    }

    private void createRegisters() {
        for (RegisterId registerId : RegisterId.values()) {
            Register register = new Register(registerId);
            registers.add(register);
        }
    }

    public int execute() {
        int counter = -1;
        boolean result;
        do {
            counter++;
            output = new ArrayList<>();
            result = executeWithStartingValue(counter);
        }
        while (!result);
        return counter;
    }

    private boolean executeWithStartingValue(int input) {
        index = 0;
        Register register = findRegister("a");
        register.copy(input);
        int lengthOfOutput = output.size();
        while (index < instructions.size() && lengthOfOutput < 100) {
            operation();
            if (output.size() > lengthOfOutput) {
                lengthOfOutput = output.size();
                boolean success = checkOutput();
                if (!success) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkOutput() {
        for (int i = 0; i < output.size(); i++) {
            if (output.get(i) != i % 2) {
                return false;
            }
        }
        return true;
    }

    private void operation() {
        String stringOperation = instructions.get(index);
        String[] words = stringOperation.split(" ");
        String order = words[0];
        String parameter1 = words[1];
        String parameter2 = "";
        if (words.length > 2) {
            parameter2 = words[2];
        }
        switch (order) {
            case "cpy":
                copy(parameter1, parameter2);
                break;
            case "inc":
                increment(parameter1);
                break;
            case "dec":
                decrement(parameter1);
                break;
            case "jnz":
                jump(parameter1, parameter2);
                break;
            case "out":
                out(parameter1);
                break;
            default:
                throw new IllegalArgumentException("No order found: " + order);
        }
    }

    private void out(String parameter1) {
        int value = getValueForParameter(parameter1);
        output.add(value);
        index++;
    }

    private void jump(String parameter1, String parameter2) {
        int check = getValueForParameter(parameter1);
        int shift = getValueForParameter(parameter2);
        if (check == 0) {
            index++;
        } else {
            index += shift;
        }
    }

    private void decrement(String parameter) {
        Register register = findRegister(parameter);
        register.decrement();
        index++;
    }

    private void increment(String parameter) {
        Register register = findRegister(parameter);
        register.increment();
        index++;
    }

    private void copy(String parameter1, String parameter2) {
        int value = getValueForParameter(parameter1);
        Register register = findRegister(parameter2);
        register.copy(value);
        index++;
    }

    private int getValueForParameter(String parameter) {
        int value;
        if (isRegister(parameter)) {
            value = findRegister(parameter).getValue();
        } else {
            value = Integer.parseInt(parameter);
        }
        return value;
    }

    private boolean isRegister(String parameter) {
        return Character.isAlphabetic(parameter.charAt(0));
    }

    private Register findRegister(String stringId) {
        char id = stringId.charAt(0);
        RegisterId registerId = findRegisterId(id);
        return findRegister(registerId);
    }

    private RegisterId findRegisterId(char id) {
        for (RegisterId registerId : RegisterId.values()) {
            if (registerId.getValue() == id) {
                return registerId;
            }
        }
        throw new IllegalArgumentException("No RegisterId found for this char: " + id);
    }

    private Register findRegister(RegisterId registerId) {
        return registers.stream()
                .filter(r -> r.getId().equals(registerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No RegisterId found for this RegisterId: " + registerId.getValue()));
    }
}
