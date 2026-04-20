package tzjanosi.y2018.day16;

import java.util.List;

public class Calculator {
    private List<Integer> registers;

    public Calculator(List<Integer> registers) {
        this.registers = registers;
    }

    public List<Integer> calculate(Operator operator, List<Integer> arguments) {
        return operator.execute(registers, arguments);
    }
}
