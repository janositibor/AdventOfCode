package tzjanosi.y2025.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemSolverTest {
    @Test
    void calculateGrandTotalTest() {
        ReadData readData = new ReadData("testInput.txt");
        ProblemSolver problemSolver = new ProblemSolver(readData.getOutput());
        assertEquals(4277556L, problemSolver.calculateGrandTotal());
    }

    @Test
    void calculateGrandTotalProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        ProblemSolver problemSolver = new ProblemSolver(readData.getOutput());
        assertEquals(5361735137219L, problemSolver.calculateGrandTotal());
    }

    @Test
    void initTest() {
        ReadData readData = new ReadData("testInput.txt");
        ProblemSolver problemSolver = new ProblemSolver(readData.getOutput());
        assertArrayEquals(new char[]{'1', ' ', ' ', '*'}, problemSolver.getInputMatrix()[0]);
        assertArrayEquals(new char[]{' ', ' ', '4', ' '}, problemSolver.getInputMatrix()[14]);
        assertArrayEquals(new char[][]{
                {'1', ' ', ' ', '*'},
                {'2', '4', ' ', ' '},
                {'3', '5', '6', ' '},
                {' ', ' ', ' ', ' '},
                {'3', '6', '9', '+'},
                {'2', '4', '8', ' '},
                {'8', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', '3', '2', '*'},
                {'5', '8', '1', ' '},
                {'1', '7', '5', ' '},
                {' ', ' ', ' ', ' '},
                {'6', '2', '3', '+'},
                {'4', '3', '1', ' '},
                {' ', ' ', '4', ' '}}, problemSolver.getInputMatrix());

    }

    @Test
    void calculateGrandTotalForPart2Test() {
        ReadData readData = new ReadData("testInput.txt");
        ProblemSolver problemSolver = new ProblemSolver(readData.getOutput());
        assertEquals(3263827L, problemSolver.calculateGrandTotalForPart2());
    }

    @Test
    void calculateGrandTotalForPart2ProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        ProblemSolver problemSolver = new ProblemSolver(readData.getOutput());
        assertEquals(11744693538946L, problemSolver.calculateGrandTotalForPart2());
    }
}