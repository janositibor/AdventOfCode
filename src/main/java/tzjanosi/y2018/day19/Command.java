package tzjanosi.y2018.day19;

import java.util.List;

public class Command {
    private Operator operator;
    private List<Integer> arguments;

    public Command(Operator operator, List<Integer> arguments) {
        this.operator = operator;
        this.arguments = arguments;
    }

    public State execute(State state) {
        List<Integer> updatedRegisters = operator.execute(state.getRegisters(), arguments);
        int ipRegister = state.getIpRegister();
        updatedRegisters.set(ipRegister, updatedRegisters.get(ipRegister) + 1);
        return new State(ipRegister, updatedRegisters);
    }
}
