package tzjanosi.y2018.day24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarTest {
    @Test
    void battleTest() {
        ReadData readData = new ReadData("testInput.txt");
        War war = new War(readData.getOutput());
        assertEquals(5216, war.battle());
    }

    @Test
    void battleProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        War war = new War(readData.getOutput());
        assertEquals(38008, war.battle());
    }

    @Test
    void findMinimalBoostTest() {
        ReadData readData = new ReadData("testInput.txt");
        War war = new War(readData.getOutput());
        assertEquals(51, war.findMinimalBoost());
    }

    @Test
    void findMinimalBoostProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        War war = new War(readData.getOutput());
        assertEquals(4009, war.findMinimalBoost());
    }
}