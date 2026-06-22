package tzjanosi.y2019.day02;

public class Service {
    private int[] numbers;

    public Service(String input) {
        processInput(input);
    }

    public void preparation() {
        numbers[1] = 12;
        numbers[2] = 2;
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
        for (int i = 0; i < numbers.length; i++) {
            this.numbers[i] = Integer.parseInt(numbers[i]);
        }
    }
}
