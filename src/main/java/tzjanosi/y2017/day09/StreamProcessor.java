package tzjanosi.y2017.day09;

import java.util.ArrayList;
import java.util.List;

public class StreamProcessor {
    private List<Group> groups = new ArrayList<>();
    private Group actual;
    private boolean inGarbage;
    private int index;
    private int numberOfGarbageCharacters;

    public StreamProcessor(String input) {
        actual = new Group(null, 0);
        groups.add(actual);
        process(input);
    }

    private void process(String input) {
        String[] letters = input.split("");
        while (index < input.length()) {
            processLetter(letters[index]);
        }
    }

    private void processLetter(String letter) {
        switch (letter) {
            case "{":
                openGroup();
                break;
            case "}":
                closeGroup();
                break;
            case "<":
                enterGarbage();
                break;
            case ">":
                leaveGarbage();
                break;
            case "!":
                skip();
                break;
            default:
                garbageCharacter();
                break;
        }
        index++;
    }

    private void garbageCharacter() {
        if (inGarbage) {
            numberOfGarbageCharacters++;
        }
    }

    private void skip() {
        index++;
    }

    private void leaveGarbage() {
        inGarbage = false;
    }

    private void enterGarbage() {
        garbageCharacter();
        if (!inGarbage) {
            inGarbage = true;
        }
    }

    private void closeGroup() {
        garbageCharacter();
        if (!inGarbage) {
            actual = actual.getParent();
        }
    }

    private void openGroup() {
        garbageCharacter();
        if (!inGarbage) {
            Group newGroup = new Group(actual, actual.getScore() + 1);
            actual.addChild(newGroup);
            actual = newGroup;
        }
    }

    public int calculateTotalScore() {
        return groups.stream().mapToInt(Group::totalScore).sum();
    }

    public int getNumberOfGarbageCharacters() {
        return numberOfGarbageCharacters;
    }
}
