package TZJanosi.y2015.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    @Test
    void isNice() {
        Word word1 = new Word("ugknbfddgicrmopn");
        assertEquals(true, word1.isNice());
        Word word2 = new Word("aaa");
        assertEquals(true, word2.isNice());
        Word word3 = new Word("jchzalrnumimnmhp");
        assertEquals(false, word3.isNice());
        Word word4 = new Word("haegwjzuvuyypxyu");
        assertEquals(false, word4.isNice());
        Word word5 = new Word("dvszwmarrgswjxmb");
        assertEquals(false, word5.isNice());
    }

}