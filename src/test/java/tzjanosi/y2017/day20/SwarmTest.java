package tzjanosi.y2017.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwarmTest {
    @Test
    void idForLongTermMinimalManhattanDistanceTest() {
        ReadData readData = new ReadData("testInput.txt");
        Swarm swarm = new Swarm(readData.getOutput());
        assertEquals(0, swarm.idForLongTermMinimalManhattanDistance());
    }

    @Test
    void idForLongTermMinimalManhattanDistanceProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Swarm swarm = new Swarm(readData.getOutput());
        assertEquals(344, swarm.idForLongTermMinimalManhattanDistance());
    }

    @Test
    void startTest() {
        ReadData readData = new ReadData("testInput2.txt");
        Swarm swarm = new Swarm(readData.getOutput());
        assertEquals(0, swarm.start());
    }

    @Test
    void startProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Swarm swarm = new Swarm(readData.getOutput());
        assertEquals(404, swarm.start());
    }

}