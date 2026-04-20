package tzjanosi.y2018.day16;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<List<List<Integer>>> input = new ArrayList<>();

    public Service(List<String> input) {
        processInput(input);
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

    private int numberOfPossibleOpCodes(List<List<Integer>> input) {
        int count = 0;
        List<Integer> inputRegister = input.get(0);
        List<Integer> arguments = input.get(1);
        List<Integer> outputRegister = input.get(2);
        Calculator calculator = new Calculator(inputRegister);
        Operator[] operators = Operator.values();
        for (Operator operator : operators) {
            if (outputRegister.equals(calculator.calculate(operator, arguments))) {
                count++;
            }
        }
        return count;
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
