package TZJanosi.y2024.day24.part2;

import java.util.*;
import java.util.stream.Collectors;

public class LogicCircuitHandler {
    List<String> originalInput;
    private LogicCircuit originalLogicCircuit;
    private LogicCircuit workLogicCircuit;
    List<Operation> operations;
    private List<String> operandsToSwap = new ArrayList<>();

    public LogicCircuitHandler(List<String> input) {
        originalInput = input;
        originalLogicCircuit = new LogicCircuit(input);
        operations = originalLogicCircuit.getOperations();
        workLogicCircuit = originalLogicCircuit;
    }

    public long calculateSum(double operand1, double operand2) {
        return workLogicCircuit.doCalculation(operand1, operand2);
    }

    public void checkCircuit() {
        String temp = "wrs";

        for (int i = 1; i < 45; i++) {
            temp = residual(i, temp);
        }
    }

    private String checkXor(int digit, String previousResidualName, String tempXOR, String resultName) {
        String output = tempXOR;
        if (resultName(previousResidualName, tempXOR, Operator.XOR).isEmpty() || !resultName.equals(resultName(previousResidualName, tempXOR, Operator.XOR).get())) {
            if (!matchResultAndOperator(previousResidualName, Operator.XOR, resultName) && !matchResultAndOperator(tempXOR, Operator.XOR, resultName)) {
//                System.out.println("NOK, z: "+getOperandName("z", digit));
//                System.out.println("Helyette: "+ resultName(previousResidualName,tempXOR,Operator.XOR).get());
                swap(getOperandName("z", digit), resultName(previousResidualName, tempXOR, Operator.XOR).get());
            } else {
//                if(!matchResultAndOperator(previousResidualName,Operator.XOR,resultName)){
//                    System.out.println("NOK, previousResidual: "+previousResidualName);
//                }
                if (!matchResultAndOperator(tempXOR, Operator.XOR, resultName)) {
//                    System.out.println("NOK, tempXOR: "+tempXOR);
//                    System.out.println("Helyette: "+ findOtherOperand(previousResidualName,Operator.XOR,resultName).get());
                    swap(tempXOR, findOtherOperand(previousResidualName, Operator.XOR, resultName).get());
                    output = findOtherOperand(previousResidualName, Operator.XOR, resultName).get();
                }
            }
        }
        return output;
    }

    private String residual(int digit, String previousResidualName) {
        String tempXOR = resultName(digit, Operator.XOR).get();
        String resultName = getOperandName("z", digit);
        tempXOR = checkXor(digit, previousResidualName, tempXOR, resultName);


        String tempAND = resultName(digit, Operator.AND).get();
        String tempANDXOR = resultName(previousResidualName, tempXOR, Operator.AND).get();

        Optional<String> optionalResidual = resultName(tempAND, tempANDXOR, Operator.OR);
        optionalResidual = setOptionalResidual(optionalResidual, tempAND, tempANDXOR);

        return optionalResidual.get();
    }

    private Optional<String> setOptionalResidual(Optional<String> optionalResidual, String tempAND, String tempANDXOR) {
        Optional<String> output = optionalResidual;
        if (optionalResidual.isEmpty()) {
            if (!matchResultAndOperator(tempAND, Operator.OR, null)) {
//                System.out.println("NOK, tempAND: "+tempAND);
//                System.out.println("Helyette: "+ findOtherOperand(tempANDXOR,Operator.OR,null).get());
                addOperandNameToSwapList(tempAND, findOtherOperand(tempANDXOR, Operator.OR, null).get());
                tempAND = findOtherOperand(tempANDXOR, Operator.OR, null).get();

            }
            if (!matchResultAndOperator(tempANDXOR, Operator.OR, null)) {
//                System.out.println("NOK, tempANDXOR: "+tempANDXOR);
//                System.out.println("Helyette: "+ findOtherOperand(tempAND,Operator.OR,null));
                addOperandNameToSwapList(tempANDXOR, findOtherOperand(tempAND, Operator.OR, null).get());
            }
            output = resultName(tempAND, tempANDXOR, Operator.OR);
        }
        return output;
    }


    private void swap(String operandName1, String operandName2) {
        addOperandNameToSwapList(operandName1, operandName2);
        workLogicCircuit.swap(operandName1, operandName2);
        operations = workLogicCircuit.getOperations();
    }

    public void swapAll() {
        if (operandsToSwap.size() % 2 != 0) {
            throw new IllegalStateException("The number of operands to swap should be even!");
        }
        for (int i = 0; i < operandsToSwap.size(); i += 2) {
            swap(operandsToSwap.get(i), operandsToSwap.get(i + 1));
        }
    }

    private void addOperandNameToSwapList(String operandName1, String operandName2) {
        operandsToSwap.add(operandName1);
        operandsToSwap.add(operandName2);
    }


    private Optional<String> findOtherOperand(String operandName, Operator operator, String resultName) {
        Operation operationToMatch = new Operation();
        operationToMatch.setOperand1(new Operand(operandName));
        operationToMatch.setOperator(operator);
        operationToMatch.setResult(new Operand(resultName));
        return operations.stream()
                .filter(o -> o.match(operationToMatch))
                .map(o -> o.getOtherOperandName(operandName))
                .findFirst();
    }

    private boolean matchResultAndOperator(String operandName, Operator operator, String resultName) {
        return findOtherOperand(operandName, operator, resultName).isPresent();
    }

    private Optional<String> resultName(String operand1Name, String operand2Name, Operator operator) {
        Operation operationToMatch = new Operation();
        operationToMatch.setOperand1(new Operand(operand1Name));
        operationToMatch.setOperand2(new Operand(operand2Name));
        operationToMatch.setOperator(operator);

        return operations.stream()
                .filter(o -> o.match(operationToMatch))
                .map(o -> o.getResult().getName())
                .findFirst();
    }

    private String getOperandName(String prefix, int digit) {
        return prefix + String.format("%02d", digit);
    }

    private Optional<String> resultName(int digit, Operator operator) {
        String operand1Name = getOperandName("x", digit);
        String operand2Name = getOperandName("y", digit);
        return resultName(operand1Name, operand2Name, operator);
    }

    public String getSwaps() {
        return operandsToSwap.stream().sorted(Comparator.naturalOrder()).collect(Collectors.joining(","));
    }

    public LogicCircuit getWorkLogicCircuit() {
        return workLogicCircuit;
    }
}
