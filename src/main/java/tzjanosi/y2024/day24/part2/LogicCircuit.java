package tzjanosi.y2024.day24.part2;

import java.util.*;
import java.util.stream.Collectors;


public class LogicCircuit {
    private Set<Operand> operands = new HashSet<>();
    private List<Operation> operations = new ArrayList<>();

    public LogicCircuit(List<String> input) {
        buildLogicCircuitFromInput(input);
    }

    public long doCalculation(double x, double y) {
        setInputNumbers(x, y);
        run();
        return calculateZValue();
    }

    public void swap(int from, int to) {
        Operation fromOperation = operations.get(from);
        Operation toOperation = operations.get(to);
        Operand temp = toOperation.getResult();
        toOperation.setResult(fromOperation.getResult());
        fromOperation.setResult(temp);
    }

    public void swap(String operandName1, String operandName2) {
        List<String> operandsToSwap = new ArrayList<>();
        operandsToSwap.add(operandName1);
        operandsToSwap.add(operandName2);
        int operation1 = getDefiningOperationNumber(operandName1);
        int operation2 = getDefiningOperationNumber(operandName2);
        swap(operation1, operation2);
    }

    private int getDefiningOperationNumber(String operandName) {
        Operation operation = operations.stream()
                .filter(o -> o.getResult().getName().equals(operandName))
                .findFirst()
                .get();
        return operations.indexOf(operation);
    }

    public void setInputNumbers(double x, double y) {
        long xLong = (long) x;
        long yLong = (long) y;
        validate(xLong);
        validate(yLong);

        List<Integer> xList = createListFromNumber(xLong);
        List<Integer> yList = createListFromNumber(yLong);

        setInput("x", xList);
        setInput("y", yList);
        reset();

    }

    private void reset() {
        operations.stream()
                .forEach(o -> o.setDone(false));
        operands.stream()
                .filter(o -> (!"x".equals(o.getType()) && !"y".equals(o.getType())))
                .forEach(o -> o.setValues(false));
        operands.stream()
                .filter(o -> (!"x".equals(o.getType()) && !"y".equals(o.getType())))
                .forEach(o -> o.setValid(false));

    }

    private void setInput(String inputSelector, List<Integer> inputList) {
        validateListLength(inputList, 45, "inputList");
        List<Operand> operandsToSet = getOperandsToSet(inputSelector);
        validateListLength(operandsToSet, 45, "operands");
        for (int i = 0; i < operandsToSet.size(); i++) {
            operandsToSet.get(i).publicSetValues(inputList.get(i));
            operandsToSet.get(i).setValid(true);
        }
    }

    private void validateListLength(List listToCheck, int expectedLength, String identifier) {
        if (listToCheck.size() != expectedLength) {
            throw new IllegalArgumentException(String.format("Length of %s is %d instead of the expected %d!", identifier, listToCheck.size(), expectedLength));
        }
    }

    private List<Operand> getOperandsToSet(String inputSelector) {
        return operands.stream()
                .filter(o -> o.getType().equals(inputSelector))
                .sorted(Comparator.comparing(Operand::getOrder, Comparator.reverseOrder()))
                .toList();
    }

    private List<Integer> createListFromNumber(long x) {
        String binaryString = Long.toString(x, 2);
        String[] stringArray = binaryString.split("");
        int lengthOfInput = stringArray.length;
        Integer[] output = new Integer[45];
        Arrays.fill(output, 0);
        int startingIndex = output.length - lengthOfInput;
        for (int i = 0; i < lengthOfInput; i++) {
            output[startingIndex + i] = Integer.parseInt(stringArray[i]);
        }
        return Arrays.asList(output);
    }

    private void validate(long numberToCheck) {
        if (numberToCheck < 0 || numberToCheck > Math.pow(2, 45) - 1) {
            throw new IllegalArgumentException(String.format("Not a valid input number: %d", numberToCheck));
        }
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
                .filter(o -> "z".equals(o.getType()))
                .sorted(Comparator.comparing(Operand::getOrder, Comparator.reverseOrder()))
                .map(o -> String.valueOf(o.getIntValue()))
                .collect(Collectors.joining());
        return Long.parseLong(binaryNumber, 2);
    }


    private Optional<Operation> findOperationToProcess() {
        return operations.stream().filter(Operation::waitForProcess).findFirst();
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

    public List<Operation> getOperations() {
        return operations;
    }

}
