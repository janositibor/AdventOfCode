package tzjanosi.y2024.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    @Test
    void transposeTest(){
        ReadData readData = new ReadData("testMatrix.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        char[][] matrix=Matrix.transposeChar(inputMatrix);
        assertEquals(11,matrix.length);
        assertEquals(5,matrix[0].length);
        assertEquals(5,matrix[matrix.length-1].length);
        assertEquals('X',matrix[0][0]);
        assertEquals('A',matrix[2][1]);
        assertEquals('X',matrix[10][4]);
    }

    @Test
    void shiftLeftGraduallyCharTest(){
        ReadData readData = new ReadData("testMatrix.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        char[][] matrix=Matrix.shiftLeftGraduallyChar(inputMatrix);
        assertEquals(5,matrix.length);
        assertEquals(11+5-1,matrix[0].length);
        assertEquals(11+5-1,matrix[matrix.length-1].length);
//        System.out.println(Arrays.deepToString(matrix));
        assertEquals('o',matrix[0][0]);
        assertEquals('S',matrix[0][5]);
        assertEquals('X',matrix[0][14]);
        assertEquals('X',matrix[4][0]);
        assertEquals('X',matrix[4][10]);
    }

    @Test
    void shiftUpGraduallyCharTest(){
        ReadData readData = new ReadData("testMatrix.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        char[][] matrix=Matrix.shiftUpGraduallyChar(inputMatrix);
//        System.out.println(Arrays.deepToString(matrix));
        assertEquals(15,matrix.length);
        assertEquals(11,matrix[0].length);
        assertEquals(11,matrix[matrix.length-1].length);
        assertEquals('o',matrix[0][0]);
        assertEquals('X',matrix[0][10]);
        assertEquals('X',matrix[14][0]);
        assertEquals('o',matrix[14][1]);
        assertEquals('A',matrix[10][2]);
        assertEquals('S',matrix[7][4]);

    }

    @Test
    void shiftDownGraduallyCharTest(){
        ReadData readData = new ReadData("testMatrix.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        char[][] matrix=Matrix.shiftDownGraduallyChar(inputMatrix);
//        System.out.println(Arrays.deepToString(matrix));
        assertEquals(15,matrix.length);
        assertEquals(11,matrix[0].length);
        assertEquals(11,matrix[matrix.length-1].length);
        assertEquals('X',matrix[0][0]);
        assertEquals('o',matrix[0][1]);
        assertEquals('M',matrix[2][1]);
        assertEquals('X',matrix[14][10]);
        assertEquals('A',matrix[12][8]);
        assertEquals('o',matrix[12][7]);
    }
}