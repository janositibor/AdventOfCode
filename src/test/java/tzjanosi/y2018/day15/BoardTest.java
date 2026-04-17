package tzjanosi.y2018.day15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void gameTest() {
        ReadData readData = new ReadData("testInput.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(27730, board.game());
    }

    @Test
    void gameProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Board board = new Board(readData.getOutput());
        assertEquals(250648, board.game());
    }

}