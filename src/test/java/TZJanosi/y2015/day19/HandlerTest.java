package TZJanosi.y2015.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {
    @Test
    void findNumberOfStepsTestData2() {
        ReadData readData = new ReadData("testInput2.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(3, handler.findNumberOfSteps());
    }

    @Test
    void findNumberOfStepsTestData3() {
        ReadData readData = new ReadData("testInput3.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(6, handler.findNumberOfSteps());
    }

    @Test
    void findNumberOfStepsProblemData() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(195, handler.countSteps());
    }

}