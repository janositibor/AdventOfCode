package tzjanosi.y2018.day19;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private State state;
    private List<Command> commands = new ArrayList<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int execute() {
        int numberOfCommands = commands.size();
        while (state.getIpValue() < numberOfCommands) {
            Command command = commands.get(state.getIpValue());
            state = command.execute(state);
//            System.out.println(state.getRegisters());
        }
        int output = state.getRegisters().get(0);
        if (state.getIpRegister() == 0) {
            output--;
        }
        return output;
    }

    private void processInput(List<String> input) {
        int ipRegister = input.get(0).charAt(4) - '0';
        state = new State(ipRegister, new ArrayList<>(List.of(0, 0, 0, 0, 0, 0)));
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
