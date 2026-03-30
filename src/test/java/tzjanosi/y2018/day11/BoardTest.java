package tzjanosi.y2018.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void initTest() {
        Board board = new Board(8);
        Coordinate coordinate = new Coordinate(3, 5);
        assertEquals(4, board.getElements().get(coordinate).getPower());
    }

    @Test
    void initTest2() {
        Board board = new Board(57);
        Coordinate coordinate = new Coordinate(122, 79);
        assertEquals(-5, board.getElements().get(coordinate).getPower());
    }

    @Test
    void initTest3() {
        Board board = new Board(39);
        Coordinate coordinate = new Coordinate(217, 196);
        assertEquals(0, board.getElements().get(coordinate).getPower());
    }

    @Test
    void initTest4() {
        Board board = new Board(71);
        Coordinate coordinate = new Coordinate(101, 153);
        assertEquals(4, board.getElements().get(coordinate).getPower());
    }

    @Test
    void findMaxAreaTest() {
        Board board = new Board(18);
        assertEquals(new Coordinate(33, 45), board.findMaxArea());
    }

    @Test
    void findMaxAreaTest2() {
        Board board = new Board(42);
        assertEquals(new Coordinate(21, 61), board.findMaxArea());
    }

    @Test
    void findMaxAreaProblemDataTest() {
        Board board = new Board(2187);
        assertEquals(new Coordinate(235, 85), board.findMaxArea());
    }

    @Test
    void findExtendedMaxAreaTest() {
        Board board = new Board(18);
        assertEquals("90,269,16", board.findExtendedMaxArea());
    }

    @Test
    void findExtendedMaxAreaTest2() {
        Board board = new Board(42);
        assertEquals("232,251,12", board.findExtendedMaxArea());
    }

    @Test
    void findExtendedMaxAreaProgramDataTest() {
        Board board = new Board(2187);
        assertEquals("233,40,13", board.findExtendedMaxArea());
    }

}