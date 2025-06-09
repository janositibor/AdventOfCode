package TZJanosi.y2015.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCounterTest {
    @Test
    void testCharacterCounter() {
        ReadData readData = new ReadData("testInput.txt");
        CharacterCounter counter = new CharacterCounter(readData.getOutput());
        assertEquals(12, counter.getCountOfCharacters());
    }

    @Test
    void problemData() {
        ReadData readData = new ReadData("input.txt");
        CharacterCounter counter = new CharacterCounter(readData.getOutput());
        assertEquals(1371, counter.getCountOfCharacters());
    }

}