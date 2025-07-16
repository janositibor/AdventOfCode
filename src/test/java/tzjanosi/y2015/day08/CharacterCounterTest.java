package tzjanosi.y2015.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCounterTest {
    @Test
    void testCharacterCounterPart1() {
        ReadData readData = new ReadData("testInput.txt");
        CharacterCounter counter = new CharacterCounter(readData.getOutput());
        assertEquals(12, counter.getCountOfCharactersPart1());
    }

    @Test
    void problemDataPart1() {
        ReadData readData = new ReadData("input.txt");
        CharacterCounter counter = new CharacterCounter(readData.getOutput());
        assertEquals(1371, counter.getCountOfCharactersPart1());
    }

    @Test
    void testCharacterCounterPart2() {
        ReadData readData = new ReadData("testInput.txt");
        CharacterCounter counter = new CharacterCounter(readData.getOutput());
        assertEquals(19, counter.getCountOfCharactersPart2());
    }

    @Test
    void problemDataPart2() {
        ReadData readData = new ReadData("input.txt");
        CharacterCounter counter = new CharacterCounter(readData.getOutput());
        assertEquals(2117, counter.getCountOfCharactersPart2());
    }

}