package tzjanosi.y2025.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    @Test
    void numberOfAccessibleRollsTest() {
        ReadData readData = new ReadData("testInput.txt");
        Storage storage = new Storage(readData.getOutput());
        assertEquals(13, storage.numberOfAccessibleRolls());
    }

    @Test
    void numberOfAccessibleRollsProgramDataTest() {
        ReadData readData = new ReadData("input.txt");
        Storage storage = new Storage(readData.getOutput());
        assertEquals(1370, storage.numberOfAccessibleRolls());
    }

    @Test
    void numberOfAllRemovableRollsTest() {
        ReadData readData = new ReadData("testInput.txt");
        Storage storage = new Storage(readData.getOutput());
        assertEquals(43, storage.numberOfAllRemovableRolls());
    }

    @Test
    void numberOfAllRemovableRollsProgramDataTest() {
        ReadData readData = new ReadData("input.txt");
        Storage storage = new Storage(readData.getOutput());
        assertEquals(8437, storage.numberOfAllRemovableRolls());
    }



}