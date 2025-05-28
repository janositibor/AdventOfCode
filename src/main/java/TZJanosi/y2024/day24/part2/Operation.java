package TZJanosi.y2024.day24.part2;

import java.util.List;
import java.util.Set;

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

    public Operation() {
    }

    public boolean match(Operation other) {
        if (other.getOperand2() == null) {
            if (!containsOperand1From(other)) {
                return false;
            }
        } else {
            if (!containsBothOperandFrom(other)) {
                return false;
            }
        }
        if (other.result != null && !resultEqualsTo(other)) {
            return false;
        }
        if (other.operator != null && !operatorEqualsTo(other)) {
            return false;
        }
        return true;
    }

    private boolean operatorEqualsTo(Operation other) {
        return operator.equals(other.operator);
    }

    private boolean resultEqualsTo(Operation other) {
        return result.getName().equals(other.result.getName());
    }

    private boolean containsBothOperandFrom(Operation other) {
        Set<String> operandNames = Set.of(operand1.getName(), operand2.getName());
        return operandNames.equals(Set.of(other.getOperand1().getName(), other.getOperand2().getName()));
    }

    private boolean containsOperand1From(Operation other) {
        List<String> operandNames = List.of(operand1.getName(), operand2.getName());
        return operandNames.contains(other.getOperand1().getName());
    }

    public void calculate() {
        result.setValues(operator.calculate(operand1.getValue(), operand2.getValue()));
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

    public void setResult(Operand result) {
        this.result = result;
    }

    public void setDone(boolean done) {
        this.done = done;
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
                '}';
    }

    public String getOtherOperandName(String operandName) {
        if (this.operand1.getName().equals(operandName) || this.operand2.getName().equals(operandName)) {
            return (this.operand1.getName().equals(operandName) ? this.operand2.getName() : this.operand1.getName());
        }
        throw new IllegalStateException("no otherOperand for: " + operandName);
    }

    public void setOperand1(Operand operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(Operand operand2) {
        this.operand2 = operand2;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
