package tzjanosi.y2024.day20.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    @Test
    void testDataTest() {
        ReadData readData = new ReadData("testInput.txt");
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
        assertEquals(84, labyrinth.calculateWay());
        System.out.println(labyrinth.getWayOutList());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
        assertEquals(9432, labyrinth.calculateWay());
//        System.out.println(labyrinth.getWayOut());
    }
}