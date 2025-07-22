package tzjanosi.y2016.day08;

public class Operation {
    private OperationType operationType;
    private int param1;
    private int param2;

    public Operation(OperationType operationType, int param1, int param2) {
        this.operationType = operationType;
        this.param1 = param1;
        this.param2 = param2;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public int getParam1() {
        return param1;
    }

    public int getParam2() {
        return param2;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationType=" + operationType +
                ", param1=" + param1 +
                ", param2=" + param2 +
                '}';
    }
}
