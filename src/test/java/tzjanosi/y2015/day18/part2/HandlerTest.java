package tzjanosi.y2015.day18.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandlerTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInputPart2.txt");
        Handler handler = new Handler(0, readData.getOutput());
        assertEquals(17, handler.numberOfActiveBulbsAterAnimation());
    }

    @Test
    void numberOfActiveBulbsAterAnimationTestData() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(1, readData.getOutput());
        assertEquals(18, handler.numberOfActiveBulbsAterAnimation());

        handler = new Handler(2, readData.getOutput());
        assertEquals(18, handler.numberOfActiveBulbsAterAnimation());

        handler = new Handler(5, readData.getOutput());
        assertEquals(17, handler.numberOfActiveBulbsAterAnimation());
    }

    @Test
    void numberOfActiveBulbsAterAnimationProblemData() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(100, readData.getOutput());
        assertEquals(1006, handler.numberOfActiveBulbsAterAnimation());
    }


}