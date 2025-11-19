package tzjanosi.y2017.day19;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PipelineTest {
    @Test
    void initTest() {
        ReadData readData = new ReadData("testInput.txt");
        Pipeline pipeline = new Pipeline(readData.getOutput());
        assertThat(pipeline.getSigns())
                .hasSize(75)
                .contains(new Sign('|', false, new Coordinate(5, 0)))
                .contains(new Sign('+', false, new Coordinate(5, 5)))
                .contains(new Sign('A', true, new Coordinate(5, 2)))
                .filteredOn(t -> t.isLetter())
                .hasSize(6)
                .contains(new Sign('F', true, new Coordinate(1, 3)));
    }

    @Test
    void readTest() {
        ReadData readData = new ReadData("testInput.txt");
        Pipeline pipeline = new Pipeline(readData.getOutput());
        assertEquals("ABCDEF", pipeline.read());
    }

    @Test
    void readProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Pipeline pipeline = new Pipeline(readData.getOutput());
        assertEquals("NDWHOYRUEA", pipeline.read());
    }
}