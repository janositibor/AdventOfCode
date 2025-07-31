package tzjanosi.y2016.day11;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Organizer {
    private Queue<BuildingMap> maps = new LinkedList<>();
    private Set<BuildingMap> alreadyProcessedMaps = new HashSet<>();
    private BuildingMap start;
    private Instant startTime;


    public Organizer(List<String> input) {
        start = new BuildingMap(input);
        maps.add(start);
    }


    public int findBestWay() {
        startTime = Instant.now();
        int counter = 0;
        while (true) {
            counter++;
            BuildingMap actual = maps.poll();
            List<List<Person>> peopleToMove = actual.peopleToMove();
            List<Direction> directions = actual.calculateDirectionToLevel();
            for (int i = 0; i < peopleToMove.size(); i++) {
                for (int j = 0; j < directions.size(); j++) {
                    int result = processStep(i, j, actual, peopleToMove, directions);
                    if (result > -1) {
                        return result;
                    }
                }
            }
            alreadyProcessedMaps.add(actual);
            measureTimeAndReset(counter + ": ");
        }
    }

    private int processStep(int i, int j, BuildingMap actual, List<List<Person>> peopleToMove, List<Direction> directions) {
        BuildingMap next = new BuildingMap(actual);
        if (next.move(peopleToMove.get(i), directions.get(j))) {
            if (next.isSuccess()) {
                return next.getSteps();
            }
            boolean isANewMap = isANewMap(next);
            if (isANewMap) {
                maps.add(next);
            }
        }
        return -1;
    }

    private void measureTimeAndReset(String info) {
        measureTime(info);
        startTime = Instant.now();
    }

    private void measureTime(String info) {
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        if (duration.toMillis() > 0) {
            System.out.println(info + ": " + duration.toMillis() + " ms");
        }
    }

    private boolean isANewMap(BuildingMap mapToCheck) {
        boolean isInMaps = maps.stream().filter(mp -> mp.getElevator() == mapToCheck.getElevator()).noneMatch(mp -> mp.equalState(mapToCheck));
        boolean isAlreadyProcessed = false;
        if (isInMaps) {
            isAlreadyProcessed = !alreadyProcessedMaps.contains(mapToCheck);
        }
        return isInMaps && isAlreadyProcessed;
    }
}
