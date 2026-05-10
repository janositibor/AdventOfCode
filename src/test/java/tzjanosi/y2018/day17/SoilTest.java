package tzjanosi.y2018.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoilTest {
    @Test
    void irrigateTest() {
        ReadData readData = new ReadData("testInput.txt");
        Soil soil = new Soil(readData.getOutput());
        assertEquals(57, soil.irrigate());
//        soil.print();
    }

    @Test
    void irrigateProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Soil soil = new Soil(readData.getOutput());
        assertEquals(37649, soil.irrigate());
//        soil.print();
    }

}