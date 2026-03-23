package tzjanosi.y2018.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test
    void testInit() {
        ReadData readData = new ReadData("testInput.txt");
        Tree tree = new Tree(readData.getOutput());
        assertEquals("[Node{name=A, preconditions=[C]}, Node{name=B, preconditions=[A]}, Node{name=C, preconditions=[]}, Node{name=D, preconditions=[A]}, Node{name=E, preconditions=[B, D, F]}, Node{name=F, preconditions=[C]}]", tree.getNodes().toString());
    }

    @Test
    void testFindWay() {
        ReadData readData = new ReadData("testInput.txt");
        Tree tree = new Tree(readData.getOutput());
        assertEquals("CABDFE", tree.findWay());
    }

    @Test
    void testProblemDataFindWay() {
        ReadData readData = new ReadData("input.txt");
        Tree tree = new Tree(readData.getOutput());
        assertEquals("ADEFKLBVJQWUXCNGORTMYSIHPZ", tree.findWay());
    }

    @Test
    void testFindTime() {
        ReadData readData = new ReadData("testInput.txt");
        Tree tree = new Tree(readData.getOutput());
        assertEquals(15, tree.findTime(2, 0));
    }

    @Test
    void testProblemDataFindTime() {
        ReadData readData = new ReadData("input.txt");
        Tree tree = new Tree(readData.getOutput());
        assertEquals(1120, tree.findTime(5, 60));
    }


}