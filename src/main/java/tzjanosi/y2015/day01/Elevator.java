package tzjanosi.y2015.day01;

public class Elevator {
    private String input;

    public Elevator(String input) {
        this.input = input;
    }

    public int calculateFloor() {
        int output = 0;
        String[] inputAsStringArray = input.split("");
        for (int i = 0; i < inputAsStringArray.length; i++) {
            output += movement(inputAsStringArray[i]);
        }
        return output;
    }

    public int whenWillReachBasement() {
        String[] inputAsStringArray = input.split("");
        int index = 0;
        int floor = 0;
        while (floor > -1 && index < inputAsStringArray.length) {
            index++;
            floor += movement(inputAsStringArray[index - 1]);

        }
        return index;
    }

    private int movement(String s) {
        switch (s) {
            case "(":
                return 1;
            case ")":
                return -1;
            default:
                throw new IllegalArgumentException("Unknown character in the input string: " + s);
        }
    }
}
