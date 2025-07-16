package tzjanosi_temp.y2015.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WrapperTest {
    @Test
    void init() {
        ReadData readData = new ReadData("input.txt");
        Wrapper wrapper = new Wrapper(readData.getOutput());
        assertThat(wrapper.getBoxes())
                .hasSize(1000);
    }

    @Test
    void calculateWrapperArea() {
        ReadData readData = new ReadData("input.txt");
        Wrapper wrapper = new Wrapper(readData.getOutput());
        assertEquals(1606483, wrapper.calculateWrapperArea());
    }

    @Test
    void calculateRibbonLength() {
        ReadData readData = new ReadData("input.txt");
        Wrapper wrapper = new Wrapper(readData.getOutput());
        assertEquals(3842356, wrapper.calculateRibbonLength());
    }

}