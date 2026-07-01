package tzjanosi.y2019.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    @Test
    void checkSumTest() {
        ReadData readData = new ReadData("testInput.txt");
        Image image = new Image(3, 2, readData.getOutput().get(0));
        assertEquals(1, image.checkSum());
    }

    @Test
    void checkSumProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Image image = new Image(25, 6, readData.getOutput().get(0));
        assertEquals(1360, image.checkSum());
    }


}