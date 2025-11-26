package tzjanosi.y2017.day23.part1;

import java.util.*;

public class Coprocessor {
    private Set<Register> registers = new HashSet<>();
    private List<Operation> operations = new ArrayList<>();
    private long numberOfMultiplications;

    public Coprocessor(List<String> input) {
        process(input);
    }

    public long execute() {
        int index = 0;
        while (0 <= index && index < operations.size()) {
            Operation operationToExecute = operations.get(index);
            index += executeOperation(operationToExecute);
        }
        return numberOfMultiplications;
    }

    private long executeOperation(Operation operationToExecute) {
        String name = operationToExecute.getName();
        Register parameter1 = operationToExecute.getParameter1();
        long parameter2 = operationToExecute.getParameter2Value();
        long output = 0;
        switch (name) {
            case "set":
                output = updateRegister(parameter1, parameter2);
                break;
            case "sub":
                output = subFromRegister(parameter1, parameter2);
                break;
            case "mul":
                output = multiplyRegister(parameter1, parameter2);
                break;
            case "jnz":
                output = jump(parameter1, parameter2);
                break;
            default:
                throw new IllegalArgumentException("No operation found: " + name);
        }
        return output;
    }

    private long jump(Register parameter1, long parameter2) {
        return parameter1.getValue() == 0 ? 1 : parameter2;
    }

    private long multiplyRegister(Register parameter1, long parameter2) {
        parameter1.multiply(parameter2);
        numberOfMultiplications++;
        return 1;
    }

    private long subFromRegister(Register parameter1, long parameter2) {
        parameter1.sub(parameter2);
        return 1;
    }

    private long updateRegister(Register parameter1, long parameter2) {
        parameter1.setValue(parameter2);
        return 1;
    }

    private void process(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        String name = words[0];
        String parameter1Name = ("1".equals(words[1]) ? "one" : words[1]);
        Register parameter1 = findOrCreateRegisterByName(parameter1Name);
        Operation operation;
        if (isCharacter(words[2])) {
            Register parameter2 = findOrCreateRegisterByName(words[2]);
            operation = new OperationRegister(name, parameter1, parameter2);
        } else {
            operation = new OperationNumerical(name, parameter1, Integer.parseInt(words[2]));
        }
        operations.add(operation);
    }

    private boolean isCharacter(String word) {
        return word.length() == 1 && Character.isAlphabetic(word.toCharArray()[0]);
    }

    private Register findOrCreateRegisterByName(String registerName) {
        Optional<Register> optionalRegister = registers.stream()
                .filter(r -> registerName.equals(r.getName()))
                .findFirst();

        if (optionalRegister.isPresent()) {
            return optionalRegister.get();
        }
        Register output;
        if ("one".equals(registerName)) {
            output = new Register(registerName, 1);
        } else {
            output = new Register(registerName);
        }
        registers.add(output);
        return output;
    }
}
