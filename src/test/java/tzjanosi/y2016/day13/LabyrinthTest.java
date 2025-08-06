package tzjanosi.y2016.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    @Test
    void calculateWay() {
        Labyrinth labyrinth = new Labyrinth(new Coordinate(9, 6), new Coordinate(7, 4), 10);
        assertEquals(11, labyrinth.calculateWay());
    }

}