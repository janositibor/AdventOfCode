package tzjanosi.y2025.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReactorTest {
    @Test
    void findDistanceToOutTest() {
        ReadData readData = new ReadData("testInput.txt");
        Reactor reactor = new Reactor(readData.getOutput());
        assertEquals(5L, reactor.findDistanceToOut());
    }

    @Test
    void findDistanceToOutProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Reactor reactor = new Reactor(readData.getOutput());
        assertEquals(782L, reactor.findDistanceToOut());
    }

    @Test
    void findDistanceToOutPart2Test() {
        ReadData readData = new ReadData("testInput2.txt");
        Reactor reactor = new Reactor(readData.getOutput());
        assertEquals(2L, reactor.findDistanceToOutPart2());
    }

    @Test
    void findDistanceToOutPart2ProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Reactor reactor = new Reactor(readData.getOutput());
        assertEquals(401398751986160L, reactor.findDistanceToOutPart2());
    }



}