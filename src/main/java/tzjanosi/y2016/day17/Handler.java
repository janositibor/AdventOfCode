package tzjanosi.y2016.day17;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Handler {
    private Queue<Labyrinth> labyrinthToProcess = new LinkedList<>();
    private List<Labyrinth> succeed = new ArrayList<>();

    public Handler(String secretKey) {
        labyrinthToProcess.add(new Labyrinth(secretKey));
    }

    public String findBestWay() {
        while (true) {
            Labyrinth actual = labyrinthToProcess.remove();
            if (actual.isSuccess()) {
                return actual.getPath();
            }
            process(actual);
        }
    }

    public int findLongestWay() {
        while (!labyrinthToProcess.isEmpty()) {
            Labyrinth actual = labyrinthToProcess.remove();
            if (actual.isSuccess()) {
                succeed.add(actual);
            } else {
                process(actual);
            }
        }
        return succeed.getLast().getPath().length();
    }

    private void process(Labyrinth actual) {
        List<Direction> directions = actual.validDirections();
        for (Direction direction : directions) {
            Labyrinth next = new Labyrinth(actual);
            if (next.move(direction)) {
                labyrinthToProcess.add(next);
            }
        }
    }
}
