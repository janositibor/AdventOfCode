package tzjanosi.y2019.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void calculateNumberOfVisibleAsteroidsTest0() {
        ReadData readData = new ReadData("testInput0.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(8, service.calculateNumberOfVisibleAsteroids());
    }

    @Test
    void calculateNumberOfVisibleAsteroidsTest1() {
        ReadData readData = new ReadData("testInput1.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(33, service.calculateNumberOfVisibleAsteroids());
    }

    @Test
    void calculateNumberOfVisibleAsteroidsTest2() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(35, service.calculateNumberOfVisibleAsteroids());
    }

    @Test
    void calculateNumberOfVisibleAsteroidsTest3() {
        ReadData readData = new ReadData("testInput3.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(41, service.calculateNumberOfVisibleAsteroids());
    }

    @Test
    void calculateNumberOfVisibleAsteroidsTest4() {
        ReadData readData = new ReadData("testInput4.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(210, service.calculateNumberOfVisibleAsteroids());
    }

    @Test
    void calculateNumberOfVisibleAsteroidsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(263, service.calculateNumberOfVisibleAsteroids());
    }

    @Test
    void watchTowerCoordinateIDTest0() {
        ReadData readData = new ReadData("testInput0.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(304, service.watchTowerCoordinateID());
    }

    @Test
    void watchTowerCoordinateIDTest1() {
        ReadData readData = new ReadData("testInput1.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(508, service.watchTowerCoordinateID());
    }

    @Test
    void watchTowerCoordinateIDTest2() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(102, service.watchTowerCoordinateID());
    }

    @Test
    void watchTowerCoordinateIDTest3() {
        ReadData readData = new ReadData("testInput3.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(603, service.watchTowerCoordinateID());
    }

    @Test
    void watchTowerCoordinateIDTest4() {
        ReadData readData = new ReadData("testInput4.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(1113, service.watchTowerCoordinateID());
    }

    @Test
    void findTheAsteroidInSequenceTest() {
        ReadData readData = new ReadData("testInput5.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(801, service.findTheAsteroidInSequence(1));
    }

    @Test
    void findTheAsteroidInSequenceProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(1110, service.findTheAsteroidInSequence(200));
    }
}