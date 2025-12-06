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

}