package tzjanosi.y2018.day21;

import java.util.List;

public class State {
    private int ipRegister;
    private List<Long> registers;

    public State(int ipRegister, List<Long> registers) {
        this.ipRegister = ipRegister;
        this.registers = registers;
    }

    public int getIpRegister() {
        return ipRegister;
    }

    public long getIpValue() {
        return registers.get(ipRegister);
    }

    public List<Long> getRegisters() {
        return registers;
    }
}
