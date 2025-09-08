package tzjanosi.y2017.day08;

public class Register {
    private String name;
    private int value;

    public Register(String name) {
        this.name = name;
    }

    public void increment(int shift) {
        value += shift;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
