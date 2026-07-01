package tzjanosi.y2019.day08;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    @Test
    void checkSumTest() {
        ReadData readData = new ReadData("testInput.txt");
        Image image = new Image(3, 2, readData.getOutput().get(0));
        assertEquals(1, image.checkSum());
    }
//    @Test
//    void mergeTest() {
//        ReadData readData = new ReadData("testInput2.txt");
//        Image image = new Image(2, 2, readData.getOutput().get(0));
//        image.merge();
//    }

    @Test
    void checkSumProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Image image = new Image(25, 6, readData.getOutput().get(0));
        assertEquals(1360, image.checkSum());
    }

    @Test
    void mergeProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Image image = new Image(25, 6, readData.getOutput().get(0));
        image.merge();
        assertThat(image.getMerged().getRows())
                .hasSize(6)
                .contains("1111011100100100110011100",
                        "1000010010100101001010010",
                        "1110010010100101001010010",
                        "1000011100100101111011100",
                        "1000010000100101001010100",
                        "1000010000011001001010010");
    }


}