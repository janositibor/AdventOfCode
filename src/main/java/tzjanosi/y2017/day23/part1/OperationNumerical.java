package tzjanosi.y2017.day23.part1;

public class OperationNumerical extends Operation {
    private long parameter2;

    public OperationNumerical(String name, Register parameter1, long parameter2) {
        super(name, parameter1);
        this.parameter2 = parameter2;
    }

    @Override
    public long getParameter2Value() {
        return parameter2;
    }

    @Override
    public String toString() {
        return super.toString() + "OperationNumerical{" +
                "parameter2=" + parameter2 +
                '}';
    }
}
