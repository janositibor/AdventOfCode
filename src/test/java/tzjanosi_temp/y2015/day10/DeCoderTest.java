package tzjanosi_temp.y2015.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeCoderTest {
    private DeCoder deCoder = new DeCoder();

    @Test
    void coderTest() {
        assertEquals("11", deCoder.coder("1"));
        assertEquals("21", deCoder.coder("11"));
        assertEquals("1211", deCoder.coder("21"));
        assertEquals("111221", deCoder.coder("1211"));
    }

    @Test
    void repeatedCoderTest() {
        assertEquals("11", deCoder.repeatedCoder(1, "1"));
        assertEquals("21", deCoder.repeatedCoder(2, "1"));
        assertEquals("1211", deCoder.repeatedCoder(3, "1"));
        assertEquals("111221", deCoder.repeatedCoder(4, "1"));
    }

    @Test
    void lengthOfCodeAfterRepetitionTest() {
        assertEquals(2, deCoder.lengthOfCodeAfterRepetition(1, "1"));
        assertEquals(2, deCoder.lengthOfCodeAfterRepetition(2, "1"));
        assertEquals(4, deCoder.lengthOfCodeAfterRepetition(3, "1"));
        assertEquals(6, deCoder.lengthOfCodeAfterRepetition(4, "1"));
    }

    @Test
    void problemDataTest() {
        assertEquals(329356, deCoder.lengthOfCodeAfterRepetition(40, "3113322113"));
    }

    @Test
    void problemDataTestPart2() {
        assertEquals(4666278, deCoder.lengthOfCodeAfterRepetition(50, "3113322113"));
    }



}