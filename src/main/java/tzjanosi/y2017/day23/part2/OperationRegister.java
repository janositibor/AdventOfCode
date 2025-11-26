package tzjanosi.y2017.day23.part2;

public class OperationRegister extends Operation {
    private Register parameter2;

    public OperationRegister(String name, Register parameter1, Register parameter2) {
        super(name, parameter1);
        this.parameter2 = parameter2;
    }

    @Override
    public long getParameter2Value() {
        return parameter2.getValue();
    }

    @Override
    public String toString() {
        return super.toString() + "OperationRegister{" +
                "parameter2=" + parameter2 +
                '}';
    }
}
