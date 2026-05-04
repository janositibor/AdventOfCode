package tzjanosi.y2018.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Service {
    private List<List<List<Integer>>> input = new ArrayList<>();
    private Map<Integer, List<Operator>> possibleOperators = new ConcurrentHashMap<>();
    private List<List<Integer>> inputProgram = new ArrayList<>();

    public Service(List<String> input, List<String> inputProgram) {
        processInput(input);
        processInputProgram(inputProgram);
    }

    public int execute() {
        deCrypt();
        List<Integer> registers = List.of(0, 0, 0, 0);
        for (int i = 0; i < inputProgram.size(); i++) {
            List<Integer> arguments = inputProgram.get(i);
            Operator operator = possibleOperators.get(arguments.get(0)).get(0);
            Calculator calculator = new Calculator(registers);
            registers = calculator.calculate(operator, arguments);
        }
        return registers.get(0);
    }

    private void processInputProgram(List<String> inputProgram) {
        for (int i = 0; i < inputProgram.size(); i++) {
            String line = inputProgram.get(i);
            List<Integer> arguments = createListFrom(line, " ");
            this.inputProgram.add(arguments);
        }
    }

    public int numberOfValidRules() {
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            if (numberOfPossibleOpCodes(input.get(i)) > 2) {
                count++;
            }
        }
        return count;
    }

    private void deCrypt() {
        for (int i = 0; i < input.size(); i++) {
            int numberOfOperator = input.get(i).get(1).get(0);
            List<Operator> possibleOperators = possibleOpcodes(input.get(i));
            process(numberOfOperator, possibleOperators);
        }
    }

    private void process(int numberOfOperator, List<Operator> operators) {
        if (possibleOperators.keySet().contains(numberOfOperator)) {
            List<Operator> newOperators = confine(numberOfOperator, operators);
            if (newOperators.size() == 1) {
                removeOperatorFromPossible(newOperators.get(0));
            }
            possibleOperators.put(numberOfOperator, newOperators);
        } else {
            possibleOperators.put(numberOfOperator, operators);
        }
    }

    private List<Operator> confine(int numberOfOperator, List<Operator> operators) {
        List<Operator> output = new ArrayList<>(possibleOperators.get(numberOfOperator));
        List<Operator> operatorsToRemove = new ArrayList<>();
        for (Operator operatorToCheck : output) {
            if (!operators.contains(operatorToCheck)) {
                operatorsToRemove.add(operatorToCheck);
            }
        }
        output.removeAll(operatorsToRemove);
        return output;
    }

    private void removeOperatorFromPossible(Operator operator) {
        for (Map.Entry<Integer, List<Operator>> entry : possibleOperators.entrySet()) {
            List<Operator> validOperators = entry.getValue();
            if (validOperators.contains(operator)) {
                validOperators.remove(operator);
                possibleOperators.put(entry.getKey(), validOperators);
            }
        }
    }

    private List<Operator> possibleOpcodes(List<List<Integer>> input) {
        List<Operator> output = new ArrayList<>();
        List<Integer> inputRegister = input.get(0);
        List<Integer> arguments = input.get(1);
        List<Integer> outputRegister = input.get(2);
        Calculator calculator = new Calculator(inputRegister);
        Operator[] operators = Operator.values();
        for (Operator operator : operators) {
            if (outputRegister.equals(calculator.calculate(operator, arguments))) {
                output.add(operator);
            }
        }
        return output;
    }

    private int numberOfPossibleOpCodes(List<List<Integer>> input) {
        return possibleOpcodes(input).size();

    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i += 4) {
            processBlock(input.get(i).substring(9, 19), input.get(i + 1), input.get(i + 2).substring(9, 19));
        }
    }

    private void processBlock(String line1, String line2, String line3) {
        List<Integer> input = createListFrom(line1, ", ");
        List<Integer> arguments = createListFrom(line2, " ");
        List<Integer> output = createListFrom(line3, ", ");
        this.input.add(List.of(input, arguments, output));
    }

    private List<Integer> createListFrom(String line, String separator) {
        List<Integer> output = new ArrayList<>();
        String[] numbersAsString = line.split(separator);
        for (int i = 0; i < numbersAsString.length; i++) {
            Integer number = Integer.valueOf(numbersAsString[i]);
            output.add(number);
        }
        return output;
    }
}
