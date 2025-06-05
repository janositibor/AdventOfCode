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

    @Test
    void isNicePart2() {
        Word word1 = new Word("qjhvhtzxzqqjkmpb");
        assertEquals(true, word1.isNicePart2());
        Word word2 = new Word("xxyxx");
        assertEquals(true, word2.isNicePart2());
        Word word3 = new Word("uurcxstgmygtbstg");
        assertEquals(false, word3.isNicePart2());
        Word word4 = new Word("ieodomkazucvgmuy");
        assertEquals(false, word4.isNicePart2());
    }
}