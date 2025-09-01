package tzjanosi.y2017.day07;

import java.util.ArrayList;
import java.util.List;

public class Circus {
    private List<Program> programs = new ArrayList<>();

    public Circus(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            readLine(input.get(i));
        }
        connectPrograms();
    }

    public String findRoot() {
        return programs.stream()
                .filter(f -> f.getParent() == null)
                .map(Program::getName)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("No root found")));

    }


    private void connectPrograms() {
        for (int i = 0; i < programs.size(); i++) {
            Program actual = programs.get(i);
            if (!actual.getChildrenNames().isEmpty()) {
                connectChildren(actual);
            }
        }
    }

    private void connectChildren(Program actual) {
        for (int i = 0; i < actual.getChildrenNames().size(); i++) {
            Program child = findProgramByName(actual.getChildrenNames().get(i));
            actual.addChild(child);
        }
    }

    private Program findProgramByName(String name) {
        return programs.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("No program found with the name: %s", name)));
    }

    private void readLine(String line) {
        if (line.contains(" -> ")) {
            int separatorIndex = line.indexOf(" -> ");
            Program program = readLeaf(line.substring(0, separatorIndex));
            readConnection(program, line.substring(separatorIndex + 4));
        } else {
            readLeaf(line);
        }
    }

    private void readConnection(Program program, String childrenAsString) {
        String[] childrenNames = childrenAsString.split(", ");
        for (int i = 0; i < childrenNames.length; i++) {
            program.addChildName(childrenNames[i]);
        }
    }

    private Program readLeaf(String input) {
        String temp = input.replace(")", "");
        String[] words = temp.split(" \\(");
        Program program = new Program(words[0], Integer.parseInt(words[1]));
        programs.add(program);
        return program;
    }
}
