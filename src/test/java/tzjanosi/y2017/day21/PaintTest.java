package tzjanosi.y2017.day21;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PaintTest {
    @Test
    void nextTest() {
        ReadData readData = new ReadData("testInput.txt");
        Paint paint = new Paint(readData.getOutput());
        assertEquals(true, Arrays.deepEquals(new char[][]{{'.', '#', '.'}, {'.', '.', '#'}, {'#', '#', '#'}}, paint.nextRepeated(0)));
        assertEquals(true, Arrays.deepEquals(new char[][]{{'#', '.', '.', '#'}, {'.', '.', '.', '.'}, {'.', '.', '.', '.'}, {'#', '.', '.', '#'}}, paint.nextRepeated(1)));
        assertEquals(true, Arrays.deepEquals(new char[][]{{'#', '#', '.', '#', '#', '.'}, {'#', '.', '.', '#', '.', '.',}, {'.', '.', '.', '.', '.', '.'}, {'#', '#', '.', '#', '#', '.'}, {'#', '.', '.', '#', '.', '.',}, {'.', '.', '.', '.', '.', '.'}}, paint.nextRepeated(2)));
    }

    @Test
    void numberOfOnsAfterStepsTest() {
        ReadData readData = new ReadData("testInput.txt");
        Paint paint = new Paint(readData.getOutput());
        assertEquals(12, paint.numberOfOnsAfterSteps(2));
    }

    @Test
    void numberOfOnsAfterStepsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Paint paint = new Paint(readData.getOutput());
        assertEquals(120, paint.numberOfOnsAfterSteps(5));
    }

    @Test
    void numberOfOnsAfterStepsProblemDataPart2Test() {
        ReadData readData = new ReadData("input.txt");
        Paint paint = new Paint(readData.getOutput());
        assertEquals(2204099, paint.numberOfOnsAfterSteps(18));
    }
}