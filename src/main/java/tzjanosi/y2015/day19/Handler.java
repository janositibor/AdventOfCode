package tzjanosi.y2015.day19;

import java.util.*;
import java.util.stream.Collectors;

public class Handler {
    private Producer moleculeProducer;
    private String destination;
    private int numberOfSteps;
    private Set<String> alreadyProcessed = new HashSet<>();
    private Set<String> units = Set.of("Al", "Ar", "B", "Ca", "CRn", "F", "H", "Mg", "N", "O", "P", "Rn", "Si", "Ti", "Th", "Y");

    public Handler(List<String> input) {
        moleculeProducer = new Producer(input);
        destination = moleculeProducer.getBasicMolecule();
    }

    public int countSteps() {
        int lengthOfDestination = countUnits(destination);
        int lengthOfStart = "e".length();
        int countOfQuatro = countOfSubStringInString(destination, "Ar");
        int countOfDouble = countOfSubStringInString(destination, "Y");
        System.out.println(countOfQuatro);
        System.out.println(countOfDouble);
        return lengthOfDestination - lengthOfStart - 2 * countOfQuatro - 2 * countOfDouble;
    }

    private int countUnits(String destination) {
        int output = 0;
        for (String unit : units) {
            output += countOfSubStringInString(destination, unit);
        }
        return output;
    }

    private int countOfSubStringInString(String string, String subString) {
        return string.split(subString, -1).length - 1;
    }

    public int findNumberOfSteps() {
        calculateLevel(1, Set.of("e"));
        return numberOfSteps;
    }

    private void calculateLevel(int i, Set<String> moleculesToCheck) {
        Set<String> result = new HashSet<>();
        for (String molecule : moleculesToCheck) {
            moleculeProducer.setBasicMolecule(molecule);
            moleculeProducer.findNumberOfNewMolecules();
            Set<String> createdMolecules = moleculeProducer.getCreatedMolecules();
            if (createdMolecules.contains(destination)) {
                numberOfSteps = i;
                return;
            }
            result.addAll(createdMolecules);
        }
        Set<String> output = result.stream().filter(this::keepInTheResultSet).collect(Collectors.toSet());
        calculateLevel(i + 1, output);
    }

    private boolean keepInTheResultSet(String molecule) {
        return !alreadyProcessed.contains(molecule) && molecule.length() < destination.length();
    }
}
