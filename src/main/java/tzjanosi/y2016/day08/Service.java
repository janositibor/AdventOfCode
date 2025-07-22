package tzjanosi.y2016.day08;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private Display display;
    private List<Operation> operations = new ArrayList<>();

    public Service(Coordinate limit, List<String> input) {
        display = new Display(limit);
        processInput(input);
    }

    public int runAllOperations() {
        for (int i = 0; i < operations.size(); i++) {
            runOperation(operations.get(i));
        }
        return calculateNumberOfTurnedOnPixels();
    }

    private int calculateNumberOfTurnedOnPixels() {
        return display.calculateNumberOfTurnedOnPixels();
    }

    private void runOperation(Operation operation) {
        switch (operation.getOperationType()) {
            case RECT:
                display.turnOn(new Coordinate(operation.getParam1(), operation.getParam2()));
                break;
            case ROW:
                display.row(operation.getParam1(), operation.getParam2());
                break;
            case COLUMN:
                display.column(operation.getParam1(), operation.getParam2());
                break;
        }
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String input) {
        if (input.contains("rect")) {
            processRect(input.substring(5));
            return;
        }
        if (input.contains("rotate row")) {
            processRow(input.substring(13));
            return;
        }
        if (input.contains("rotate column")) {
            processColumn(input.substring(16));
            return;
        }
        throw new IllegalStateException("Can1T process line: " + input);
    }

    private void processColumn(String input) {
        String[] params = input.split(" by ");
        Operation operation = new Operation(OperationType.COLUMN, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        operations.add(operation);
    }

    private void processRow(String input) {
        String[] params = input.split(" by ");
        Operation operation = new Operation(OperationType.ROW, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        operations.add(operation);
    }

    private void processRect(String input) {
        String[] params = input.split("x");
        Operation operation = new Operation(OperationType.RECT, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        operations.add(operation);
    }
}
