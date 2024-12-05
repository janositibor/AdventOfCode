package TZJanosi.y2024.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrosswordTest {
    @Test
    void findKeywordTest(){
        String keyword="XMAS";
        ReadData readData = new ReadData("testMatrix.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        Crossword crossword=new Crossword(inputMatrix,keyword);
//        System.out.println(crossword.getNumberOfFoundKeyword());
        assertEquals(8,crossword.getNumberOfFoundKeyword());
    }
    @Test
    void findKeywordWithProblemDataTest(){
        String keyword="XMAS";
        ReadData readData = new ReadData("inputCrossword.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        Crossword crossword=new Crossword(inputMatrix,keyword);
//        System.out.println(crossword.getNumberOfFoundKeyword());
        assertEquals(2504,crossword.getNumberOfFoundKeyword());
    }
    @Test
    void findXShapesTest(){
        String keyword="XMAS";
        ReadData readData = new ReadData("testMatrix.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        Crossword crossword=new Crossword(inputMatrix,keyword);
//        System.out.println(crossword.getNumberOfXShapes());
        assertEquals(2,crossword.getNumberOfXShapes());
    }
    @Test
    void findXShapesWithProblemDataTest(){
        String keyword="XMAS";
        ReadData readData = new ReadData("inputCrossword.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        Crossword crossword=new Crossword(inputMatrix,keyword);
//        System.out.println(crossword.getNumberOfXShapes());
        assertEquals(1923,crossword.getNumberOfXShapes());
    }

}