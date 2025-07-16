package tzjanosi.y2024.day20.part1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Cheater {
    private int gainLimit;
    private Labyrinth labyrinth;
    private List<Coordinate> originalWalls;
    private int originalLength;
    private List<Coordinate> originalWay;
    private List<Coordinate> wallsToOmit;
    private int limitX;
    private int limitY;
    private Map<Coordinate, Integer> results = new ConcurrentHashMap<>();


    public Cheater(List<String> input, int gainLimit) {
        this.gainLimit = gainLimit;
        labyrinth = new Labyrinth(input);
        originalWalls = labyrinth.getWalls();
        originalLength = labyrinth.calculateWay();
        originalWay = labyrinth.getWayOutList();
        setLimits();
        wallsToOmit = findWallsToOmit();
    }

    public int calculateCheatRoutes() {
        System.out.println("gainLimit: " + gainLimit);
//        System.out.printf("Number of possible searches is: %d\n", wallsToOmit.size());
        int output = 0;
        for (int i = 0; i < wallsToOmit.size(); i++) {
            System.out.printf("Actual search is: %d/%d\n", i + 1, wallsToOmit.size());
            List<Coordinate> neighbourWaysCoordinates = neighbourWayCoordinates(wallsToOmit.get(i));
            int numberOfNeighbours = neighbourWaysCoordinates.size();
            int newLength = 0;
            int calculateLength;
            Labyrinth newLabyrinth;
//            System.out.printf("numberOfNeighbours is: %d, ",numberOfNeighbours);
            switch (numberOfNeighbours) {
                case 3:
                    newLength = originalLength - 2;
                    break;
                case 2:
                    newLabyrinth = createNewLabyrinth(i, neighbourWaysCoordinates.get(0), neighbourWaysCoordinates.get(1), originalLength + 2);
                    newLabyrinth.setWalls(originalWalls);
                    calculateLength = newLabyrinth.calculateWay();
//                    System.out.printf("calculateLength is: %d, ",calculateLength);
                    newLength = (originalLength - calculateLength + 2) > 0 ? originalLength - calculateLength + 2 : Integer.MAX_VALUE;
                    break;
                case 1:
                    newLabyrinth = createNewLabyrinth(i, labyrinth.getStart(), labyrinth.getEnd(), originalLength - gainLimit);
                    newLength = newLabyrinth.calculateWay();
//                    System.out.printf("newLength is: %d, ",newLength);
                    break;
                default:
                    throw new IllegalArgumentException("Number of neighbours should be between 1 and 3.");
            }

//            System.out.printf("newLength is: %d, ", newLength);
            results.put(wallsToOmit.get(i), newLength);
            if (originalLength - newLength >= gainLimit) {
                output++;
//                System.out.printf("so count is: %d", output);
            }
//            System.out.println();

        }
        return output;
    }

    private List<Coordinate> neighbourWayCoordinates(Coordinate location) {
        List<Coordinate> output = new ArrayList<>();
        Coordinate neighbour;
        for (int i = -1; i <= 1; i += 2) {
            neighbour = new Coordinate(location.getX() + i, location.getY());
            if (originalWay.contains(neighbour)) {
                output.add(neighbour);
            }
        }
        for (int j = -1; j <= 1; j += 2) {
            neighbour = new Coordinate(location.getX(), location.getY() + j);
            if (originalWay.contains(neighbour)) {
                output.add(neighbour);
            }
        }
        return output;
    }

    private Labyrinth createNewLabyrinth(int i, Coordinate start, Coordinate end, int limit) {
        Labyrinth output = new Labyrinth();
        output.setStart(start);
        output.setEnd(end);
        output.setGlobalLimit(limit);

        List<Coordinate> newWalls = new ArrayList<>(originalWalls);
        newWalls.remove(wallsToOmit.get(i));
        output.setWalls(newWalls);
        return output;
    }


    private List<Coordinate> findWallsToOmit() {
//        return originalWalls.stream().filter(this::notInTheFrame).filter(c -> neighbours(c).stream().anyMatch(c2 -> originalWay.contains(c2))).toList();
        return originalWalls.stream().filter(this::notInTheFrame).filter(c -> neighbours(c).stream().anyMatch(originalWay::contains)).toList();
    }

    private boolean notInTheFrame(Coordinate coordinate) {
        return (0 < coordinate.getX() && 0 < coordinate.getY() && coordinate.getX() < limitX && coordinate.getY() < limitY);
    }

    private List<Coordinate> neighbours(Coordinate coordinate) {
        List<Coordinate> output = new ArrayList<>();
        Coordinate neighbour;
        for (int i = -1; i <= 1; i += 2) {
            neighbour = new Coordinate(coordinate.getX() + i, coordinate.getY());
            output.add(neighbour);
        }
        for (int j = -1; j <= 1; j += 2) {
            neighbour = new Coordinate(coordinate.getX(), coordinate.getY() + j);
            output.add(neighbour);
        }
        return output;

    }

    private void setLimits() {
        setXLimit();
        setYLimit();
    }

    private void setYLimit() {
        limitY = originalWalls.stream().mapToInt(Coordinate::getY).max().getAsInt();
    }

    private void setXLimit() {
        limitX = originalWalls.stream().mapToInt(Coordinate::getX).max().getAsInt();
    }

    public long getOriginalLength() {
        return originalLength;
    }

    public List<Coordinate> getWallsToOmit() {
        return wallsToOmit;
    }

    public Map<Coordinate, Integer> getResults() {
        return results;
    }
}
