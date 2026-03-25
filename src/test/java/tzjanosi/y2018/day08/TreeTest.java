package tzjanosi.y2018.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test
    void sumOfMetaDataTest() {
        ReadData readData = new ReadData("testInput.txt");
        Tree tree = new Tree(readData.getOutput().get(0));
        assertEquals(138, tree.sumOfMetaData());
    }

    @Test
    void sumOfMetaDataProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Tree tree = new Tree(readData.getOutput().get(0));
        assertEquals(41555, tree.sumOfMetaData());
    }

}