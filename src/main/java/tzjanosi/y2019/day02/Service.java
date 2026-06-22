package tzjanosi.y2019.day02;

public class Service {
    private int[] originalNumbers;
    private int[] numbers;

    public Service(String input) {
        processInput(input);
    }

    public int findNounAndVerb() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                preparation(i, j);
                if (run() == 19690720) {
                    return 100 * i + j;
                }
            }
        }
        throw new IllegalStateException("No proper noun an verb found!");
    }

    public void preparation(int noun, int verb) {
        for (int i = 0; i < originalNumbers.length; i++) {
            numbers[i] = originalNumbers[i];
        }
        numbers[1] = noun;
        numbers[2] = verb;
    }

    public int run() {
        int index = 0;
        boolean completed = false;
        while (!completed) {
            if (numbers[index] == 99) {
                completed = true;
            } else {
                int argument1 = numbers[numbers[index + 1]];
                int argument2 = numbers[numbers[index + 2]];
                int result = numbers[index] == 1 ? argument1 + argument2 : argument1 * argument2;
                numbers[numbers[index + 3]] = result;
            }
            index += 4;
        }
        return numbers[0];
    }

    private void processInput(String line) {
        String[] numbers = line.split(",");
        this.numbers = new int[numbers.length];
        this.originalNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            this.originalNumbers[i] = Integer.parseInt(numbers[i]);
            this.numbers[i] = originalNumbers[i];
        }
    }
}
