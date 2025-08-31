package tzjanosi.y2017.day05;

import java.util.ArrayList;
import java.util.List;

public class Cpu {
    private List<Command> commands = new ArrayList<>();
    private int index;

    public Cpu(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            Command command = new Command(Integer.parseInt(input.get(i)));
            commands.add(command);
        }
    }

    public int run() {
        int counter = 0;
        int limit = commands.size();
        while (index < limit) {
            counter++;
            index += commands.get(index).run();
        }
        return counter;
    }
}
