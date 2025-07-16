package tzjanosi_temp.y2015.day23;

public class Operation {
    private Action action;
    private Register register;
    private int offset;

    public Operation(Action action, Register register, int offset) {
        this.action = action;
        this.register = register;
        this.offset = offset;
    }

    public int exec() {
        switch (action) {
            case HLF:
                return register.hlf();
            case TPL:
                return register.tpl();
            case INC:
                return register.inc();
            case JMP:
                return register.jmp(offset);
            case JIE:
                return register.jie(offset);
            case JIO:
                return register.jio(offset);
            default:
                throw new IllegalArgumentException("No action found: " + action);
        }
    }

    @Override
    public String toString() {
        return "Operation{" +
                "action=" + action +
                ", register=" + register +
                ", offset=" + offset +
                '}';
    }

    public Action getAction() {
        return action;
    }

    public Register getRegister() {
        return register;
    }

    public int getOffset() {
        return offset;
    }
}
