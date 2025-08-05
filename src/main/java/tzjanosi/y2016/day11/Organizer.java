package tzjanosi.y2016.day11;

import java.util.*;

public class Organizer {
    private Queue<BuildingMap> maps = new LinkedList<>();
    private Set<Integer> alreadyProcessed = new HashSet<>();
    private BuildingMap start;

    public Organizer(List<String> input) {
        start = new BuildingMap(input);
        maps.add(start);
        alreadyProcessed.add(start.getIdMark());
    }


    public int findBestWay() {
        while (true) {
            BuildingMap actual = maps.poll();
            List<List<Person>> peopleToMove = actual.peopleToMove();
            List<Direction> directions = actual.calculateDirectionToLevel();
            Integer result = processState(directions, peopleToMove, actual);
            if (result > -1) {
                return result;
            }
            alreadyProcessed.add(actual.getIdMark());
        }
    }

    private Integer processState(List<Direction> directions, List<List<Person>> peopleToMove, BuildingMap actual) {
        for (int j = 0; j < directions.size(); j++) {
            for (int i = 0; i < peopleToMove.size(); i++) {
                if (peopleToMove.get(i).size() == 1 || directions.get(j) == Direction.UP) {
                    int result = processStep(i, j, actual, peopleToMove, directions);
                    if (result > -1) {
                        return result;
                    }
                }
            }
        }
        return -1;
    }

    private int processStep(int i, int j, BuildingMap actual, List<List<Person>> peopleToMove, List<Direction> directions) {
        BuildingMap next = new BuildingMap(actual);
        if (next.move(peopleToMove.get(i), directions.get(j))) {
            if (next.isSuccess()) {
                return next.getSteps();
            }
            if (isANewMap(next)) {
                maps.add(next);
            }
        }
        return -1;
    }

    private boolean isANewMap(BuildingMap mapToCheck) {
        boolean notInMaps = maps.stream().filter(mp -> mp.getElevator() == mapToCheck.getElevator()).noneMatch(mp -> mp.getIdMark() == mapToCheck.getIdMark());
        boolean notAlreadyProcessed = false;
        if (notInMaps) {
            notAlreadyProcessed = !alreadyProcessed.contains(mapToCheck.getIdMark());
        }
        return notInMaps && notAlreadyProcessed;
    }
}
