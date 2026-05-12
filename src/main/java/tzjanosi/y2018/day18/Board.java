package tzjanosi.y2018.day18;

import java.util.*;
import java.util.stream.Collectors;

import static tzjanosi.y2018.day18.Acre.LUMBERYARD;
import static tzjanosi.y2018.day18.Acre.TREES;

public class Board {
    private int size;
    private Set<Field> fields = new HashSet<>();

    public Board(List<String> input) {
        size = input.size();
        processInput(input);
    }

    public int longEvolve(int numberOfSteps) {
        int startOfPeriod = 512;
        int period = 28;
        int actualSteps = startOfPeriod + (numberOfSteps - startOfPeriod) % period;
        for (int i = 0; i < actualSteps; i++) {
            turn();
        }
        return calculateResourceValue();
    }

    public String findPeriod(int maxNumberOfSteps) {
        List<Set<Field>> listOfFields = new ArrayList<>();
        listOfFields.add(fields);
        for (int i = 1; i <= maxNumberOfSteps; i++) {
            turn();
            int startOfPeriod = findEquals(listOfFields);
            if (startOfPeriod > -1) {
                return (startOfPeriod + 1) + "-" + (i - startOfPeriod);
            }
            listOfFields.add(fields);
        }
        throw new IllegalStateException("No periodicity found!");
    }

    private boolean areEqualsSet(Set<Field> set1, Set<Field> set2) {
        Iterator<Field> iterator = set1.iterator();
        while (iterator.hasNext()) {
            Field field1 = iterator.next();
            Field field2 = findFieldByPosition(field1.getPosition(), set2);
            if (field1.getType() != field2.getType()) {
                return false;
            }
        }
        return true;
    }

    private int findEquals(List<Set<Field>> listOfFields) {
        for (int i = listOfFields.size() - 1; i >= 0; i--) {
            if (areEqualsSet(fields, listOfFields.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public int evolve(int numberOfSteps) {
        for (int i = 0; i < numberOfSteps; i++) {
            turn();
        }
        return calculateResourceValue();
    }

    private int calculateResourceValue() {
        int numberOfLumberyards = findNumberOfAcres(LUMBERYARD);
        int numberOfTrees = findNumberOfAcres(TREES);
//        System.out.println(numberOfLumberyards+" * "+numberOfTrees);
        return numberOfLumberyards * numberOfTrees;
    }

    private void turn() {
        Set<Field> tempFields = copyFields();
        Iterator<Field> iterator = tempFields.iterator();
        while (iterator.hasNext()) {
            Field actualFieldToTurn = iterator.next();
            Set<Field> neighbours = findNeighbourFields(actualFieldToTurn);
            actualFieldToTurn.turn(neighbours);
        }
        fields = tempFields;
    }

    public void print(Set<Field> input) {
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < size; j++) {
                Coordinate actualPosition = new Coordinate(j, i);
                Field actualField = findFieldByPosition(actualPosition, input);
                stringBuilder.append(actualField.getType().getSign());
            }
            System.out.println(stringBuilder);
        }
    }

    private int findNumberOfAcres(Acre type) {
        return (int) fields.stream().filter(f -> f.getType().equals(type)).count();
    }

    private Field findFieldByPosition(Coordinate position, Set<Field> from) {
        return from.stream()
                .filter(f -> f.getPosition().equals(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No field found for the position: " + position));
    }

    private Set<Field> findNeighbourFields(Field field) {
        return fields.stream().filter(f -> f.getPosition().isNeighbour(field.getPosition())).collect(Collectors.toSet());
    }

    private Set<Field> copyFields() {
        Set<Field> output = new HashSet<>();
        Iterator<Field> iterator = fields.iterator();
        while (iterator.hasNext()) {
            Field actual = iterator.next();
            output.add(new Field(actual));
        }
        return output;
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(i, line);
        }
    }

    private void processLine(int y, String line) {
        for (int i = 0; i < line.length(); i++) {
            Coordinate position = new Coordinate(i, y);
            char sign = line.charAt(i);
            Acre type = findAcreBySign(sign);
            Field field = new Field(position, type);
            fields.add(field);
        }
    }

    private Acre findAcreBySign(char sign) {
        Acre[] acres = Acre.values();
        for (int i = 0; i < acres.length; i++) {
            Acre actual = acres[i];
            if (actual.getSign() == sign) {
                return actual;
            }
        }
        throw new IllegalArgumentException("Unexpected sign: " + sign);
    }
}
