package tzjanosi.y2015.day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Computer {
    private Set<Register> registers;
    private List<Operation> operations = new ArrayList<>();

    public Computer(List<String> input, Set<Register> registers) {
        this.registers = registers;
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    public long run(String outputRegisterName) {
        runWithoutOutput();
        return findValueByRegisterName(outputRegisterName);
    }

    private void runWithoutOutput() {
        int lengthOfOperations = operations.size();
        int index = 0;
        while (index < lengthOfOperations) {
            Operation operation = operations.get(index);
//            System.out.println(index);
            index += operation.exec();
        }
    }

    private long findValueByRegisterName(String registerName) {
        return findRegisterByName(registerName).getValue();
    }

    private Register findRegisterByName(String name) {
        return registers.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No register found with name: " + name));
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        Operation operation = buildOperationFromWords(words);
        operations.add(operation);
    }

    private Operation buildOperationFromWords(String[] words) {
        int offset;
        String actionName = words[0];
        Action action = findActionByName(actionName);
        if (action == Action.JMP) {
            offset = Integer.parseInt(words[1]);
            return new Operation(action, findRegisterByName("a"), offset);
        }
        if (words.length == 3) {
            offset = Integer.parseInt(words[2]);
            String registerName = words[1].substring(0, 1);
            return new Operation(action, findRegisterByName(registerName), offset);
        }
        return new Operation(action, findRegisterByName(words[1]), 0);
    }

    private Action findActionByName(String actionName) {
        return Arrays.stream(Action.values())
                .filter(a -> a.name().equalsIgnoreCase(actionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No action found with name: " + actionName));
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
