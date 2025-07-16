package tzjanosi.y2015.day07;

public class Operation {
    public Operand operand1;
    public Operand operand2;
    public Operand result;
    public Operator operator;
    public boolean done;

    public Operation(Operand operand1, Operand operand2, Operand result, Operator operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.operator = operator;
    }

    public void calculate() {
        result.setValue(operator.calculate(operand1.getValue(), operand2.getValue()));
        done = true;
    }

    public boolean waitForProcess() {
        return bothOperandAreValid() && !isDone();
    }

    public boolean isDone() {
        return done;
    }

    public boolean bothOperandAreValid() {
        return operand1.isValid() && operand2.isValid();
    }

    public Operand getOperand1() {
        return operand1;
    }

    public Operand getOperand2() {
        return operand2;
    }

    public Operand getResult() {
        return result;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", result=" + result +
                ", operator=" + operator +
                ", done=" + done +
                "}\n";
    }
}
