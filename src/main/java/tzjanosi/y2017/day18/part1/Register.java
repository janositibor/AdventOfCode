package tzjanosi.y2017.day18.part1;

public class Register {
    private String name;
    private long value;

    public Register(String name) {
        this.name = name;
    }

    public Register(String name, long value) {
        this.name = name;
        this.value = value;
    }


    public void mod(long valueToModWith) {
        value = (value % valueToModWith);
    }

    public void multiply(long valueToMultiplyWith) {
        value *= valueToMultiplyWith;
    }

    public void add(long valueToAdd) {
        value += valueToAdd;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Register{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
