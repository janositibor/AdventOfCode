package tzjanosi.y2018.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void calculateNanobotsNearByTheStrongestTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(7, service.calculateNanobotsNearByTheStrongest());
    }

    @Test
    void calculateNanobotsNearByTheStrongestProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(652, service.calculateNanobotsNearByTheStrongest());
    }

    @Test
    void findManhattanDistanceToMostPopularPointTest() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(36, service.findManhattanDistanceToMostPopularPoint());
    }


    @Test
    void findManhattanDistanceToMostPopularPointProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(164960498, service.findManhattanDistanceToMostPopularPoint());
    }

}