package tzjanosi.y2015.day17;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(0, readData.getOutput());
        assertTrue(handler.getStart().getFilled().isEmpty());
        assertThat(handler.getStart().getBuckets())
                .hasSize(5)
                .extracting(Bucket::getId, Bucket::getVolume)
                .containsOnly(tuple(0, 20),
                        tuple(1, 15),
                        tuple(2, 10),
                        tuple(3, 5),
                        tuple(4, 5));
    }

    @Test
    void testDataFindNumbersOfValidFilling() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(25, readData.getOutput());
        assertEquals(4, handler.findNumbersOfValidFilling());
    }

    @Test
    void problemDataFindNumbersOfValidFilling() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(150, readData.getOutput());
        assertEquals(1638, handler.findNumbersOfValidFilling());
    }

    @Test
    void testDataFindNumbersOfCombinationWithMinimalBucketNumbers() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(25, readData.getOutput());
        assertEquals(3, handler.findNumbersOfCombinationWithMinimalBucketNumbers());
    }

    @Test
    void problemDataFindNumbersOfCombinationWithMinimalBucketNumbers() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(150, readData.getOutput());
        assertEquals(17, handler.findNumbersOfCombinationWithMinimalBucketNumbers());
    }

}