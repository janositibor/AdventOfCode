package tzjanosi.y2016.day22;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    @Test
    void createTest() {
        ReadData readData = new ReadData("input.txt");
        Grid grid = new Grid(readData.getOutput());
        assertThat(grid.getNodes())
                .hasSize(960);
    }

    @Test
    void numberOfCompatiblePairsTest() {
        ReadData readData = new ReadData("input.txt");
        Grid grid = new Grid(readData.getOutput());
        assertEquals(937, grid.numberOfCompatiblePairs());
    }


}