package tzjanosi.y2018.day18;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

    public int evolve(int numberOfSteps) {
        for (int i = 0; i < numberOfSteps; i++) {
            turn();
        }

        int numberOfLumberyards = findNumberOfAcres(LUMBERYARD);
        int numberOfTrees = findNumberOfAcres(TREES);
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

    public void print() {
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < size; j++) {
                Coordinate actualPosition = new Coordinate(j, i);
                Field actualField = findFieldByPosition(actualPosition, fields);
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
