package tzjanosi.y2018.day15.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void gameTest() {
        ReadData readData = new ReadData("testInput.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(27730, board.game());
    }

    @Test
    void gameTest2() {
        ReadData readData = new ReadData("testInput2.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(36334, board.game());
    }

    @Test
    void gameTest3() {
        ReadData readData = new ReadData("testInput3.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(39514, board.game());
    }

    @Test
    void gameTest4() {
        ReadData readData = new ReadData("testInput4.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(27755, board.game());
    }

    @Test
    void gameTest5() {
        ReadData readData = new ReadData("testInput5.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(28944, board.game());
    }

    @Test
    void gameTest6() {
        ReadData readData = new ReadData("testInput6.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(18740, board.game());
    }

    @Test
    void gameProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(250648, board.game());
    }

}