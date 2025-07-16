package tzjanosi_temp.y2015.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {
    @Test
    void testData() {
        ReadData readData = new ReadData("testInput.txt");
        Race race = new Race(readData.getOutput());
        assertEquals(1120, race.maxDistance(1000));
    }

    @Test
    void problemData() {
        ReadData readData = new ReadData("input.txt");
        Race race = new Race(readData.getOutput());
        assertEquals(2640, race.maxDistance(2503));
    }

    @Test
    void testDataPart2() {
        ReadData readData = new ReadData("testInput.txt");
        Race race = new Race(readData.getOutput());
        assertEquals(689, race.calculateTotalPoints(1000));
    }

    @Test
    void problemDataPart2() {
        ReadData readData = new ReadData("input.txt");
        Race race = new Race(readData.getOutput());
        assertEquals(1102, race.calculateTotalPoints(2503));
    }


}