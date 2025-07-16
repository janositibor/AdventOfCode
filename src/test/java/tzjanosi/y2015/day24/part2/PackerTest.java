package tzjanosi.y2015.day24.part2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PackerTest {
    @Test
    void calculateMinQeWithTestDataPart1() {
        Sleigh sleigh = new Sleigh(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10, 11), 3);
        Packer packer = new Packer(sleigh);
        assertEquals(99, packer.calculateMinQe());
    }

    @Test
    void calculateMinQeWithTestDataPart2() {
        Sleigh sleigh = new Sleigh(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10, 11), 4);
        Packer packer = new Packer(sleigh);
        assertEquals(44, packer.calculateMinQe());
    }

    @Test
    void calculateMinQeWithProblemDataPart1() {
        Sleigh sleigh = new Sleigh(Arrays.asList(1, 3, 5, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113), 3);
        Packer packer = new Packer(sleigh);
        assertEquals(11266889531L, packer.calculateMinQe());

    }

    @Test
    void calculateMinQeWithProblemDataPart2() {
        Sleigh sleigh = new Sleigh(Arrays.asList(1, 3, 5, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113), 4);
        Packer packer = new Packer(sleigh);
        assertEquals(77387711L, packer.calculateMinQe());

    }

}