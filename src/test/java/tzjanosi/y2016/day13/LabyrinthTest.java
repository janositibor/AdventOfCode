package tzjanosi.y2016.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    @Test
    void calculateWayTest() {
        Labyrinth labyrinth = new Labyrinth(new Coordinate(9, 6), new Coordinate(7, 4), 10);
        assertEquals(11, labyrinth.calculateWay());
    }

    @Test
    void locationsInDistanceTest() {
        Labyrinth labyrinth = new Labyrinth(new Coordinate(9, 6), new Coordinate(7, 4), 10);
        assertEquals(11, labyrinth.calculateWay());
        assertEquals(1, labyrinth.locationsInDistance(0));
        assertEquals(3, labyrinth.locationsInDistance(1));
        assertEquals(5, labyrinth.locationsInDistance(2));
    }


}