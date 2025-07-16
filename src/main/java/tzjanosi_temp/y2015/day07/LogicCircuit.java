package tzjanosi_temp.y2015.day07;

import java.util.*;

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

    public int getResult(String operandName) {
        return findOperandToName(operandName).getValue();
    }

    private Optional<Operation> findOperationToProcess() {
        return operations.stream().filter(Operation::waitForProcess).findFirst();
    }

    private void buildLogicCircuitFromInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line);
        }
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        switch (words.length) {
            case 3:
                processOperandLine(line);
                break;
            case 4:
                processOneOperandOperationLine(line);
                break;
            case 5:
                processTwoOperandOperationLine(line);
                break;
            default:
                throw new IllegalArgumentException(String.format("Not a valid line:%s", line));
        }
    }

    private void processOneOperandOperationLine(String line) {
        String[] parts = line.split(" -> ");
        String resultName = parts[1];

        String[] operandsAndOperation = parts[0].split(" ");
        String operatorName = operandsAndOperation[0];
        String operand1Name = operandsAndOperation[1];


        Operand result = findOperandToName(resultName);
        Operand operand1 = findOperandToName(operand1Name);
        Operator operator = findOperatorToName(operatorName);

        Operation operation = new Operation(operand1, operand1, result, operator);
        operations.add(operation);
    }

    private void processTwoOperandOperationLine(String line) {
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
        return Arrays.stream(Operator.values())
                .filter(o -> o.name().equals(operatorName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No operator found with the name: %s", operatorName)));
    }

    private Operand findOperandToName(String name) {
        Optional<Operand> optionalResult = findOperand(name);
        if (optionalResult.isPresent()) {
            return optionalResult.get();
        } else {
            Operand operand = new Operand(name);
            if (isPositiveInteger(name)) {
                operand.setValue(Integer.parseInt(name));
            }
            operands.add(operand);
            return operand;
        }
    }

    private boolean isPositiveInteger(String str) {
        return str != null && str.matches("\\d+");
//        return str != null && str.matches("[1-9]\\d*");
    }

    private Optional<Operand> findOperand(String name) {
        return operands.stream().filter(o -> o.getName().equals(name)).findFirst();
    }

    private void processOperandLine(String line) {
        String[] words = line.split(" ");
        String name = words[2];
//        Operand operand=new Operand(name);
        Operand operand = findOperandToName(name);
        if (isPositiveInteger(words[0])) {
            int intValue = Integer.parseInt(words[0]);
            operand.setValue(intValue);
        } else {
            Operand original = findOperandToName(words[0]);
            operand.setValue(original);
        }
        operands.add(operand);
    }


    public Set<Operand> getOperands() {
        return operands;
    }

    public Set<Operation> getOperations() {
        return operations;
    }
}
