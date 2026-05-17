package tzjanosi.y2018.day19;

import java.util.List;

public class State {
    private int ipRegister;
    private List<Integer> registers;

    public State(int ipRegister, List<Integer> registers) {
        this.ipRegister = ipRegister;
        this.registers = registers;
    }

    public int getIpRegister() {
        return ipRegister;
    }

    public int getIpValue() {
        return registers.get(ipRegister);
    }

    public List<Integer> getRegisters() {
        return registers;
    }
}
