package tzjanosi.y2017.day18.part1;

import java.util.*;

public class Duet {
    private Set<Register> registers = new HashSet<>();
    private List<Operation> operations = new ArrayList<>();
    private long lastPlayedFrequency;
    private boolean stop;

    public Duet(List<String> input) {
        process(input);
    }

    public long execute() {
        int index = 0;
        while (!stop) {
            Operation operationToExecute = operations.get(index);
            index += executeOperation(operationToExecute);
        }
        return lastPlayedFrequency;
    }

    private long executeOperation(Operation operationToExecute) {
        String name = operationToExecute.getName();
        Register parameter1 = operationToExecute.getParameter1();
        long parameter2 = operationToExecute.getParameter2Value();
        long output = 0;
        switch (name) {
            case "snd":
                output = sound(parameter1);
                break;
            case "set":
                output = updateRegister(parameter1, parameter2);
                break;
            case "add":
                output = addToRegister(parameter1, parameter2);
                break;
            case "mul":
                output = multiplyRegister(parameter1, parameter2);
                break;
            case "mod":
                output = modRegister(parameter1, parameter2);
                break;
            case "rcv":
                output = recover(parameter1);
                break;
            case "jgz":
                output = jump(parameter1, parameter2);
                break;
            default:
                throw new IllegalArgumentException("No operation found: " + name);
        }
        return output;
    }

    private long jump(Register parameter1, long parameter2) {
        return parameter1.getValue() > 0 ? parameter2 : 1;
    }

    private long recover(Register parameter1) {
        if (parameter1.getValue() != 0) {
            stop = true;
            return 0;
        }
        return 1;
    }

    private long modRegister(Register parameter1, long parameter2) {
        parameter1.mod(parameter2);
        return 1;
    }

    private long multiplyRegister(Register parameter1, long parameter2) {
        parameter1.multiply(parameter2);
        return 1;
    }

    private long addToRegister(Register parameter1, long parameter2) {
        parameter1.add(parameter2);
        return 1;
    }

    private long updateRegister(Register parameter1, long parameter2) {
        parameter1.setValue(parameter2);
        return 1;
    }

    private long sound(Register parameter1) {
        lastPlayedFrequency = parameter1.getValue();
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
        if (words.length > 2) {
            if (isCharacter(words[2])) {
                Register parameter2 = findOrCreateRegisterByName(words[2]);
                operation = new OperationRegister(name, parameter1, parameter2);
            } else {
                operation = new OperationNumerical(name, parameter1, Integer.parseInt(words[2]));
            }
        } else {
            operation = new OperationNumerical(name, parameter1, 0);
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
