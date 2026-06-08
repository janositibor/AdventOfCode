package tzjanosi.y2018.day21;

import java.util.*;

public class Service {
    private State state;
    private List<Command> commands = new ArrayList<>();
    @SuppressWarnings("PMD.LooseCoupling")
    private LinkedHashSet<Long> reg3Values = new LinkedHashSet<>();

    public Service(List<String> input, long firstRegister) {
        processInput(input, firstRegister);
    }

    public long findMaxBySimplifiedExecution() {
        long reg3 = 0;
        int reg4;
        while (true) {
            reg4 = (int) (reg3 | 65536);
            reg3 = commands.get(7).getArguments().get(0);
            int reg5 = 0;
            while (reg4 >= 256) {
                reg5 = reg4 % 256;
                reg3 = updateRegister3(reg3, reg5);
                reg5 = reg4 / 256;
                reg4 = reg5;
            }
            reg3 = updateRegister3(reg3, reg5);
            if (reg3Values.contains(reg3)) {
                return reg3Values.getLast();
            } else {
                reg3Values.add(reg3);
            }
        }
    }

    public long simplifiedExecution() {
        long reg3 = commands.get(7).getArguments().get(0);
        int reg4 = commands.get(6).getArguments().get(1);
        int reg5 = 0;
        while (reg4 >= 256) {
            reg5 = reg4 % 256;
            reg3 = updateRegister3(reg3, reg5);
            reg5 = reg4 / 256;
            reg4 = reg5;
        }
        return updateRegister3(reg3, reg5);
    }

    private long updateRegister3(long reg3, int reg5) {
        long output = reg3 + reg5;
        output = output % (256 * 256 * 256);
        output *= 65899;
        output = output % (256 * 256 * 256);
        return output;
    }

    public long execute() {
        int numberOfCommands = commands.size();
        System.out.println(state.getIpValue());
        while (state.getIpValue() < numberOfCommands) {
            Command command = commands.get((int) state.getIpValue());
            state = command.execute(state);
            System.out.println(state.getRegisters());
            System.out.println(state.getIpValue());
        }
        long output = state.getRegisters().get(0);
        if (state.getIpRegister() == 0) {
            output--;
        }
        return output;
    }

    private void processInput(List<String> input, long firstRegister) {
        int ipRegister = input.get(0).charAt(4) - '0';
        state = new State(ipRegister, new ArrayList<>(List.of(firstRegister, 0L, 0L, 0L, 0L, 0L)));
        for (int i = 1; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        Operator operator = findOperatorByName(words[0]);
        List<Integer> arguments = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            arguments.add(Integer.valueOf(words[i]));
        }
        commands.add(new Command(operator, arguments));
    }

    private Operator findOperatorByName(String name) {
        Operator[] operators = Operator.values();
        for (Operator operator : operators) {
            if (operator.name().equalsIgnoreCase(name)) {
                return operator;
            }
        }
        throw new IllegalStateException("No operator found with name: " + name);
    }
}
