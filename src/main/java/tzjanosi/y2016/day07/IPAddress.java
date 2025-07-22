package tzjanosi.y2016.day07;

import java.util.ArrayList;
import java.util.List;

public class IPAddress {
    private List<String> outsideBrackets = new ArrayList<>();
    private List<String> insideBrackets = new ArrayList<>();

    public IPAddress(String input) {
        String stringToProcess = input;
        int positionOfNextBracket = input.indexOf('[');
        int positionOfNextClosingBracket = input.indexOf(']');
        while (stringToProcess.length() > 0 && positionOfNextBracket > -1) {
            addOutsideBrackets(stringToProcess, positionOfNextBracket);
            addInsideBrackets(stringToProcess, positionOfNextBracket, positionOfNextClosingBracket);

            stringToProcess = stringToProcess.substring(positionOfNextClosingBracket + 1);
            positionOfNextBracket = stringToProcess.indexOf('[');
            positionOfNextClosingBracket = stringToProcess.indexOf(']');
        }
        outsideBrackets.add(stringToProcess);
    }

    public boolean supportSSL() {
        for (int i = 0; i < outsideBrackets.size(); i++) {
            if (isAba(outsideBrackets.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isAba(String input) {
        for (int i = 0; i < input.length() - 2; i++) {
            if (isAbaAtPosition(input, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAbaAtPosition(String input, int index) {
        if (input.charAt(index) == input.charAt(index + 2)
                && input.charAt(index) != input.charAt(index + 1)) {
            String stringToFind = new String(new char[]{input.charAt(index + 1), input.charAt(index), input.charAt(index + 1)});
            return inContains(stringToFind);
        }
        return false;
    }

    private boolean inContains(String stringToFind) {
        for (int i = 0; i < insideBrackets.size(); i++) {
            if (insideBrackets.get(i).contains(stringToFind)) {
                return true;
            }
        }
        return false;
    }

    public boolean supportTLS() {
        return !insideIsAbba() && outsideIsAbba();
    }

    private boolean outsideIsAbba() {
        return isAbbaList(outsideBrackets);
    }

    private boolean insideIsAbba() {
        return isAbbaList(insideBrackets);
    }

    private boolean isAbbaList(List<String> listToCheck) {
        for (int i = 0; i < listToCheck.size(); i++) {
            if (isAbba(listToCheck.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isAbba(String input) {
        for (int i = 0; i < input.length() - 3; i++) {
            if (isAbbaAtPosition(input, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAbbaAtPosition(String input, int index) {
        return input.charAt(index) == input.charAt(index + 3)
                && input.charAt(index + 1) == input.charAt(index + 2)
                && input.charAt(index) != input.charAt(index + 1);
    }


    private void addInsideBrackets(String stringToProcess, int positionOfNextBracket, int positionOfNextClosingBracket) {
        String stringToInsert = stringToProcess.substring(positionOfNextBracket + 1, positionOfNextClosingBracket);
        insideBrackets.add(stringToInsert);
    }

    private void addOutsideBrackets(String stringToProcess, int positionOfNextBracket) {
        if (positionOfNextBracket > 0) {
            String stringToInsert = stringToProcess.substring(0, positionOfNextBracket);
            outsideBrackets.add(stringToInsert);
        }
    }

    public List<String> getOutsideBrackets() {
        return outsideBrackets;
    }

    public List<String> getInsideBrackets() {
        return insideBrackets;
    }
}
