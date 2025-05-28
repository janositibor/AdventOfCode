package TZJanosi.y2024.day24.part1;

import java.util.*;
import java.util.stream.Collectors;

public class LogicCircuit {
    private Set<Operand> operands = new HashSet<>();
    private Set<Operation> operations = new HashSet<>();

    public LogicCircuit(List<String> input) {
        buildLogicCircuitFromInput(input);
    }

    public void run() {
        Optional<Operation> optionalOperation = findOperationToProcess();
        while (optionalOperation.isPresent()) {
            optionalOperation.get().calculate();
            optionalOperation = findOperationToProcess();
        }
    }

    public long calculateZValue() {
        String binaryNumber = operands.stream()
                .filter(o -> o.getType().equals("z"))
                .sorted(Comparator.comparing(Operand::getOrder, Comparator.reverseOrder()))
                .map(o -> String.valueOf(o.getIntValue()))
                .collect(Collectors.joining());
        return Long.parseLong(binaryNumber, 2);
    }


    private Optional<Operation> findOperationToProcess() {
        return operations.stream().filter(o -> o.waitForProcess()).findFirst();
    }

    private void buildLogicCircuitFromInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (!line.isBlank()) {
                processLine(line);
            }
        }
    }

    private void processLine(String line) {
        if (isOperandLine(line)) {
            processOperandLine(line);
            return;
        } else if (isOperationLine(line)) {
            processOperationLine(line);
            return;
        }
        throw new IllegalArgumentException(String.format("Not a valid line:%s", line));
    }

    private void processOperationLine(String line) {
        String[] parts = line.split(" -> ");
        String resultName = parts[1];

        String[] operandsAndOperation = parts[0].split(" ");
        String operand1Name = operandsAndOperation[0];
        String operatorName = operandsAndOperation[1];
        String operand2Name = operandsAndOperation[2];

        Operand result = findOperandToName(resultName);
        Operand operand1 = findOperandToName(operand1Name);
        Operand operand2 = findOperandToName(operand2Name);
        Operator operator = findOperatorToName(operatorName);

        Operation operation = new Operation(operand1, operand2, result, operator);
        operations.add(operation);
    }

    private Operator findOperatorToName(String operatorName) {
        switch (operatorName) {
            case "AND":
                return Operator.AND;
            case "OR":
                return Operator.OR;
            case "XOR":
                return Operator.XOR;
            default:
                throw new IllegalArgumentException(String.format("Invalid operator: %s", operatorName));
        }
    }

    private Operand findOperandToName(String name) {
        Optional<Operand> optionalResult = findOperand(name);
        if (optionalResult.isPresent()) {
            return optionalResult.get();
        } else {
            Operand operand = new Operand(name);
            operands.add(operand);
            return operand;
        }
    }

    private Optional<Operand> findOperand(String name) {
        return operands.stream().filter(o -> o.getName().equals(name)).findFirst();
    }

    private void processOperandLine(String line) {
        String[] words = line.split(": ");
        String name = words[0];
        int intValue = Integer.parseInt(words[1]);
        Operand operand = new Operand(name, intValue);
        operands.add(operand);
    }

    private boolean isOperationLine(String line) {
        return line.contains(" -> ");
    }

    private boolean isOperandLine(String line) {
        return line.contains(": ");
    }

    public Set<Operand> getOperands() {
        return operands;
    }

    public Set<Operation> getOperations() {
        return operations;
    }
}
