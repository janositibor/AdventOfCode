package tzjanosi.y2016.day13;

import java.util.ArrayList;
import java.util.List;

public class Organizer {
    private Coordinate end;
    private int favoriteNumber;
    private List<Integer> shortestWays = new ArrayList<>();
    private List<Integer> numberOfLocations = new ArrayList<>();
    private Coordinate size;


    public Organizer(Coordinate end, int favoriteNumber) {
        this.end = end;
        this.favoriteNumber = favoriteNumber;
        this.size = end;
    }

    public int findShortestWay() {
        int index = 0;
        while (index < 2 || isBetter(index)) {
            Labyrinth labyrinth = new Labyrinth(size, end, favoriteNumber);
            shortestWays.add(labyrinth.calculateWay());
            numberOfLocations.add(labyrinth.locationsInDistance(50));
            size = new Coordinate(size.getX() + 1, size.getY() + 1);
            index++;
        }
        return shortestWays.get(index - 1);
    }

    public int numberOfLocationsInDistance() {
        return numberOfLocations.get(numberOfLocations.size() - 1);
    }

    private boolean isBetter(int index) {
        return (index <= 1 || shortestWays.get(index - 1) < shortestWays.get(index - 2));
    }

}
