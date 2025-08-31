package tzjanosi.y2017.day05;

public class Command {
    private int value;

    public Command(int value) {
        this.value = value;
    }

    public int run() {
        int output = value;
        value++;
        return output;
    }

    public int runPart2() {
        int output = value;
        if (value >= 3) {
            value--;
        } else {
            value++;
        }
        return output;
    }
}
