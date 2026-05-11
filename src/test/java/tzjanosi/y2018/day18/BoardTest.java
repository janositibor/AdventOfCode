package tzjanosi.y2018.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void evolveTest() {
        ReadData readData = new ReadData("testInput.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(1147, board.evolve(10));
//        board.print();
    }

    @Test
    void evolveProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(564375, board.evolve(10));
//        board.print();
    }


}