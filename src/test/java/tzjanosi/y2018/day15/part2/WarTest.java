package tzjanosi.y2018.day15.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarTest {
    @Test
    void cheatTest() {
        ReadData readData = new ReadData("testInput.txt");
        War war = new War(readData.getOutput());
        assertEquals(4988, war.cheat());
    }

    @Test
    void cheatTest3() {
        ReadData readData = new ReadData("testInput3.txt");
        War war = new War(readData.getOutput());
        assertEquals(31284, war.cheat());
    }

    @Test
    void cheatTest4() {
        ReadData readData = new ReadData("testInput4.txt");
        War war = new War(readData.getOutput());
        assertEquals(3478, war.cheat());
    }

    @Test
    void cheatTest5() {
        ReadData readData = new ReadData("testInput5.txt");
        War war = new War(readData.getOutput());
        assertEquals(6474, war.cheat());
    }

    @Test
    void cheatTest6() {
        ReadData readData = new ReadData("testInput6.txt");
        War war = new War(readData.getOutput());
        assertEquals(1140, war.cheat());
    }

    @Test
    void cheatProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        War war = new War(readData.getOutput());
        assertEquals(42224, war.cheat());
    }

}