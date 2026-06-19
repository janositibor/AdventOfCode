package tzjanosi.y2018.day25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void buildTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(2, service.build());
    }

    @Test
    void build2Test() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(4, service.build());
    }

    @Test
    void build3Test() {
        ReadData readData = new ReadData("testInput3.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(3, service.build());
    }

    @Test
    void build4Test() {
        ReadData readData = new ReadData("testInput4.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(8, service.build());
    }

    @Test
    void buildProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(367, service.build());
    }

}