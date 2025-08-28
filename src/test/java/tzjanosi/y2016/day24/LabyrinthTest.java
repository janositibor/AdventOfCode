package tzjanosi.y2016.day24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    @Test
    void calculateWayTest() {
        ReadData readData = new ReadData("testInput.txt");
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
        assertEquals(14, labyrinth.calculateWay());
    }

    @Test
    void calculateWayProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
        assertEquals(428, labyrinth.calculateWay());
    }

}