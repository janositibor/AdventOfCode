package tzjanosi.y2025.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReactorTest {
    @Test
    void findDistanceToOutTest() {
        ReadData readData = new ReadData("testInput.txt");
        Reactor reactor = new Reactor(readData.getOutput());
        assertEquals(5, reactor.findDistanceToOut());
    }

    @Test
    void findDistanceToOutProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Reactor reactor = new Reactor(readData.getOutput());
        assertEquals(782, reactor.findDistanceToOut());
    }

}