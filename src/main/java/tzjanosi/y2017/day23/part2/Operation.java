package tzjanosi.y2017.day23.part2;

public class Operation {
    private String name;
    private Register parameter1;

    public Operation(String name, Register parameter1) {
        this.name = name;
        this.parameter1 = parameter1;
    }

    public String getName() {
        return name;
    }

    public Register getParameter1() {
        return parameter1;
    }

    public long getParameter2Value() {
        return 0;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "name='" + name + '\'' +
                ", parameter1=" + parameter1 +
                '}';
    }
}
