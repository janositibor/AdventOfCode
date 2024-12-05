package TZJanosi.y2024.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadDataTest {
    @Test
    void readDataTest() {
        ReadData readData = new ReadData("testMatrix.txt");
        char[][] matrix=readData.getInputMatrix();
        assertEquals(5,matrix.length);
        assertEquals(11,matrix[0].length);
        assertEquals(11,matrix[matrix.length-1].length);
        assertEquals('X',matrix[0][0]);
        assertEquals('A',matrix[1][2]);
        assertEquals('X',matrix[4][10]);

    }

}