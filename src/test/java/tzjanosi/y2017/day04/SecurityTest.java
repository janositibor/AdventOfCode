package tzjanosi.y2017.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityTest {
    @Test
    void numberOfValidPhrasesTest() {
        ReadData readData = new ReadData("testInput.txt");
        Security security = new Security(readData.getOutput());
        assertEquals(2, security.numberOfValidPhrases());
    }

    @Test
    void numberOfValidPhrasesProgramDataTest() {
        ReadData readData = new ReadData("input.txt");
        Security security = new Security(readData.getOutput());
        assertEquals(337, security.numberOfValidPhrases());
    }
}