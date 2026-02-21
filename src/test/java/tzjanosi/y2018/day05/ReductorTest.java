package tzjanosi.y2018.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReductorTest {
    @Test
    void reduceTest() {
        ReadData readData = new ReadData("testInput.txt");
        Reductor reductor = new Reductor(readData.getOutput().get(0));
        assertEquals(10, reductor.reduce());
    }

    @Test
    void reduceProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Reductor reductor = new Reductor(readData.getOutput().get(0));
        assertEquals(10766, reductor.reduce());
    }

    @Test
    void enhancedReduceTest() {
        ReadData readData = new ReadData("testInput.txt");
        Reductor reductor = new Reductor(readData.getOutput().get(0));
        assertEquals(4, reductor.enhancedReduce());
    }

    @Test
    void enhancedReduceProblemSataTest() {
        ReadData readData = new ReadData("input.txt");
        Reductor reductor = new Reductor(readData.getOutput().get(0));
        assertEquals(6538, reductor.enhancedReduce());
    }

}