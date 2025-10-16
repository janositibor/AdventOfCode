package tzjanosi.y2017.day10;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KnotHashTest {
    @Test
    void initTest() {
        ReadData readData = new ReadData("input.txt");
        KnotHash knotHash = new KnotHash(readData.getOutput().get(0));
        assertEquals(37230, knotHash.knot());
    }

}