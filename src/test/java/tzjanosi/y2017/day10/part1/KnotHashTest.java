package tzjanosi.y2017.day10.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnotHashTest {
    @Test
    void hashTest() {
        ReadData readData = new ReadData("input.txt");
        KnotHash knotHash = new KnotHash(readData.getOutput().get(0));
        assertEquals(37230, knotHash.knot());
    }

}